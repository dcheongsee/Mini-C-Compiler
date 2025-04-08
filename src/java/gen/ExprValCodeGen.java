package gen;

import ast.*;
import gen.asm.AssemblyProgram;
import gen.asm.Label;
import gen.asm.OpCode;
import gen.asm.Register;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Generates code to evaluate an expression and return the result in a register.
 */
public class ExprValCodeGen extends CodeGen {

    // map struct names to decl
    private final Map<String, StructTypeDecl> structDecls;
    private final ASTNode astRoot;
    private final ExprAddrCodeGen addrGen;
    private static final Map<String, Label> emittedStrings = new HashMap<>();


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
                VarDecl vd = v.vd;
                if (vd == null) {
                    throw new RuntimeException("VarExpr has no VarDecl linked: " + v.name);
                }

                if (vd.type instanceof ArrayType || vd.type instanceof StructType || (vd.isParameter && vd.isArrayParam)) {
                    yield addrGen.visit(v);
                }
                Register addr = addrGen.visit(v);
                Register result = Register.Virtual.create();
                if (isCharType(vd.type)) {
                    asmProg.getCurrentTextSection().emit(OpCode.LB, result, addr, 0);
                } else {
                    asmProg.getCurrentTextSection().emit(OpCode.LW, result, addr, 0);
                }
                yield result;
            }

            // ArrayAccess, address, load
            case ArrayAccessExpr aa -> {
                Register baseAddr;
                if (aa.array instanceof VarExpr) {
                    VarDecl vd = ((VarExpr) aa.array).vd;
                    if (vd != null && vd.isParameter && vd.isArrayParam) {
                        baseAddr = addrGen.visit(aa.array);
                    } else {
                        if (aa.array.type instanceof ArrayType) {
                            baseAddr = addrGen.visit(aa.array);
                        } else {
                            baseAddr = new ExprValCodeGen(asmProg, astRoot).visit(aa.array);
                        }
                    }
                } else {
                    // for non varExpr cases check computed type
                    if (aa.array.type instanceof ArrayType) {
                        baseAddr = addrGen.visit(aa.array);
                    } else {
                        baseAddr = new ExprValCodeGen(asmProg, astRoot).visit(aa.array);
                    }
                }

                // Compute the index’s value
                Register indexVal = new ExprValCodeGen(asmProg, astRoot).visit(aa.index);
                int elemSize = getSizeOfArrayElement(aa.array.type);
                Register sizeReg = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.LI, sizeReg, elemSize);
                Register offsetReg = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.MUL, offsetReg, indexVal, sizeReg);
                // final address = base address + offset
                Register finalAddr = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, finalAddr, baseAddr, offsetReg);

                // if element type is a struct return its address
                if (aa.type instanceof StructType) {
                    yield finalAddr;
                }

                Register result = Register.Virtual.create();
                Type elemType;
                if (aa.array.type instanceof ArrayType at) {
                    elemType = at.elementType;
                } else if (aa.array.type instanceof PointerType pt) {
                    elemType = pt.base;
                } else {
                    throw new RuntimeException("ArrayAccess on non-array type or unknown elementType.");
                }
                if (isCharType(elemType)) {
                    asmProg.getCurrentTextSection().emit(OpCode.LB, result, finalAddr, 0);
                } else {
                    asmProg.getCurrentTextSection().emit(OpCode.LW, result, finalAddr, 0);
                }
                yield result;
            }

            // FieldAccess, address, load
            case FieldAccessExpr fa -> {
                Register baseAddr = new ExprAddrCodeGen(asmProg, astRoot).visit(fa.expr);
                VarDecl fieldDecl;
                int fieldOffset;
                Type fieldType;
                if (fa.expr.type instanceof StructType st) {
                    fieldDecl = findFieldDecl(fa.expr.type, fa.field);
                    fieldOffset = getFieldOffset(fa.expr.type, fa.field);
                    fieldType = fieldDecl.type;
                } else if (fa.expr.type instanceof ClassType ct) {
                    ClassDecl cd = lookupClassDecl(ct.getName());
                    if (cd == null) {
                        throw new RuntimeException("Class " + ct.getName() + " not found");
                    }
                    fieldDecl = null;
                    for (VarDecl fld : cd.getFields()) {
                        if (fld.name.equals(fa.field)) {
                            fieldDecl = fld;
                            break;
                        }
                    }
                    if (fieldDecl == null) {
                        throw new RuntimeException("Field " + fa.field + " not found in class " + ct.getName());
                    }
                    fieldOffset = fieldDecl.offset;
                    fieldType = fieldDecl.type;
                } else {
                    throw new RuntimeException("Field access on non-struct and non-class type");
                }

                // if field is an array, perform decay conversion
                if (fieldType instanceof ArrayType) {
                    Register arrayPtr = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.ADDIU, arrayPtr, baseAddr, fieldOffset);
                    fa.type = new PointerType(((ArrayType) fieldType).elementType);
                    yield arrayPtr;
                }

                // if field is a struct, return its address
                if (fieldType instanceof StructType) {
                    Register fieldAddr = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.ADDIU, fieldAddr, baseAddr, fieldOffset);
                    yield fieldAddr;
                }

                // otherwise for scalar types load value
                Register result = Register.Virtual.create();
                if (isCharType(fieldType)) {
                    asmProg.getCurrentTextSection().emit(OpCode.LB, result, baseAddr, fieldOffset);
                } else {
                    asmProg.getCurrentTextSection().emit(OpCode.LW, result, baseAddr, fieldOffset);
                }
                fa.type = fieldType;
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
                System.out.println("ExprValCodeGen: AddressOf computed for expression, address = " + address);
                yield address;
            }

            // Assign, evaluate RHS, compute LHS address, then store
            case Assign assign -> {
                if (assign.left.type instanceof StructType) {
                    int structSize = getSize(assign.left.type);
                    Register rhsVal = visit(assign.right);
                    Register lhsAddr = addrGen.visit(assign.left);
                    emitStructCopy(rhsVal, lhsAddr, structSize);
                    System.out.println("Assign: Copied struct of size " + structSize + " from RHS to LHS at " + lhsAddr);
                    yield rhsVal;
                } else {
                    Register rhsVal = visit(assign.right);
                    Register lhsAddr = addrGen.visit(assign.left);
                    if (isCharType(assign.left.type)) {
                        asmProg.getCurrentTextSection().emit(OpCode.SB, rhsVal, lhsAddr, 0);
                    } else {
                        asmProg.getCurrentTextSection().emit(OpCode.SW, rhsVal, lhsAddr, 0);
                    }
                    System.out.println("Assign: Stored value " + rhsVal + " into address " + lhsAddr);
                    yield rhsVal;
                }
            }

            // BinOp, evaluate left & right, apply MIPS op, result
            case BinOp bin -> {
                if (bin.op == Op.AND) {
                    // short-circuit for &&
                    Register leftVal = visit(bin.left);
                    Label falseLabel = Label.create("and_false");
                    Label endLabel = Label.create("and_end");
                    // if left operand false, jump to falseLabel
                    asmProg.getCurrentTextSection().emit(OpCode.BEQZ, leftVal, falseLabel);
                    // left operand true, evaluate right operand
                    Register rightVal = visit(bin.right);
                    asmProg.getCurrentTextSection().emit(OpCode.BEQZ, rightVal, falseLabel);
                    // both operands true, set result to 1
                    Register result = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.LI, result, 1);
                    asmProg.getCurrentTextSection().emit(OpCode.J, endLabel);
                    // false case, set result to 0
                    asmProg.getCurrentTextSection().emit(falseLabel);
                    asmProg.getCurrentTextSection().emit(OpCode.LI, result, 0);
                    asmProg.getCurrentTextSection().emit(endLabel);
                    yield result;
                } else if (bin.op == Op.OR) {
                    // short-circuit for ||
                    Register leftVal = visit(bin.left);
                    Label trueLabel = Label.create("or_true");
                    Label endLabel = Label.create("or_end");
                    // if left operand true, jump to trueLabel
                    asmProg.getCurrentTextSection().emit(OpCode.BNEZ, leftVal, trueLabel);
                    // left operand false, evaluate right operand
                    Register rightVal = visit(bin.right);
                    asmProg.getCurrentTextSection().emit(OpCode.BNEZ, rightVal, trueLabel);
                    // both operands false, set result to 0
                    Register result = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.LI, result, 0);
                    asmProg.getCurrentTextSection().emit(OpCode.J, endLabel);
                    // true case, set result to 1
                    asmProg.getCurrentTextSection().emit(trueLabel);
                    asmProg.getCurrentTextSection().emit(OpCode.LI, result, 1);
                    asmProg.getCurrentTextSection().emit(endLabel);
                    yield result;
                } else {
                    // all other binary ops
                    Register left = visit(bin.left);
                    // save left operand on stack
                    asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -4);
                    asmProg.getCurrentTextSection().emit(OpCode.SW, left, Register.Arch.sp, 0);
                    Register right = visit(bin.right);
                    Register leftTemp = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.LW, leftTemp, Register.Arch.sp, 0);
                    asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, 4);
                    Register rightTemp = Register.Virtual.create();
                    asmProg.getCurrentTextSection().emit(OpCode.ADD, rightTemp, right, Register.Arch.zero);
                    Register result = Register.Virtual.create();
                    switch (bin.op) {
                        case ADD:
                            asmProg.getCurrentTextSection().emit(OpCode.ADD, result, leftTemp, rightTemp);
                            break;
                        case SUB:
                            asmProg.getCurrentTextSection().emit(OpCode.SUB, result, leftTemp, rightTemp);
                            break;
                        case MUL:
                            asmProg.getCurrentTextSection().emit(OpCode.MULT, leftTemp, rightTemp);
                            asmProg.getCurrentTextSection().emit(OpCode.MFLO, result);
                            break;
                        case DIV:
                            asmProg.getCurrentTextSection().emit(OpCode.DIV, leftTemp, rightTemp);
                            asmProg.getCurrentTextSection().emit(OpCode.MFLO, result);
                            break;
                        case MOD:
                            asmProg.getCurrentTextSection().emit(OpCode.DIV, leftTemp, rightTemp);
                            asmProg.getCurrentTextSection().emit(OpCode.MFHI, result);
                            break;
                        case LT:
                            asmProg.getCurrentTextSection().emit(OpCode.SLT, result, leftTemp, rightTemp);
                            break;
                        case GT:
                            asmProg.getCurrentTextSection().emit(OpCode.SLT, result, rightTemp, leftTemp);
                            break;
                        case LE:
                            Register tmpLE = Register.Virtual.create();
                            asmProg.getCurrentTextSection().emit(OpCode.SLT, tmpLE, rightTemp, leftTemp);
                            asmProg.getCurrentTextSection().emit(OpCode.XORI, result, tmpLE, 1);
                            break;
                        case GE:
                            Register tmpGE = Register.Virtual.create();
                            asmProg.getCurrentTextSection().emit(OpCode.SLT, tmpGE, leftTemp, rightTemp);
                            asmProg.getCurrentTextSection().emit(OpCode.XORI, result, tmpGE, 1);
                            break;
                        case EQ:
                            Register tmpEQ1 = Register.Virtual.create();
                            Register tmpEQ2 = Register.Virtual.create();
                            asmProg.getCurrentTextSection().emit(OpCode.SLT, tmpEQ1, leftTemp, rightTemp);
                            asmProg.getCurrentTextSection().emit(OpCode.SLT, tmpEQ2, rightTemp, leftTemp);
                            asmProg.getCurrentTextSection().emit(OpCode.OR, result, tmpEQ1, tmpEQ2);
                            asmProg.getCurrentTextSection().emit(OpCode.XORI, result, result, 1);
                            break;
                        case NE:
                            Register tmpNE1 = Register.Virtual.create();
                            Register tmpNE2 = Register.Virtual.create();
                            asmProg.getCurrentTextSection().emit(OpCode.SLT, tmpNE1, leftTemp, rightTemp);
                            asmProg.getCurrentTextSection().emit(OpCode.SLT, tmpNE2, rightTemp, leftTemp);
                            asmProg.getCurrentTextSection().emit(OpCode.OR, result, tmpNE1, tmpNE2);
                            break;
                        default:
                            throw new UnsupportedOperationException("Unsupported binary operator: " + bin.op);
                    }
                    yield result;
                }
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
                String text = sl.value;
                Label lbl;
                if (emittedStrings.containsKey(text)) {
                    lbl = emittedStrings.get(text);
                } else {
                    String labelName = "str_" + Integer.toUnsignedString(text.hashCode());
                    lbl = Label.get(labelName);
                    emittedStrings.put(text, lbl);
                    String fixed = escapeString(text);
                    asmProg.dataSection.emit(lbl);
                    asmProg.dataSection.emit(new gen.asm.Directive("asciiz \"" + fixed + "\""));
                }
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
                    int totalArgSize = 0;
                    boolean structReturn = isStructReturn(fc);
                    if (structReturn) {
                        // get raw struct size
                        int retSize = getSize(fc.decl.type);
                        // round up to multiple of 4 so we store with LW/SW safely
                        retSize = alignTo(retSize, 4);

                        asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -retSize);

                        // set $a0 to point to that allocated space
                        asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.a0, Register.Arch.sp, Register.Arch.zero);

                        totalArgSize += retSize;
                    }

                    // push explicit args in reverse order
                    for (int i = fc.args.size() - 1; i >= 0; i--) {
                        Expr argExpr = fc.args.get(i);
                        Type argType = argExpr.type;
                        if (argType instanceof StructType) {
                            int structSize = getSize(argType);
                            Register structAddr = new ExprAddrCodeGen(asmProg, astRoot).visit(argExpr);
                            for (int off = structSize - 4; off >= 0; off -= 4) {
                                Register temp = Register.Virtual.create();
                                asmProg.getCurrentTextSection().emit(OpCode.LW, temp, structAddr, off);
                                asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -4);
                                asmProg.getCurrentTextSection().emit(OpCode.SW, temp, Register.Arch.sp, 0);
                                totalArgSize += 4;
                            }
                        } else {
                            Register argVal = new ExprValCodeGen(asmProg, astRoot).visit(argExpr);
                            asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -4);
                            asmProg.getCurrentTextSection().emit(OpCode.SW, argVal, Register.Arch.sp, 0);
                            totalArgSize += 4;
                        }
                    }
                    System.out.println("Total argument space pushed (including hidden return slot if any): " + totalArgSize);
                    asmProg.getCurrentTextSection().emit(OpCode.JAL, Label.get(fc.name));
                    // after returning pop the args
                    asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, totalArgSize);
                    Register result = Register.Virtual.create();
                    if (structReturn) {
                        System.out.println("Struct-returning function '" + fc.name +
                                "' call: retrieving return value from hidden slot in $a0.");
                        // here callee’s epilog should have copied struct into memory
                        // return that address as the result
                        asmProg.getCurrentTextSection().emit(OpCode.ADD, result, Register.Arch.a0, Register.Arch.zero);
                    } else {
                        asmProg.getCurrentTextSection().emit(OpCode.ADD, result, Register.Arch.v0, Register.Arch.zero);
                    }
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

            // new instances, generates code for 'new class C()'
            case NewInstance ni -> {
                // evaluate the NewInstance expression
                // lookup object size for the class
                if (!(ni.classType instanceof ClassType ct)) {
                    throw new RuntimeException("NewInstance: Expected ClassType.");
                }
                int objSize = getClassObjectSize(ct);
                // allocate object memory by calling mcmalloc
                Register sizeReg = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.LI, sizeReg, objSize);
                asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.a0, sizeReg, Register.Arch.zero);
                asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 9); // syscall/mcmalloc code
                asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
                // allocated object address is in v0
                Register objAddr = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, objAddr, Register.Arch.v0, Register.Arch.zero);
                // load vtable pointer for the class (from global data, label "vt_<ClassName>")
                Label vtLabel = Label.get("vt_" + ct.getName());
                Register vtReg = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.LA, vtReg, vtLabel);
                // store the vtable pointer at offset 0 in the object
                asmProg.getCurrentTextSection().emit(OpCode.SW, vtReg, objAddr, 0);
                yield objAddr;
            }

            // instance function call, dynamic dispatch for object method calls
            case InstanceFunCallExpr ifce -> {
                // evaluate the object expression to obtain the object pointer
                Register objReg = new ExprValCodeGen(asmProg, astRoot).visit(ifce.object);

                // evaluate explicit arguments and push them in reverse order
                int totalArgSize = 0;
                for (int i = ifce.args.size() - 1; i >= 0; i--) {
                    Expr argExpr = ifce.args.get(i);
                    Type argType = argExpr.type;
                    if (argType instanceof StructType) {
                        int structSize = getSize(argType);
                        Register structAddr = new ExprAddrCodeGen(asmProg, astRoot).visit(argExpr);
                        for (int off = structSize - 4; off >= 0; off -= 4) {
                            Register temp = Register.Virtual.create();
                            asmProg.getCurrentTextSection().emit(OpCode.LW, temp, structAddr, off);
                            asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -4);
                            asmProg.getCurrentTextSection().emit(OpCode.SW, temp, Register.Arch.sp, 0);
                            totalArgSize += 4;
                        }
                    } else {
                        Register argVal = new ExprValCodeGen(asmProg, astRoot).visit(argExpr);
                        asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -4);
                        asmProg.getCurrentTextSection().emit(OpCode.SW, argVal, Register.Arch.sp, 0);
                        totalArgSize += 4;
                    }
                }

                // push the implicit "this" pointer
                asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -4);
                asmProg.getCurrentTextSection().emit(OpCode.SW, objReg, Register.Arch.sp, 0);
                totalArgSize += 4;

                // load the vtable pointer from the object (assumed to be stored at offset 0)
                Register vtablePtr = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.LW, vtablePtr, objReg, 0);
                // compute the method offset in the vtable
                int offset = getMethodOffset(ifce);
                // load the method address from the vtable
                Register methodAddr = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.LW, methodAddr, vtablePtr, offset);
                asmProg.getCurrentTextSection().emit(OpCode.JALR, methodAddr);
                asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, totalArgSize);
                Register result = Register.Virtual.create();
                asmProg.getCurrentTextSection().emit(OpCode.ADD, result, Register.Arch.v0, Register.Arch.zero);
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
            if (decl == null) {
                throw new RuntimeException("Unknown struct " + st.name);
            }
            return computeStructSize(decl);
        } else if (type instanceof ClassType) {
            // for any class type, the sizeof operator should return the pointer size (4 bytes)
            return 4;
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
                if (align > maxAlign) {
                    maxAlign = align;
                }
            }
        }
        maxAlign = Math.max(4, maxAlign);
        offset = alignTo(offset, maxAlign);
        return offset;
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
            maxAlign = Math.max(4, maxAlign);
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

    private void emitStructCopy(Register src, Register dest, int size) {
        // copy word by word
        int words = size / 4;
        int remainder = size % 4;
        for (int i = 0; i < words; i++) {
            Register temp = Register.Virtual.create();
            asmProg.getCurrentTextSection().emit(OpCode.LW, temp, src, i * 4);
            asmProg.getCurrentTextSection().emit(OpCode.SW, temp, dest, i * 4);
        }
        // if struct size is not a multiple of 4 copy remaining bytes
        for (int i = words * 4; i < size; i++) {
            Register temp = Register.Virtual.create();
            asmProg.getCurrentTextSection().emit(OpCode.LB, temp, src, i);
            asmProg.getCurrentTextSection().emit(OpCode.SB, temp, dest, i);
        }
    }

    // determine if function returns a struct
    private boolean isStructReturn(FunCallExpr fc) {
        if (fc.decl == null) return false;
        Type retType;
        if (fc.decl instanceof FunDecl fd) {
            retType = fd.type;
        } else if (fc.decl instanceof FunDef fd) {
            retType = fd.type;
        } else {
            return false;
        }
        return retType instanceof StructType;
    }

    // get return type from a func decl
    private Type getReturnType(FunCallExpr fc) {
        if (fc.decl instanceof FunDecl fd) {
            return fd.type;
        } else if (fc.decl instanceof FunDef fd) {
            return fd.type;
        }
        return BaseType.UNKNOWN;
    }

    private int getFieldOffset(Type structType, String fieldName) {
        if (structType instanceof StructType st) {
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
        } else {
            throw new RuntimeException("getFieldOffset called on non-struct type");
        }
    }

    private int getSizeOfArrayElement(Type arrayType) {
        if (arrayType instanceof ArrayType at) {
            return getSize(at.elementType);
        } else if (arrayType instanceof PointerType pt) {
            return getSize(pt.base);
        }
        throw new RuntimeException("getSizeOfArrayElement called on non-array type");
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

    // compute the size of a class object
    // for simplicity, allocate 4 bytes for the vtable pointer plus 4 bytes per field
    private int getClassObjectSize(ClassType ct) {
        ClassDecl cd = lookupClassDecl(ct.getName());
        if (cd == null) return 4;
        return 4 + cd.getFields().size() * 4;
    }

    // compute the method offset in the vtable for an instance function call
    // each method is assumed to occupy 4 bytes in the vtable
    private int getMethodOffset(InstanceFunCallExpr ifce) {
        if (!(ifce.object.type instanceof ClassType ct)) {
            throw new RuntimeException("Instance method call on non-class type");
        }
        ClassDecl cd = lookupClassDecl(ct.getName());
        if (cd == null) {
            throw new RuntimeException("Class " + ct.getName() + " not found");
        }
        List<FunDef> methods = new ArrayList<>();
        collectVTableMethods(cd, methods);
        for (int i = 0; i < methods.size(); i++) {
            if (methods.get(i).name.equals(ifce.methodName)) {
                return i * 4;
            }
        }
        throw new RuntimeException("Method " + ifce.methodName + " not found in class " + ct.getName());
    }

    // recursively collect vtable methods in order (inherited first, then overridden/own)
    private void collectVTableMethods(ClassDecl cd, List<FunDef> methods) {
        if (cd.getParentName() != null) {
            ClassDecl parent = lookupClassDecl(cd.getParentName());
            if (parent != null) {
                collectVTableMethods(parent, methods);
            }
        }
        for (FunDef fd : cd.getMethods()) {
            boolean found = false;
            for (int i = 0; i < methods.size(); i++) {
                if (methods.get(i).name.equals(fd.name)) {
                    methods.set(i, fd);
                    found = true;
                    break;
                }
            }
            if (!found) {
                methods.add(fd);
            }
        }
    }
}
