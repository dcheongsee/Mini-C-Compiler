package gen;

import ast.*;
import gen.asm.AssemblyProgram;
import gen.asm.Label;
import gen.asm.OpCode;
import gen.asm.Register;
import java.util.HashMap;
import java.util.Map;

/**
 * Generates code to calculate the address of an expression and return the result in a register.
 */
public class ExprAddrCodeGen extends CodeGen {

    // mapping of struct names to their decl
    private Map<String, StructTypeDecl> structDecls;
    private final ASTNode astRoot;

    public ExprAddrCodeGen(AssemblyProgram asmProg, ASTNode astRoot) {
        this.asmProg = asmProg;
        this.astRoot = astRoot;
        this.structDecls = collectStructDecls(astRoot);
    }

    // recursively collect struct decl
    private Map<String, StructTypeDecl> collectStructDecls(ASTNode node) {
        Map<String, StructTypeDecl> map = new HashMap<>();
        collect(node, map);
        return map;
    }

    private void collect(ASTNode node, Map<String, StructTypeDecl> map) {
        if (node instanceof StructTypeDecl std) {
            map.put(std.getName(), std);
        }
        for (ASTNode child : node.children()) {
            collect(child, map);
        }
    }

    public Register visit(Expr e) {
        switch (e) {
            case VarExpr v -> {
                VarDecl vd = v.vd;
                Register r = Register.Virtual.create();
                if (vd.globalLabel != null) {
                    asmProg.getCurrentTextSection().emit(OpCode.LA, r, Label.get(vd.globalLabel));
                } else {
                    asmProg.getCurrentTextSection().emit(OpCode.ADDIU, r, Register.Arch.fp, vd.offset);
                }
                return r;
            }
            case ArrayAccessExpr aa -> {
                Register base = visit(aa.array);
                Register index = new ExprValCodeGen(asmProg, astRoot).visit(aa.index);
                int elemSize;
                if (aa.array.type instanceof ArrayType at) {
                    elemSize = getSize(at.elementType);
                } else {
                    throw new RuntimeException("ArrayAccess on non-array type");
                }
                Register immReg = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADDIU, immReg, Register.Arch.zero, elemSize);
                Register offsetReg = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.MUL, offsetReg, index, immReg);
                Register addr = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, addr, base, offsetReg);
                return addr;
            }
            case FieldAccessExpr fa -> {
                Register base = visit(fa.expr);
                int fieldOffset = getFieldOffset(fa.expr.type, fa.field);
                Register addr = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADDIU, addr, base, fieldOffset);
                return addr;
            }
            case AddressOfExpr ao -> {
                return visit(ao.expr);
            }
            case ValueAtExpr va -> {
                return new ExprValCodeGen(asmProg, astRoot).visit(va.expr);
            }
            case null, default ->
                    throw new RuntimeException("Expression is not an lvalue: " + e.getClass().getSimpleName());
        }
    }

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
            StructTypeDecl decl = structDecls.get(st.name);
            if (decl == null) throw new RuntimeException("Struct not found: " + st.name);
            return computeStructSize(decl);
        }
        return 0;
    }

    private int computeStructSize(StructTypeDecl decl) {
        int offset = 0, maxAlign = 1;
        for (Decl field : decl.getFields()) {
            if (field instanceof VarDecl vd) {
                int size = getSize(vd.type);
                int align = getAlignment(vd.type);
                offset = alignTo(offset, align);
                offset += size;
                if (align > maxAlign) maxAlign = align;
            }
        }
        return alignTo(offset, maxAlign);
    }

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
            StructTypeDecl decl = structDecls.get(st.name);
            int maxAlign = 1;
            if (decl != null) {
                for (Decl field : decl.getFields()) {
                    if (field instanceof VarDecl vd) {
                        int align = getAlignment(vd.type);
                        if (align > maxAlign) maxAlign = align;
                    }
                }
            }
            return maxAlign;
        }
        return 1;
    }

    private int alignTo(int value, int alignment) {
        if (alignment == 0) return value;
        return ((value + alignment - 1) / alignment) * alignment;
    }

    private int getFieldOffset(Type structType, String fieldName) {
        if (!(structType instanceof StructType st))
            throw new RuntimeException("Not a struct type");
        StructTypeDecl decl = structDecls.get(st.name);
        if (decl == null) throw new RuntimeException("Struct declaration not found for " + st.name);
        int offset = 0;
        for (Decl d : decl.getFields()) {
            if (d instanceof VarDecl vd) {
                int size = getSize(vd.type);
                int align = getAlignment(vd.type);
                offset = alignTo(offset, align);
                if (vd.name.equals(fieldName))
                    return offset;
                offset += size;
            }
        }
        throw new RuntimeException("Field " + fieldName + " not found in struct " + st.name);
    }
}
