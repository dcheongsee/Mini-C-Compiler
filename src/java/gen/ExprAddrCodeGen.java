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
                System.out.println("isParameter: " + vd.isParameter);
                System.out.println("isArrayParam: " + vd.isArrayParam);
                if (vd.globalLabel != null) {
                    asmProg.getCurrentTextSection().emit(OpCode.LA, addrReg, Label.get(vd.globalLabel));
                } else if (vd.isParameter) {
                    // use different behavior for array params vs pointer params
                    if (vd.isArrayParam) {
                        // for params declared as arrays (which decay to pointers)
                        // load pointer value from parameter slot
                        asmProg.getCurrentTextSection().emit(OpCode.LW, addrReg, Register.Arch.fp, vd.offset);
                    } else {
                        // for pointer (and scalar) params the caller already stored the pointer value
                        // so compute address using ADDIU
                        asmProg.getCurrentTextSection().emit(OpCode.ADDIU, addrReg, Register.Arch.fp, vd.offset);
                    }
                } else {
                    asmProg.getCurrentTextSection().emit(OpCode.ADDIU, addrReg, Register.Arch.fp, vd.offset);
                }
                System.out.println("ExprAddrCodeGen: VarExpr '" + v.name +
                        "' at computed address (fp+offset): " + vd.offset);
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

            // FieldAccess, address = base address + field offset
            case FieldAccessExpr fa -> {
                Register baseAddr = visit(fa.expr);
                int fieldOffset;
                if (fa.expr.type instanceof StructType) {
                    fieldOffset = getFieldOffset(fa.expr.type, fa.field);
                } else if (fa.expr.type instanceof ClassType ct) {
                    ClassDecl cd = lookupClassDecl(ct.getName());
                    if (cd == null) {
                        throw new RuntimeException("Class " + ct.getName() + " not found");
                    }
                    boolean found = false;
                    fieldOffset = 0;
                    for (VarDecl fld : cd.getFields()) {
                        if (fld.name.equals(fa.field)) {
                            fieldOffset = fld.offset;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        throw new RuntimeException("Field " + fa.field + " not found in class " + ct.getName());
                    }
                } else {
                    throw new RuntimeException("Field access on non-struct and non-class type");
                }
                Register finalAddr = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADDIU, finalAddr, baseAddr, fieldOffset);
                System.out.println("ExprAddrCodeGen: FieldAccessExpr on field '" + fa.field +
                        "' with base address: " + baseAddr + " and offset: " + fieldOffset);
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
        } else if (arrayType instanceof PointerType pt) {
            return getSize(pt.base);
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
    private int computeStructSize(StructTypeDecl decl) {
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
            if (decl == null) return 4; // at least 4
            int maxAlign = 1;
            for (Decl f : decl.getFields()) {
                if (f instanceof VarDecl vd) {
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

    // lookup class declaration in the global AST
    private ClassDecl lookupClassDecl(String className) {
        for (Decl d : sem.SemanticAnalyzer.GlobalAST.decls) {
            if (d instanceof ClassDecl cd && cd.getName().equals(className)) {
                return cd;
            }
        }
        return null;
    }
}
