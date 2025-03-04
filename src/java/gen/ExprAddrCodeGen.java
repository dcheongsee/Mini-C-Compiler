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

    private final Map<String, StructTypeDecl> structDecls;
    private final ASTNode astRoot;

    public ExprAddrCodeGen(AssemblyProgram asmProg, ASTNode astRoot) {
        this.asmProg = asmProg;
        this.astRoot = astRoot;
        this.structDecls = collectStructDecls(astRoot);
    }

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
        if (e == null) {
            throw new RuntimeException("ExprAddrCodeGen: null expression encountered.");
        }
        return switch (e) {

            // VarExpr, either global or local
            case VarExpr v -> {
                VarDecl vd = v.vd;  // NameAnalyzer should have set this
                if (vd == null) {
                    throw new RuntimeException("VarExpr has no VarDecl linked: " + v.name);
                }
                Register addrReg = Register.Virtual.create();
                if (vd.globalLabel != null) {
                    // global var, la  addrReg, globalLabel
                    asmProg.getCurrentTextSection().emit(OpCode.LA, addrReg, Label.get(vd.globalLabel));
                } else {
                    // local, offset from $fp
                    asmProg.getCurrentTextSection().emit(OpCode.ADDIU, addrReg, Register.Arch.fp, vd.offset);
                }
                yield addrReg;
            }

            // ArrayAccess, base address + (index * elemSize)
            case ArrayAccessExpr aa -> {
                // get base address
                Register baseAddr = visit(aa.array);

                // compute index’s value with ExprValCodeGen
                Register indexVal = new ExprValCodeGen(asmProg, astRoot).visit(aa.index);

                int elemSize = getSizeOfArrayElement(aa.array.type);
                Register sizeReg = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.LI, sizeReg, elemSize);

                Register offsetReg = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.MUL, offsetReg, indexVal, sizeReg);

                // final address = base + offset
                Register finalAddr = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, finalAddr, baseAddr, offsetReg);
                yield finalAddr;
            }

            // FieldAccess, struct base + offset of field
            case FieldAccessExpr fa -> {
                Register baseAddr = visit(fa.expr);
                int fieldOffset = getFieldOffset(fa.expr.type, fa.field);
                Register finalAddr = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADDIU, finalAddr, baseAddr, fieldOffset);
                yield finalAddr;
            }

            // ValueAt, address is the pointer's value
            case ValueAtExpr va -> {
                Register pointerVal = new ExprValCodeGen(asmProg, astRoot).visit(va.expr);
                yield pointerVal; // that pointerVal is the address
            }

            // AddressOf, address of e, just call visit(e) because e must be an lvalue
            case AddressOfExpr ao -> {
                yield visit(ao.expr);
            }

            default -> throw new RuntimeException(
                    "Expression type " + e.getClass().getSimpleName()
                            + " is not an lvalue and does not have an address."
            );
        };
    }

    private int getSizeOfArrayElement(Type arrayType) {
        if (arrayType instanceof ArrayType at) {
            return getSize(at.elementType);
        }
        throw new RuntimeException("getSizeOfArrayElement called on non-array type");
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
            if (decl == null) {
                throw new RuntimeException("Unknown struct " + st.name);
            }
            return computeStructSize(decl);
        }
        return 0;
    }

    // calculates total size of a struct with alignment
    private int computeStructSize(StructTypeDecl std) {
        int offset = 0;
        int maxAlign = 1;
        for (Decl field : std.getFields()) {
            if (field instanceof VarDecl vd) {
                int size = getSize(vd.type);
                int align = getAlignment(vd.type);
                offset = alignTo(offset, align);
                offset += size;
                if (align > maxAlign) maxAlign = align;
            }
        }
        offset = alignTo(offset, maxAlign);
        return offset;
    }

    // compute alignment
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
            if (decl == null) return 1;
            int maxAlign = 1;
            for (Decl f : decl.getFields()) {
                if (f instanceof VarDecl vd) {
                    int align = getAlignment(vd.type);
                    if (align > maxAlign) maxAlign = align;
                }
            }
            return maxAlign;
        }
        return 1;
    }

    private int alignTo(int value, int alignment) {
        if (alignment <= 1) return value;
        return ((value + alignment - 1) / alignment) * alignment;
    }

    private int getFieldOffset(Type structType, String fieldName) {
        if (!(structType instanceof StructType st)) {
            throw new RuntimeException("Field access on non-struct type");
        }
        StructTypeDecl decl = structDecls.get(st.name);
        if (decl == null) {
            throw new RuntimeException("Struct " + st.name + " not found");
        }
        int offset = 0;
        int maxAlign = 1;
        for (Decl d : decl.getFields()) {
            if (d instanceof VarDecl vd) {
                int size = getSize(vd.type);
                int align = getAlignment(vd.type);
                offset = alignTo(offset, align);
                if (vd.name.equals(fieldName)) {
                    return offset;
                }
                offset += size;
                if (align > maxAlign) maxAlign = align;
            }
        }
        throw new RuntimeException("Field " + fieldName + " not found in struct " + st.name);
    }

}
