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
				// type check the declared type
				Type t = visit(vd.type);
				if (t == BaseType.VOID) {
					error("Variable " + vd.name + " cannot be declared void.");
				}
				yield t;
			}
			case VarExpr v -> {
				if (v.vd == null) {
					error("Variable " + v.name + " is not linked to any declaration.");
					yield BaseType.UNKNOWN;
				} else {
					yield visit(v.vd);
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
				if (leftType.equals(BaseType.INT) && rightType.equals(BaseType.INT)) {
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
					yield pt.base;
				} else {
					error("ValueAtExpr applied to non-pointer type.");
					yield BaseType.UNKNOWN;
				}
			}
			case ArrayAccessExpr aa -> {
				Type arrayType = visit(aa.array);
				Type indexType = visit(aa.index);
				if (!indexType.equals(BaseType.INT)) {
					error("Array index is not an int.");
					yield BaseType.UNKNOWN;
				}
				if (arrayType instanceof ArrayType at) {
					yield at.elementType;
				} else {
					error("Array access on a non-array type.");
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
					if (formal instanceof ArrayType atFormal && actual instanceof ArrayType atActual) {
						if (atFormal.length != atActual.length) {
							error("Array size mismatch for parameter " + formals.get(i).name +
									" in function " + fc.name + ": expected " + atFormal.length +
									", got " + atActual.length + ".");
						}
					} else if (!formal.equals(actual)) {
						error("Type mismatch for parameter " + formals.get(i).name +
								" in function " + fc.name + ".");
					}
				}
				if (fc.decl instanceof FunDecl fd) yield fd.type;
				else if (fc.decl instanceof FunDef fd) yield fd.type;
				else yield BaseType.UNKNOWN;
			}

			case TypecastExpr tc -> {
				yield visit(tc.castType);
			}

			case Assign assign -> {
				Type leftType = visit(assign.left);
				Type rightType = visit(assign.right);
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
						yield BaseType.UNKNOWN;
					} else {
						yield fieldType;
					}
				} else {
					error("Field access on non-struct type.");
					yield BaseType.UNKNOWN;
				}
			}

			case IntLiteral il -> BaseType.INT;
			case ChrLiteral cl -> BaseType.CHAR;
			case StrLiteral sl -> new ArrayType(BaseType.CHAR, sl.value.length() + 1);

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