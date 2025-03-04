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
    private final Map<String, StructTypeDecl> structDecls;
    private final ASTNode astRoot;
    private final ExprAddrCodeGen addrGen;

    public ExprValCodeGen(AssemblyProgram asmProg, ASTNode astRoot) {
        this.asmProg = asmProg;
        this.astRoot = astRoot;
        this.structDecls = collectStructDecls(astRoot);
        this.addrGen = new ExprAddrCodeGen(asmProg, astRoot);
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
            throw new RuntimeException("ExprValCodeGen: null expression encountered.");
        }

        return switch (e) {
            // VarExpr, get address from addrGen, load LB/LW
            case VarExpr v -> {
                Register addr = addrGen.visit(v);
                Register result = Register.Virtual.create();
                if (isCharType(v.vd.type)) {
                    asmProg.getCurrentTextSection().emit(OpCode.LB, result, addr, 0);
                } else {
                    asmProg.getCurrentTextSection().emit(OpCode.LW, result, addr, 0);
                }
                yield result;
            }

            // ArrayAccess, address, load
            case ArrayAccessExpr aa -> {
                Register addr = addrGen.visit(aa);
                Register result = Register.Virtual.create();
                Type elemType = null;
                if (aa.array.type instanceof ArrayType at) {
                    elemType = at.elementType;
                }
                if (elemType == null) {
                    throw new RuntimeException("ArrayAccess on non-array type or unknown elementType.");
                }
                if (isCharType(elemType)) {
                    asmProg.getCurrentTextSection().emit(OpCode.LB, result, addr, 0);
                } else {
                    asmProg.getCurrentTextSection().emit(OpCode.LW, result, addr, 0);
                }
                yield result;
            }

            // FieldAccess, address, load
            case FieldAccessExpr fa -> {
                Register addr = addrGen.visit(fa);
                Register result = Register.Virtual.create();
                // figure out the field's type if needed
                VarDecl fieldDecl = findFieldDecl(fa.expr.type, fa.field);
                Type fieldType = (fieldDecl != null) ? fieldDecl.type : BaseType.UNKNOWN;
                if (isCharType(fieldType)) {
                    asmProg.getCurrentTextSection().emit(OpCode.LB, result, addr, 0);
                } else {
                    asmProg.getCurrentTextSection().emit(OpCode.LW, result, addr, 0);
                }
                yield result;
            }

            // ValueAt, *pointer, get pointer value then LB/LW from that pointer
            case ValueAtExpr va -> {
                // evaluate pointer
                Register pointerVal = this.visit(va.expr);  // recursively get pointer
                Register result = Register.Virtual.create();
                Type baseType = getValueAtType(va.expr.type);
                if (isCharType(baseType)) {
                    asmProg.getCurrentTextSection().emit(OpCode.LB, result, pointerVal, 0);
                } else {
                    asmProg.getCurrentTextSection().emit(OpCode.LW, result, pointerVal, 0);
                }
                yield result;
            }

            // AddressOf, the value is the address of subexpr, call addrGen
            case AddressOfExpr ao -> {
                Register address = addrGen.visit(ao.expr);
                yield address;
            }

            // Assign, evaluate RHS, compute LHS address, then store
            case Assign assign -> {
                Register rhsVal = visit(assign.right);
                Register lhsAddr = addrGen.visit(assign.left);

                // store
                if (isCharType(assign.left.type)) {
                    asmProg.getCurrentTextSection().emit(OpCode.SB, rhsVal, lhsAddr, 0);
                } else {
                    asmProg.getCurrentTextSection().emit(OpCode.SW, rhsVal, lhsAddr, 0);
                }
                // value of the assignment expression is the RHS
                yield rhsVal;
            }

            // BinOp, evaluate left & right, apply MIPS op, result
            case BinOp bin -> {
                Register leftVal = visit(bin.left);
                Register rightVal = visit(bin.right);
                Register result = Register.Virtual.create();
                switch (bin.op) {
                    case ADD -> asmProg.getCurrentTextSection().emit(OpCode.ADD, result, leftVal, rightVal);
                    case SUB -> asmProg.getCurrentTextSection().emit(OpCode.SUB, result, leftVal, rightVal);
                    case MUL -> asmProg.getCurrentTextSection().emit(OpCode.MUL, result, leftVal, rightVal);
                    case DIV -> {
                        asmProg.getCurrentTextSection().emit(OpCode.DIV, leftVal, rightVal);
                        Register quot = Register.Virtual.create();
                        asmProg.getCurrentTextSection().emit(OpCode.MFLO, quot);
                        result = quot;
                    }
                    case MOD -> {
                        asmProg.getCurrentTextSection().emit(OpCode.DIV, leftVal, rightVal);
                        Register rem = Register.Virtual.create();
                        asmProg.getCurrentTextSection().emit(OpCode.MFHI, rem);
                        result = rem;
                    }
                    case LT -> asmProg.getCurrentTextSection().emit(OpCode.SLT, result, leftVal, rightVal);
                    case GT -> asmProg.getCurrentTextSection().emit(OpCode.SLT, result, rightVal, leftVal);
                    case LE -> {
                        // left<=right => 1 - (right<left)
                        Register tmp = Register.Virtual.create();
                        asmProg.getCurrentTextSection().emit(OpCode.SLT, tmp, rightVal, leftVal);
                        Register one = Register.Virtual.create();
                        asmProg.getCurrentTextSection().emit(OpCode.LI, one, 1);
                        asmProg.getCurrentTextSection().emit(OpCode.SUB, result, one, tmp);
                    }
                    case GE -> {
                        // left>=right => 1 - (left<right)
                        Register tmp = Register.Virtual.create();
                        asmProg.getCurrentTextSection().emit(OpCode.SLT, tmp, leftVal, rightVal);
                        Register one = Register.Virtual.create();
                        asmProg.getCurrentTextSection().emit(OpCode.LI, one, 1);
                        asmProg.getCurrentTextSection().emit(OpCode.SUB, result, one, tmp);
                    }
                    case EQ -> {
                        Register tmp1 = Register.Virtual.create();
                        asmProg.getCurrentTextSection().emit(OpCode.SLT, tmp1, leftVal, rightVal);
                        Register tmp2 = Register.Virtual.create();
                        asmProg.getCurrentTextSection().emit(OpCode.SLT, tmp2, rightVal, leftVal);
                        Register tmpOr = Register.Virtual.create();
                        asmProg.getCurrentTextSection().emit(OpCode.OR, tmpOr, tmp1, tmp2);
                        Register one = Register.Virtual.create();
                        asmProg.getCurrentTextSection().emit(OpCode.LI, one, 1);
                        asmProg.getCurrentTextSection().emit(OpCode.SUB, result, one, tmpOr);
                    }
                    case NE -> {
                        Register tmp1 = Register.Virtual.create();
                        asmProg.getCurrentTextSection().emit(OpCode.SLT, tmp1, leftVal, rightVal);
                        Register tmp2 = Register.Virtual.create();
                        asmProg.getCurrentTextSection().emit(OpCode.SLT, tmp2, rightVal, leftVal);
                        asmProg.getCurrentTextSection().emit(OpCode.OR, result, tmp1, tmp2);
                    }
                    case AND -> asmProg.getCurrentTextSection().emit(OpCode.AND, result, leftVal, rightVal);
                    case OR -> asmProg.getCurrentTextSection().emit(OpCode.OR, result, leftVal, rightVal);
                    default ->
                            throw new RuntimeException("Unsupported BinOp: " + bin.op);
                }
                yield result;
            }

            // IntLiteral, load immediate
            case IntLiteral il -> {
                Register result = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.LI, result, il.value);
                yield result;
            }

            // ChrLiteral, load char immediate
            case ChrLiteral cl -> {
                Register result = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.LI, result, cl.value);
                yield result;
            }

            // StrLiteral, create a label in .data, la, reg
            case StrLiteral sl -> {
                String labelName = "str_" + Integer.toUnsignedString(sl.value.hashCode());
                Label lbl = Label.get(labelName);
                String fixed = escapeString(sl.value); // esc chars
                asmProg.dataSection.emit(lbl);
                asmProg.dataSection.emit(new gen.asm.Directive("asciiz \"" + fixed + "\""));
                Register result = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.LA, result, lbl);
                yield result;
            }

            // Typecast, just evaluate subexpr
            case TypecastExpr tc -> {
                Register subVal = visit(tc.expr);
                yield subVal;
            }

            // FunCallExpr, either built-in or normal function call
            case FunCallExpr fc -> {
                if (isBuiltin(fc.name)) {
                    yield handleBuiltinCall(fc);
                } else {
                    // push arguments in reverse
                    for (int i = fc.args.size() - 1; i >= 0; i--) {
                        Register argVal = visit(fc.args.get(i));
                        asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -4);
                        asmProg.getCurrentTextSection().emit(OpCode.SW, argVal, Register.Arch.sp, 0);
                    }
                    // call
                    asmProg.getCurrentTextSection().emit(OpCode.JAL, Label.get(fc.name));
                    // pop
                    int stackAdj = fc.args.size() * 4;
                    asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, stackAdj);

                    // move v0, result
                    Register result = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.ADD, result, Register.Arch.v0, Register.Arch.zero);
                    yield result;
                }
            }

            // SizeOfExpr, produce immediate
            case SizeOfExpr so -> {
                int size = getSize(so.type);
                Register result = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.LI, result, size);
                yield result;
            }

            default -> {
                for (ASTNode child : e.children()) {
                    if (child instanceof Expr cexpr) {
                        visit(cexpr);
                    }
                }
                throw new RuntimeException("ExprValCodeGen: unsupported expression type: " + e.getClass().getSimpleName());
            }
        };
    }


    private boolean isBuiltin(String name) {
        return name.equals("read_i") ||
                name.equals("print_c") ||
                name.equals("read_c") ||
                name.equals("mcmalloc") ||
                name.equals("print_s") ||
                name.equals("print_i");
    }


    private Register handleBuiltinCall(FunCallExpr fc) {
        switch (fc.name) {
            case "print_i" -> {
                // evaluate 1 argument, put in a0
                Register arg = visit(fc.args.get(0));
                asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.a0, arg, Register.Arch.zero);
                asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 1); // print int
                asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
                // return dummy
                Register dummy = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, dummy, Register.Arch.zero, Register.Arch.zero);
                return dummy;
            }
            case "print_c" -> {
                Register arg = visit(fc.args.get(0));
                asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.a0, arg, Register.Arch.zero);
                asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 11); // print char
                asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
                Register dummy = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, dummy, Register.Arch.zero, Register.Arch.zero);
                return dummy;
            }
            case "read_i" -> {
                asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 5); // read int
                asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
                Register result = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, result, Register.Arch.v0, Register.Arch.zero);
                return result;
            }
            case "read_c" -> {
                asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 12); // read char
                asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
                Register result = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, result, Register.Arch.v0, Register.Arch.zero);
                return result;
            }
            case "print_s" -> {
                Register arg = visit(fc.args.get(0));
                asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.a0, arg, Register.Arch.zero);
                asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 4); // print string
                asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
                Register dummy = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, dummy, Register.Arch.zero, Register.Arch.zero);
                return dummy;
            }
            case "mcmalloc" -> {
                Register size = visit(fc.args.get(0));
                asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.a0, size, Register.Arch.zero);
                asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 9); // sbrk/malloc
                asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
                Register result = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, result, Register.Arch.v0, Register.Arch.zero);
                return result;
            }
            default ->
                    throw new RuntimeException("Unknown built-in function: " + fc.name);
        }
    }


    // is the type a char?
    private boolean isCharType(Type type) {
        return (type instanceof BaseType bt && bt == BaseType.CHAR);
    }

    // if we have *p, we want the base type of the pointer
    private Type getValueAtType(Type t) {
        if (t instanceof PointerType pt) {
            return pt.base;
        }
        return BaseType.UNKNOWN;
    }


    private VarDecl findFieldDecl(Type structType, String field) {
        if (!(structType instanceof StructType st)) {
            return null;
        }
        StructTypeDecl std = structDecls.get(st.name);
        if (std == null) return null;
        for (Decl d : std.getFields()) {
            if (d instanceof VarDecl vd && vd.name.equals(field)) {
                return vd;
            }
        }
        return null;
    }

    // get total size in bytes for a type
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
        int offset = 0;
        int maxAlign = 1;
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
                for (Decl f : decl.getFields()) {
                    if (f instanceof VarDecl vd) {
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
        if (alignment <= 1) return value;
        return ((value + alignment - 1) / alignment) * alignment;
    }

    private String escapeString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\n': sb.append("\\n"); break;
                case '\t': sb.append("\\t"); break;
                case '\r': sb.append("\\r"); break;
                case '\\': sb.append("\\\\"); break;
                case '\'': sb.append("\\'"); break;
                case '\"': sb.append("\\\""); break;
                case '\f': sb.append("\\f"); break;
                case '\b': sb.append("\\b"); break;
                case '\u000B': sb.append("\\v"); break;
                case '\u0007': sb.append("\\a"); break;
                case '\0': sb.append("\\0"); break;
                default:   sb.append(c); break;
            }
        }
        return sb.toString();
    }

}
