package gen;

import ast.*;
import gen.asm.AssemblyProgram;
import gen.asm.Label;
import gen.asm.OpCode;
import gen.asm.Register;
import java.util.Deque;
import java.util.ArrayDeque;

public class StmtCodeGen extends CodeGen {

    private final ASTNode astRoot;
    private final Deque<Label> breakStack = new ArrayDeque<>();
    private final Deque<Label> continueStack = new ArrayDeque<>();
    // should be set by FunCodeGen before processing a function body
    public Label returnLabel;

    public StmtCodeGen(AssemblyProgram asmProg, ASTNode astRoot) {
        this.asmProg = asmProg;
        this.astRoot = astRoot;
    }

    public void visit(Stmt s) {
        switch (s) {
            case Block b -> {
                for (Stmt inner : b.stmts) {
                    visit(inner);
                }
            }
            case ExprStmt es -> {
                new ExprValCodeGen(asmProg, astRoot).visit(es.expr);
            }
            case If i -> {
                Label elseLabel = Label.create("if_else");
                Label endLabel = Label.create("if_end");
                Register cond = new ExprValCodeGen(asmProg, astRoot).visit(i.expr);
                asmProg.getCurrentTextSection().emit(OpCode.BEQZ, cond, elseLabel);
                visit(i.thenStmt);
                asmProg.getCurrentTextSection().emit(OpCode.J, endLabel);
                asmProg.getCurrentTextSection().emit(elseLabel);
                if (i.elseStmt != null) {
                    visit(i.elseStmt);
                }
                asmProg.getCurrentTextSection().emit(endLabel);
            }
            case While wh -> {
                Label startLabel = Label.create("while_start");
                Label exitLabel = Label.create("while_exit");
                breakStack.push(exitLabel);
                continueStack.push(startLabel);
                asmProg.getCurrentTextSection().emit(startLabel);
                Register cond = new ExprValCodeGen(asmProg, astRoot).visit(wh.expr);
                asmProg.getCurrentTextSection().emit(OpCode.BEQZ, cond, exitLabel);
                visit(wh.stmt);
                asmProg.getCurrentTextSection().emit(OpCode.J, startLabel);
                asmProg.getCurrentTextSection().emit(exitLabel);
                breakStack.pop();
                continueStack.pop();
            }
            case Break b -> {
                if (breakStack.isEmpty()) {
                    throw new RuntimeException("Break statement not within a loop");
                }
                asmProg.getCurrentTextSection().emit(OpCode.J, breakStack.peek());
            }
            case Continue c -> {
                if (continueStack.isEmpty()) {
                    throw new RuntimeException("Continue statement not within a loop");
                }
                asmProg.getCurrentTextSection().emit(OpCode.J, continueStack.peek());
            }
            case Return r -> {
                if (r.expr != null) {
                    if (r.expr.type instanceof StructType) {
                        // for struct returns, evaluate expression to get its address
                        Register structAddr = new ExprAddrCodeGen(asmProg, astRoot).visit(r.expr);
                        int structSize = getSize(r.expr.type);
                        emitStructCopy(structAddr, Register.Arch.a0, structSize);
                    } else {
                        Register retVal = new ExprValCodeGen(asmProg, astRoot).visit(r.expr);
                        asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.v0, retVal, Register.Arch.zero);
                    }
                }
                if (returnLabel == null) {
                    throw new RuntimeException("Return label not set");
                }
                asmProg.getCurrentTextSection().emit(OpCode.J, returnLabel);
            }
            default -> {
                for (ASTNode child : s.children()) {
                    if (child instanceof Stmt stmtChild) {
                        visit(stmtChild);
                    }
                }
            }
        }
    }
    // computes size in bytes of given type
    private int getSize(Type type) {
        if (type instanceof BaseType bt) {
            return switch (bt) {
                case INT -> 4;
                case CHAR -> 1;
                default -> 0;
            };
        } else if (type instanceof PointerType) {
            return 4;
        } else if (type instanceof ArrayType at) {
            return getSize(at.elementType) * at.length;
        } else if (type instanceof StructType st) {
            return computeStructSize(st);
        }
        return 0;
    }

    // computes total size of a struct type using its decl
    private int computeStructSize(StructType st) {
        StructTypeDecl decl = lookupStructDecl(st.name);
        if (decl == null) {
            throw new RuntimeException("Struct not found: " + st.name);
        }
        int offset = 0;
        int maxAlign = 1;
        for (Decl field : decl.getFields()) {
            if (field instanceof VarDecl vd) {
                int size = getSize(vd.type);
                int align = getAlignment(vd.type);
                offset = alignTo(offset, align);
                offset += size;
                if (align > maxAlign) {
                    maxAlign = align;
                }
            }
        }
        // now force struct’s alignment to at least 4
        maxAlign = Math.max(4, maxAlign);
        // then align the final offset too
        offset = alignTo(offset, maxAlign);
        return offset;
    }

    // computes alignment requirement for a type
    private int getAlignment(Type type) {
        if (type instanceof BaseType bt) {
            return switch (bt) {
                case INT -> 4;
                case CHAR -> 1;
                default -> 1;
            };
        } else if (type instanceof PointerType) {
            return 4;
        } else if (type instanceof ArrayType at) {
            return getAlignment(at.elementType);
        } else if (type instanceof StructType st) {
            StructTypeDecl decl = lookupStructDecl(st.name);
            if (decl == null) return 1;

            int maxAlign = 1;
            for (Decl field : decl.getFields()) {
                if (field instanceof VarDecl vd) {
                    int align = getAlignment(vd.type);
                    if (align > maxAlign) {
                        maxAlign = align;
                    }
                }
            }
            // force struct alignment to at least 4
            maxAlign = Math.max(4, maxAlign);

            return maxAlign;
        }

        return 1;
    }

    // aligns value to the next multiple of alignment
    private int alignTo(int value, int alignment) {
        if (alignment <= 1) return value;
        return ((value + alignment - 1) / alignment) * alignment;
    }

    // looks up struct decl from the global AST
    private StructTypeDecl lookupStructDecl(String structName) {
        for (Decl d : sem.SemanticAnalyzer.GlobalAST.decls) {
            if (d instanceof StructTypeDecl std && std.getName().equals(structName)) {
                return std;
            }
        }
        return null;
    }

    // copies memory from src to dest, word by word and then any remaining bytes
    private void emitStructCopy(Register src, Register dest, int size) {
        int words = size / 4;
        int remainder = size % 4;
        for (int i = 0; i < words; i++) {
            Register temp = Register.Virtual.create();
            asmProg.getCurrentTextSection().emit(OpCode.LW, temp, src, i * 4);
            asmProg.getCurrentTextSection().emit(OpCode.SW, temp, dest, i * 4);
        }
        for (int i = words * 4; i < size; i++) {
            Register temp = Register.Virtual.create();
            asmProg.getCurrentTextSection().emit(OpCode.LB, temp, src, i);
            asmProg.getCurrentTextSection().emit(OpCode.SB, temp, dest, i);
        }
    }

}
