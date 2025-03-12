package sem;

import ast.*;
import java.util.List;

public class TypeAnalyzer extends BaseSemanticAnalyzer {

	private List<Decl> globalDecls;

	public Type visit(ASTNode node) {
		return switch (node) {
			case null -> {
				throw new IllegalStateException("Unexpected null value");
			}
			case Block b -> {
				// visit each child of the block
				for (ASTNode c : b.children()) {
					visit(c);
				}
				yield BaseType.NONE;
			}
			case FunDecl fd -> {
				// visit its params
				for (VarDecl param : fd.params) {
					visit(param);
				}
				// no body to check
				yield fd.type;
			}
			case FunDef fd -> {
				// visit params
				for (VarDecl param : fd.params) {
					visit(param);
				}
				// visit body
				Type bodyType = visit(fd.block);
				yield fd.type;
			}
			case Program p -> {
				// record global declarations for later lookup
				this.globalDecls = p.decls;
				for (Decl d : p.decls) {
					visit(d);
				}
				yield BaseType.NONE;
			}
			case VarDecl vd -> {
				Type t = visit(vd.type);
				if (t == BaseType.VOID) {
					error("Variable " + vd.name + " cannot be declared void.");
				}
				vd.type = t;
				yield t;
			}
			case VarExpr v -> {
				if (v.vd == null) {
					error("Variable " + v.name + " is not linked to any declaration.");
					v.type = BaseType.UNKNOWN;
					yield BaseType.UNKNOWN;
				} else {
					Type t = visit(v.vd);
					v.type = t;
					yield t;
				}
			}
			case StructTypeDecl std -> {
				for (ASTNode child : std.children()) {
					if (!(child instanceof VarDecl)) continue;
					VarDecl field = (VarDecl) child;
					Type fType = visit(field);
					if (fType == BaseType.VOID) {
						error("Field " + field.name + " in struct " + std.getName() + " cannot be void.");
					}
				}
				yield new StructType(std.getName());
			}
			case Type t -> {
				yield t;
			}
			case BinOp bin -> {
				Type leftType = visit(bin.left);
				Type rightType = visit(bin.right);

				// if doing equality or inequality, allow implicit conversion of int lit 0 to pointer
				if (bin.op == Op.EQ || bin.op == Op.NE) {
					if (leftType instanceof PointerType && bin.right instanceof IntLiteral) {
						IntLiteral il = (IntLiteral) bin.right;
						if (il.value == 0) {
							rightType = leftType;
						}
					} else if (rightType instanceof PointerType && bin.left instanceof IntLiteral) {
						IntLiteral il = (IntLiteral) bin.left;
						if (il.value == 0) {
							leftType = rightType;
						}
					}
				}

				if ((bin.op == Op.EQ || bin.op == Op.NE) && leftType.equals(rightType)
						&& !leftType.equals(BaseType.VOID)) {
					yield BaseType.INT;
				} else if (leftType.equals(BaseType.INT) && rightType.equals(BaseType.INT)) {
					yield BaseType.INT;
				} else {
					error("BinOp operands are not both int. Left: " + leftType + ", Right: " + rightType);
					yield BaseType.UNKNOWN;
				}
			}

			case AddressOfExpr ao -> {
				yield new PointerType(visit(ao.expr));
			}
			case ValueAtExpr va -> {
				Type t = visit(va.expr);
				if (t instanceof PointerType pt) {
					va.type = pt.base;
					yield pt.base;
				} else {
					error("ValueAtExpr applied to non-pointer type.");
					va.type = BaseType.UNKNOWN;
					yield BaseType.UNKNOWN;
				}
			}

			case ArrayAccessExpr aa -> {
				Type arrayType = visit(aa.array);
				Type indexType = visit(aa.index);
				if (!indexType.equals(BaseType.INT)) {
					error("Array index is not an int.");
					aa.type = BaseType.UNKNOWN;
					yield BaseType.UNKNOWN;
				}
				if (arrayType instanceof ArrayType at) {
					aa.type = at.elementType;
					yield at.elementType;
				} else if (arrayType instanceof PointerType pt) {
					aa.type = pt.base;
					yield pt.base;
				} else {
					error("Array access on a non-array type.");
					aa.type = BaseType.UNKNOWN;
					yield BaseType.UNKNOWN;
				}
			}
			case SizeOfExpr so -> {
				yield BaseType.INT;
			}
			case FunCallExpr fc -> {
				if (fc.decl == null) {
					error("Function " + fc.name + " is not declared.");
					yield BaseType.UNKNOWN;
				}
				List<VarDecl> formals;
				if (fc.decl instanceof FunDecl fd) {
					formals = fd.params;
				} else if (fc.decl instanceof FunDef fd) {
					formals = fd.params;
				} else {
					yield BaseType.UNKNOWN;
				}
				if (formals.size() != fc.args.size()) {
					error("Function " + fc.name + " called with incorrect number of arguments.");
					yield BaseType.UNKNOWN;
				}
				for (int i = 0; i < formals.size(); i++) {
					Type formal = visit(formals.get(i));
					Type actual = visit(fc.args.get(i));
					// allow implicit conversion: if formal is pointer and actual is array,
					// then they match if the array's element type equals the pointer's base
					if (formal instanceof PointerType pt && actual instanceof ArrayType at) {
						if (at.elementType.equals(pt.base)) {
							continue;
						}
					}
					if (!formal.equals(actual)) {
						error("Type mismatch for parameter " + formals.get(i).name +
								" in function " + fc.name + ".");
					}
				}
				if (fc.decl instanceof FunDecl fd) yield fd.type;
				else if (fc.decl instanceof FunDef fd) yield fd.type;
				else yield BaseType.UNKNOWN;
			}

			case TypecastExpr tc -> {
				Type innerType = visit(tc.expr);
				tc.type = tc.castType;
				yield tc.type;
			}

			case Assign assign -> {
				Type leftType = visit(assign.left);
				Type rightType = visit(assign.right);

				// check if left side is a pointer and right side is an int literal 0
				if (leftType instanceof PointerType && assign.right instanceof IntLiteral) {
					IntLiteral il = (IntLiteral) assign.right;
					if (il.value == 0) {
						// implicitly convert 0 to pointer type
						rightType = leftType;
					}
				}

				if (!leftType.equals(rightType)) {
					error("Assignment type mismatch: " + leftType + " does not match " + rightType);
					yield BaseType.UNKNOWN;
				} else {
					yield leftType;
				}
			}


			case FieldAccessExpr fa -> {
				// get type of the expr
				Type structType = visit(fa.expr);
				if (structType instanceof StructType st) {
					// lookup struct decl using the global decl
					StructTypeDecl decl = lookupStructDecl(st.name);
					if (decl == null) {
						error("No declaration found for struct " + st.name);
						fa.type = BaseType.UNKNOWN;
						yield BaseType.UNKNOWN;
					}
					Type fieldType = null;
					for (ASTNode child : decl.children()) {
						if (child instanceof VarDecl field && field.name.equals(fa.field)) {
							fieldType = visit(field);
							break;
						}
					}
					if (fieldType == null) {
						error("Unknown field " + fa.field + " in struct " + st.name);
						fa.type = BaseType.UNKNOWN;
						yield BaseType.UNKNOWN;
					} else {
						fa.type = fieldType;
						yield fieldType;
					}
				} else {
					error("Field access on non-struct type.");
					fa.type = BaseType.UNKNOWN;
					yield BaseType.UNKNOWN;
				}
			}


			case IntLiteral il -> {
				il.type = BaseType.INT;
				yield BaseType.INT;
			}

			case ChrLiteral cl -> {
				cl.type = BaseType.CHAR;
				yield BaseType.CHAR;
			}

			case StrLiteral sl -> {
				ArrayType t = new ArrayType(BaseType.CHAR, sl.value.length() + 1);
				sl.type = t;
				yield t;
			}

			default -> {
				for (ASTNode child : node.children()) {
					visit(child);
				}
				yield BaseType.UNKNOWN;
			}
		};
	}

	private StructTypeDecl lookupStructDecl(String structName) {
		if (globalDecls != null) {
			for (Decl d : globalDecls) {
				if (d instanceof StructTypeDecl std && std.getName().equals(structName)) {
					return std;
				}
			}
		}
		return null;
	}

}