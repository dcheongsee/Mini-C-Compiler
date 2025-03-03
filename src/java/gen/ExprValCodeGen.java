package gen;

import ast.*;
import gen.asm.AssemblyProgram;
import gen.asm.Label;
import gen.asm.OpCode;
import gen.asm.Register;
import java.util.HashMap;
import java.util.Map;

/**
 * Generates code to evaluate an expression and return the result in a register.
 */
public class ExprValCodeGen extends CodeGen {

    // map struct names to decl
    private Map<String, StructTypeDecl> structDecls;

    public ExprValCodeGen(AssemblyProgram asmProg, ASTNode astRoot) {
        this.asmProg = asmProg;
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
        if (e instanceof VarExpr v) {
            VarDecl vd = v.vd;
            Register addr = computeAddress(v);
            Register result = Register.Virtual.create();
            if (isCharType(vd.type)) {
                asmProg.getCurrentTextSection().emit(OpCode.LB, result, addr, 0);
            } else {
                asmProg.getCurrentTextSection().emit(OpCode.LW, result, addr, 0);
            }
            return result;
        } else if (e instanceof ArrayAccessExpr aa) {
            Register addr = computeAddress(aa);
            Register result = Register.Virtual.create();
            Type elemType = null;
            if (aa.array.type instanceof ArrayType at) {
                elemType = at.elementType;
            } else {
                throw new RuntimeException("ArrayAccess on non-array type");
            }
            if (isCharType(elemType)) {
                asmProg.getCurrentTextSection().emit(OpCode.LB, result, addr, 0);
            } else {
                asmProg.getCurrentTextSection().emit(OpCode.LW, result, addr, 0);
            }
            return result;
        } else if (e instanceof FieldAccessExpr fa) {
            Register addr = computeAddress(fa);
            Register result = Register.Virtual.create();
            VarDecl fieldDecl = null;
            if (fa.expr.type instanceof StructType st) {
                StructTypeDecl decl = structDecls.get(st.name);
                if (decl != null) {
                    for (Decl d : decl.getFields()) {
                        if (d instanceof VarDecl vd && vd.name.equals(fa.field)) {
                            fieldDecl = vd;
                            break;
                        }
                    }
                }
            }
            if (fieldDecl == null) {
                throw new RuntimeException("Field " + fa.field + " not found");
            }
            if (isCharType(fieldDecl.type)) {
                asmProg.getCurrentTextSection().emit(OpCode.LB, result, addr, 0);
            } else {
                asmProg.getCurrentTextSection().emit(OpCode.LW, result, addr, 0);
            }
            return result;
        } else if (e instanceof ValueAtExpr va) {
            Register ptr = visit(va.expr);
            Register result = Register.Virtual.create();
            if (va.expr.type instanceof PointerType pt) {
                if (isCharType(pt.base)) {
                    asmProg.getCurrentTextSection().emit(OpCode.LB, result, ptr, 0);
                } else {
                    asmProg.getCurrentTextSection().emit(OpCode.LW, result, ptr, 0);
                }
            } else {
                throw new RuntimeException("ValueAtExpr applied to non-pointer type");
            }
            return result;
        } else if (e instanceof Assign a) {
            Register rhs = visit(a.right);
            Register addr = computeAddress(a.left);
            if (isCharType(a.left.type)) {
                asmProg.getCurrentTextSection().emit(OpCode.SB, rhs, addr, 0);
            } else {
                asmProg.getCurrentTextSection().emit(OpCode.SW, rhs, addr, 0);
            }
            return rhs;
        } else if (e instanceof BinOp bin) {
            Register left = visit(bin.left);
            Register right = visit(bin.right);
            Register result = Register.Virtual.create();
            switch (bin.op) {
                case ADD:
                    asmProg.getCurrentTextSection().emit(OpCode.ADD, result, left, right);
                    break;
                case SUB:
                    asmProg.getCurrentTextSection().emit(OpCode.SUB, result, left, right);
                    break;
                case MUL:
                    asmProg.getCurrentTextSection().emit(OpCode.MUL, result, left, right);
                    break;
                case DIV:
                    // stores quotient in LO and remainder in HI
                    asmProg.getCurrentTextSection().emit(OpCode.DIV, left, right);
                    // retrieve quotient from LO
                    Register quotient = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.MFLO, quotient);
                    result = quotient;
                    break;
                case MOD:
                    // quotient in LO, remainder in HI
                    asmProg.getCurrentTextSection().emit(OpCode.DIV, left, right);
                    // retrieve remainder from HI
                    Register remainder = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.MFHI, remainder);
                    result = remainder;
                    break;
                case LT:
                    asmProg.getCurrentTextSection().emit(OpCode.SLT, result, left, right);
                    break;
                case GT:
                    asmProg.getCurrentTextSection().emit(OpCode.SLT, result, right, left);
                    break;
                case LE: {
                    Register tmp = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.SLT, tmp, right, left);
                    Register one = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.LI, one, 1);
                    asmProg.getCurrentTextSection().emit(OpCode.SUB, result, one, tmp);
                    break;
                }
                case GE: {
                    Register tmp = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.SLT, tmp, left, right);
                    Register one = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.LI, one, 1);
                    asmProg.getCurrentTextSection().emit(OpCode.SUB, result, one, tmp);
                    break;
                }
                case EQ: {
                    Register tmp1 = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.SLT, tmp1, left, right);
                    Register tmp2 = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.SLT, tmp2, right, left);
                    Register tmpOr = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.OR, tmpOr, tmp1, tmp2);
                    Register one = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.LI, one, 1);
                    asmProg.getCurrentTextSection().emit(OpCode.SUB, result, one, tmpOr);
                    break;
                }
                case NE: {
                    Register tmp1 = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.SLT, tmp1, left, right);
                    Register tmp2 = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.SLT, tmp2, right, left);
                    asmProg.getCurrentTextSection().emit(OpCode.OR, result, tmp1, tmp2);
                    break;
                }
                case AND:
                    asmProg.getCurrentTextSection().emit(OpCode.AND, result, left, right);
                    break;
                case OR:
                    asmProg.getCurrentTextSection().emit(OpCode.OR, result, left, right);
                    break;
                default:
                    throw new RuntimeException("Unsupported binary operator " + bin.op);
            }
            return result;
        } else if (e instanceof IntLiteral il) {
            Register result = Register.Virtual.create();
            asmProg.getCurrentTextSection().emit(OpCode.LI, result, il.value);
            return result;
        } else if (e instanceof ChrLiteral cl) {
            Register result = Register.Virtual.create();
            asmProg.getCurrentTextSection().emit(OpCode.LI, result, cl.value);
            return result;
        } else if (e instanceof StrLiteral sl) {
            String labelName = "str_" + sl.value.hashCode();
            Label lbl = Label.get(labelName);
            asmProg.dataSection.emit(lbl);
            asmProg.dataSection.emit(new gen.asm.Directive("asciiz \"" + sl.value + "\""));
            Register result = Register.Virtual.create();
            asmProg.getCurrentTextSection().emit(OpCode.LA, result, lbl);
            return result;
        } else if (e instanceof TypecastExpr tc) {
            return visit(tc.expr);
        } else if (e instanceof FunCallExpr fc) {
            if (isBuiltin(fc.name)) {
                return handleBuiltinCall(fc);
            } else {
                // push arguments in reverse order
                for (int i = fc.args.size() - 1; i >= 0; i--) {
                    Register argVal = visit(fc.args.get(i));
                    asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -4);
                    asmProg.getCurrentTextSection().emit(OpCode.SW, argVal, Register.Arch.sp, 0);
                }
                asmProg.getCurrentTextSection().emit(OpCode.JAL, Label.get(fc.name));
                int stackAdj = fc.args.size() * 4;
                asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, stackAdj);
                Register result = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, result, Register.Arch.v0, Register.Arch.zero);
                return result;
            }
        } else if (e instanceof SizeOfExpr so) {
            int size = getSize(so.type);
            Register result = Register.Virtual.create();
            asmProg.getCurrentTextSection().emit(OpCode.LI, result, size);
            return result;
        } else if (e instanceof AddressOfExpr ao) {
            return computeAddress(ao.expr);
        }
        throw new RuntimeException("Unsupported expression type: " + e.getClass().getSimpleName());
    }

    private Register computeAddress(Expr e) {
        if (e instanceof VarExpr v) {
            VarDecl vd = v.vd;
            Register r = Register.Virtual.create();
            if (vd.globalLabel != null) {
                asmProg.getCurrentTextSection().emit(OpCode.LA, r, Label.get(vd.globalLabel));
            } else {
                asmProg.getCurrentTextSection().emit(OpCode.ADDIU, r, Register.Arch.fp, vd.offset);
            }
            return r;
        } else if (e instanceof ArrayAccessExpr aa) {
            Register base = computeAddress(aa.array);
            Register index = visit(aa.index);
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
        } else if (e instanceof FieldAccessExpr fa) {
            Register base = computeAddress(fa.expr);
            int fieldOffset = getFieldOffset(fa.expr.type, fa.field);
            Register addr = Register.Virtual.create();
            asmProg.getCurrentTextSection().emit(OpCode.ADDIU, addr, base, fieldOffset);
            return addr;
        } else if (e instanceof AddressOfExpr ao) {
            return computeAddress(ao.expr);
        }
        throw new RuntimeException("Not an lvalue: " + e.getClass().getSimpleName());
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

    private boolean isCharType(Type type) {
        if (type instanceof BaseType bt) {
            return bt == BaseType.CHAR;
        }
        return false;
    }

    private Register handleBuiltinCall(FunCallExpr fc) {
        switch (fc.name) {
            case "print_i": {
                // evaluate argument and move it into $a0
                Register arg = visit(fc.args.get(0));
                asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.a0, arg, Register.Arch.zero);
                asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 1);
                asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
                // return a dummy value (0)
                Register dummy = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, dummy, Register.Arch.zero, Register.Arch.zero);
                return dummy;
            }
            case "print_c": {
                Register arg = visit(fc.args.get(0));
                asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.a0, arg, Register.Arch.zero);
                asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 11); // syscall for print char
                asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
                Register dummy = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, dummy, Register.Arch.zero, Register.Arch.zero);
                return dummy;
            }
            case "read_i": {
                asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 5); // syscall for read int
                asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
                Register result = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, result, Register.Arch.v0, Register.Arch.zero);
                return result;
            }
            case "read_c": {
                asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 12); // syscall for read char
                asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
                Register result = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, result, Register.Arch.v0, Register.Arch.zero);
                return result;
            }
            case "print_s": {
                Register arg = visit(fc.args.get(0));
                asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.a0, arg, Register.Arch.zero);
                asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 4); // syscall for print string
                asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
                Register dummy = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, dummy, Register.Arch.zero, Register.Arch.zero);
                return dummy;
            }
            case "mcmalloc": {
                Register size = visit(fc.args.get(0));
                asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.a0, size, Register.Arch.zero);
                asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 9); // syscall for sbrk/malloc
                asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
                Register result = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, result, Register.Arch.v0, Register.Arch.zero);
                return result;
            }
            default:
                throw new RuntimeException("Unknown built-in function: " + fc.name);
        }
    }

    private boolean isBuiltin(String name) {
        return name.equals("read_i") ||
                name.equals("print_c") ||
                name.equals("read_c") ||
                name.equals("mcmalloc") ||
                name.equals("print_s") ||
                name.equals("print_i");
    }


}
