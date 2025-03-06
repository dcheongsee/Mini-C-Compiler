package gen;


import ast.*;
import gen.asm.AssemblyProgram;
import gen.asm.Label;
import gen.asm.OpCode;
import gen.asm.Register;
import sem.SemanticAnalyzer;

/**
 * A visitor that produces code for a single function declaration
 */
public class FunCodeGen extends CodeGen {

    public FunCodeGen(AssemblyProgram asmProg) {
        this.asmProg = asmProg;
    }

    public void visit(FunDef fd) {
        // Each function should be produced in its own section.
        // This is necessary for the register allocator.
        asmProg.emitNewTextSection();

        if (fd.name.equals("main")) {
            asmProg.getCurrentTextSection().emit(new gen.asm.Directive("globl main"));
        }

        // emit function label
        Label functionLabel = Label.get(fd.name);
        asmProg.getCurrentTextSection().emit(functionLabel);

        asmProg.getCurrentTextSection().emit(OpCode.ANDI, Register.Arch.sp, Register.Arch.sp, 0xFFFFFFFC);
        asmProg.getCurrentTextSection().emit(new gen.asm.Comment("Align sp to word boundary"));

        // prolog, push $ra onto the stack then set up frame pointer
        asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -8);
        asmProg.getCurrentTextSection().emit(OpCode.SW, Register.Arch.fp, Register.Arch.sp, 4); // Save old fp
        asmProg.getCurrentTextSection().emit(OpCode.SW, Register.Arch.ra, Register.Arch.sp, 0); // Save ra
        asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.fp, Register.Arch.sp, Register.Arch.zero);

        int localSpace = fd.localVarSize;  // computed by MemAllocCodeGen
        int alignedLocalSpace = ((localSpace + 3) / 4) * 4;  // round up to nearest multiple of 4
        if (alignedLocalSpace > 0) {
            asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -alignedLocalSpace);
        }

        // set up return label
        StmtCodeGen scd = new StmtCodeGen(asmProg, SemanticAnalyzer.GlobalAST);
        Label retLabel = Label.create("return_" + fd.name);
        scd.returnLabel = retLabel;

        System.out.println("FunCodeGen: Entering function '" + fd.name + "'. Local variable space = " + fd.localVarSize);

        // emit function body
        scd.visit(fd.block);

        // epilog, emit return label then restore $ra
        asmProg.getCurrentTextSection().emit(retLabel);

        if (fd.type instanceof StructType) {
            System.out.println("Function '" + fd.name + "' returns a struct. Searching for designated return variable...");
            VarDecl retDecl = findReturnVarDecl(fd);
            if (retDecl == null) {
                throw new RuntimeException("No designated return variable found for function " + fd.name);
            }
            System.out.println("Using return variable '" + retDecl.name + "' at offset " + retDecl.offset + " for struct return.");
            Register localResult = Register.Virtual.create();
            asmProg.getCurrentTextSection().emit(OpCode.ADDIU, localResult, Register.Arch.fp, retDecl.offset);
            int structSize = getSize(fd.type);
            System.out.println("Copying struct of size " + structSize + " from offset " + retDecl.offset + " into $a0");
            emitStructCopy(localResult, Register.Arch.a0, structSize);
        }

        if (alignedLocalSpace > 0) {
            asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, localSpace);
        }

        asmProg.getCurrentTextSection().emit(OpCode.LW, Register.Arch.ra, Register.Arch.sp, 0);
        asmProg.getCurrentTextSection().emit(OpCode.LW, Register.Arch.fp, Register.Arch.sp, 4);
        asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, 8);

        if (fd.name.equals("main")) {
            // exit sequence for main
            asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 10);
            asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
        } else {
            asmProg.getCurrentTextSection().emit(OpCode.JR, Register.Arch.ra);
        }
    }

    // copies a memory block from src to dest
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

    private int alignTo(int value, int alignment) {
        if (alignment <= 1) return value;
        return ((value + alignment - 1) / alignment) * alignment;
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
            StructTypeDecl decl = lookupStructDecl(st.name);
            if (decl != null) {
                return computeStructAlignment(decl);
            }
            return 4; // fallback alignment for undefined structs
        }
        return 1;
    }

    private int computeStructAlignment(StructTypeDecl decl) {
        int maxAlign = 1;
        for (Decl field : decl.getFields()) {
            if (field instanceof VarDecl vd) {
                int align = getAlignment(vd.type);
                if (align > maxAlign) {
                    maxAlign = align;
                }
            }
        }
        return maxAlign;
    }

    private int computeStructSize(StructTypeDecl decl) {
        int offset = 0, maxAlign = 1;
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
        return alignTo(offset, maxAlign);
    }

    private int computeStructSize(StructType st) {
        StructTypeDecl decl = lookupStructDecl(st.name);
        if (decl == null) return 0;
        return computeStructSize(decl);
    }

    private StructTypeDecl lookupStructDecl(String structName) {
        for (Decl d : sem.SemanticAnalyzer.GlobalAST.decls) {
            if (d instanceof StructTypeDecl std && std.getName().equals(structName)) {
                return std;
            }
        }
        return null;
    }

    private VarDecl findReturnVarDecl(FunDef fd) {
        if (!(fd.type instanceof StructType)) {
            System.out.println("Function '" + fd.name + "' does not return a struct.");
            return null;
        }
        StructType retType = (StructType) fd.type;
        System.out.println("Looking for a local variable with struct type '" + retType.name + "' in function '" + fd.name + "'.");
        for (VarDecl vd : fd.block.vds) {
            if (!vd.isParameter && vd.type instanceof StructType) {
                StructType localType = (StructType) vd.type;
                System.out.println("Checking local variable '" + vd.name + "' with type '" + localType.name + "' and offset " + vd.offset);
                if (localType.name.equals(retType.name)) { // compare names
                    System.out.println("Matched return variable: '" + vd.name + "' with offset " + vd.offset);
                    return vd;
                }
            }
        }
        System.out.println("No matching return variable found for function '" + fd.name + "'.");
        return null;
    }



}
