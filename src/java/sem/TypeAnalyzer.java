package sem;

import ast.*;
import java.util.List;
import java.util.Objects;

public class TypeAnalyzer extends BaseSemanticAnalyzer {

	private List<Decl> globalDecls;

	public Type visit(ASTNode node) {
		return switch (node) {
			case null -> {
				throw new IllegalStateException("Unexpected null value");
			}

			case ClassType ct -> {
				// Just yield itself, no extra checks unless you want to confirm existence here
				yield ct;
			}

			case Type t -> {
				// base case: array, base type, pointer, struct, etc
				yield t;
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
			case BinOp bin -> {
				Type leftType = visit(bin.left);
				Type rightType = visit(bin.right);

				// if doing equality or inequality, allow implicit conversion of int lit 0 to pointer
				if (bin.op == Op.EQ || bin.op == Op.NE) {
					if (leftType instanceof PointerType ptL && bin.right instanceof IntLiteral il && il.value == 0) {
						rightType = leftType;
					} else if (rightType instanceof PointerType ptR && bin.left instanceof IntLiteral il && il.value == 0) {
						leftType = rightType;
					}

					// part V: allow eq/neq on class types then pointer comparison
					if (leftType instanceof ClassType && rightType instanceof ClassType) {
						yield BaseType.INT;
					}
				}

				// normal integer binop
				if ((bin.op == Op.EQ || bin.op == Op.NE)
						&& leftType.equals(rightType)
						&& !leftType.equals(BaseType.VOID)) {
					yield BaseType.INT;
				} else if (leftType.equals(BaseType.INT) && rightType.equals(BaseType.INT)) {
					yield BaseType.INT;
				} else {
					error("BinOp operands are invalid. Left: " + leftType + ", Right: " + rightType);
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
				// size of a class is the size of a pointer then just treat it as int result
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
					// allow implicit conversion, if formal is pointer and actual is array
					if (formal instanceof PointerType pt && actual instanceof ArrayType at) {
						if (at.elementType.equals(pt.base)) {
							continue;
						}
					}
					if (!typeEqualsOrSubType(actual, formal)) {
						error("Type mismatch for parameter " + formals.get(i).name +
								" in function " + fc.name + ". Expected " + formal + " got " + actual);
					}
				}
				if (fc.decl instanceof FunDecl fd) yield fd.type;
				else if (fc.decl instanceof FunDef fd) yield fd.type;
				else yield BaseType.UNKNOWN;
			}
			case TypecastExpr tc -> {
				// handle class subtyping cast then upcasting only
				Type innerType = visit(tc.expr);
				tc.type = visit(tc.castType);

				// if both are class types, check subtyping
				if (tc.type instanceof ClassType ct2 && innerType instanceof ClassType ct1) {
					// must have ct1 <: ct2
					if (!classSubTypeOf(ct1, ct2)) {
						error("Invalid cast from " + ct1 + " to " + ct2 + ": Not a valid upcast.");
						tc.type = BaseType.UNKNOWN;
					}
				}
				yield tc.type;
			}
			case Assign assign -> {
				Type leftType = visit(assign.left);
				Type rightType = visit(assign.right);

				// check if left side is a pointer and right side is an int literal 0
				if (leftType instanceof PointerType && assign.right instanceof IntLiteral il && il.value == 0) {
					rightType = leftType;
				}

				// for class types, require exact type equality, implicit upcasting is disallowed
				if (leftType instanceof ClassType cl && rightType instanceof ClassType cr) {
					if (!cl.getName().equals(cr.getName())) {
						error("Assignment type mismatch: cannot implicitly assign " + cr + " to " + cl + ". A cast is required.");
						yield BaseType.UNKNOWN;
					} else {
						yield leftType;
					}
				} else if (!leftType.equals(rightType)) {
					error("Assignment type mismatch: " + leftType + " does not match " + rightType);
					yield BaseType.UNKNOWN;
				} else {
					yield leftType;
				}
			}


			case FieldAccessExpr fa -> {
				// handle if fa.expr is a ClassType then we must do an inheritance-based field lookup
				Type containerType = visit(fa.expr);
				if (containerType instanceof StructType st) {
					StructTypeDecl decl = lookupStructDecl(st.name);
					if (decl == null) {
						error("No declaration found for struct " + st.name);
						fa.type = BaseType.UNKNOWN;
						yield BaseType.UNKNOWN;
					}
					Type fieldType = findFieldTypeInStruct(decl, fa.field);
					if (fieldType == null) {
						error("Unknown field " + fa.field + " in struct " + st.name);
						fa.type = BaseType.UNKNOWN;
						yield BaseType.UNKNOWN;
					} else {
						fa.type = fieldType;
						yield fieldType;
					}
				} else if (containerType instanceof ClassType ct) {
					// find field in class or ancestor
					Type fieldT = findFieldTypeInClass(ct, fa.field);
					if (fieldT == null) {
						error("Unknown field '" + fa.field + "' in class " + ct.getName() + " or its ancestors.");
						fa.type = BaseType.UNKNOWN;
						yield BaseType.UNKNOWN;
					} else {
						fa.type = fieldT;
						yield fieldT;
					}
				} else {
					error("Field access on neither a struct nor a class.");
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


			case ClassDecl cd -> {
				// for each field, must not be void
				// also, ensure no overshadowing from ancestors
				if (cd.getFields() != null) {
					for (VarDecl field : cd.getFields()) {
						Type ft = visit(field);
						if (ft == BaseType.VOID) {
							error("Field " + field.name + " in class " + cd.getName() + " cannot be void.");
						}
						// check overshadowing, if an ancestor has same field then error
						if (fieldExistsInAncestors(cd.getParentName(), field.name)) {
							error("Field " + field.name + " in class " + cd.getName() + " illegally overrides ancestor field.");
						}
					}
				}
				// for each method, if it matches a parent method name then check overriding
				if (cd.getMethods() != null) {
					for (FunDef method : cd.getMethods()) {
						// visit to typecheck the method’s body etc
						visit(method);
						FunDef ancestorMethod = findMethodInAncestors(cd.getParentName(), method.name);
						if (ancestorMethod != null) {
							// check override signature then same return type, same param types
							if (!sameSignature(method, ancestorMethod)) {
								error("Method " + method.name + " in class " + cd.getName()
										+ " does not properly override ancestor method signature.");
							}
						}
					}
				}
				yield BaseType.NONE; // class decl itself has no "type"
			}

			case NewInstance ni -> {
				// evaluate to the class type
				ClassType ctype = ni.classType;
				// confirm that the class is known
				if (lookupClassDecl(ctype.getName()) == null) {
					error("Cannot instantiate unknown class " + ctype.getName());
					yield BaseType.UNKNOWN;
				} else {
					ni.type = ctype;
					yield ctype;
				}
			}

			case InstanceFunCallExpr ifce -> {
				// first get the object's type
				Type objType = visit(ifce.object);
				if (!(objType instanceof ClassType ct)) {
					error("Instance method call on non-class type: " + objType);
					yield BaseType.UNKNOWN;
				} else {
					// find method in the class or ancestors
					FunDef methodDecl = findMethodInClassOrAncestors(ct, ifce.methodName);
					if (methodDecl == null) {
						error("No method named " + ifce.methodName + " found in class " + ct.getName() + " or its ancestors.");
						yield BaseType.UNKNOWN;
					} else {
						// check argument count & types
						if (methodDecl.params.size() != ifce.args.size()) {
							error("Instance method " + ifce.methodName + " called with wrong number of arguments.");
							yield BaseType.UNKNOWN;
						} else {
							for (int i = 0; i < methodDecl.params.size(); i++) {
								Type formal = visit(methodDecl.params.get(i));
								Type actual = visit(ifce.args.get(i));
								if (!typeEqualsOrSubType(actual, formal)) {
									error("Argument type mismatch for parameter " + i + " in method " + ifce.methodName
											+ ". Expected " + formal + " got " + actual);
								}
							}
						}
						// the call’s type is the method’s return type
						yield methodDecl.type;
					}
				}
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

	// find the ClassDecl for className if it exists
	private ClassDecl lookupClassDecl(String className) {
		if (globalDecls == null) return null;
		for (Decl d : globalDecls) {
			if (d instanceof ClassDecl cd && cd.getName().equals(className)) {
				return cd;
			}
		}
		return null;
	}

	// check if child <: parent for class types
	private boolean classSubTypeOf(ClassType child, ClassType parent) {
		if (child.getName().equals(parent.getName())) {
			return true;
		}
		ClassDecl cd = lookupClassDecl(child.getName());
		if (cd == null) {
			// unknown class => can't confirm
			return false;
		}
		// if no parent => not a subtype
		if (cd.getParentName() == null) {
			return false;
		}
		// otherwise see if parent's name is the parent's
		if (cd.getParentName().equals(parent.getName())) {
			return true;
		}
		// else continue walking up
		ClassDecl pDecl = lookupClassDecl(cd.getParentName());
		if (pDecl == null) {
			return false;
		}
		return classSubTypeOf(new ClassType(pDecl.getName()), parent);
	}

	// general equality check or subtype check
	private boolean typeEqualsOrSubType(Type actual, Type formal) {
		// if they’re identical, fine
		if (Objects.equals(actual, formal)) {
			return true;
		}
		// if both are class types then check subtyping
		if (actual instanceof ClassType ca && formal instanceof ClassType cf) {
			return classSubTypeOf(ca, cf);
		}
		return false;
	}

	// find a field in a struct
	private Type findFieldTypeInStruct(StructTypeDecl decl, String fieldName) {
		for (ASTNode child : decl.children()) {
			if (child instanceof VarDecl vd && vd.name.equals(fieldName)) {
				return visit(vd);
			}
		}
		return null;
	}

	// find field in a class or its ancestors
	private Type findFieldTypeInClass(ClassType ct, String fieldName) {
		ClassDecl cd = lookupClassDecl(ct.getName());
		if (cd == null) return null;
		// check local fields
		for (VarDecl field : cd.getFields()) {
			if (field.name.equals(fieldName)) {
				return visit(field);
			}
		}
		// else check parent
		if (cd.getParentName() != null) {
			return findFieldTypeInClass(new ClassType(cd.getParentName()), fieldName);
		}
		return null;
	}

	// checks if a field already exists in ancestors then overshadowing
	private boolean fieldExistsInAncestors(String parentName, String fieldName) {
		if (parentName == null) return false;
		ClassDecl parentDecl = lookupClassDecl(parentName);
		if (parentDecl == null) return false;
		// check parent’s fields
		for (VarDecl field : parentDecl.getFields()) {
			if (field.name.equals(fieldName)) {
				return true;
			}
		}
		// recurse up
		if (parentDecl.getParentName() == null) return false;
		return fieldExistsInAncestors(parentDecl.getParentName(), fieldName);
	}


	private FunDef findMethodInAncestors(String parentName, String methodName) {
		if (parentName == null) return null;
		ClassDecl parentDecl = lookupClassDecl(parentName);
		if (parentDecl == null) return null;
		// check parent's methods
		for (FunDef method : parentDecl.getMethods()) {
			if (method.name.equals(methodName)) {
				return method;
			}
		}
		// keep going up
		return findMethodInAncestors(parentDecl.getParentName(), methodName);
	}

	private FunDef findMethodInClassOrAncestors(ClassType ct, String methodName) {
		ClassDecl cd = lookupClassDecl(ct.getName());
		if (cd == null) return null;
		// check local methods
		for (FunDef md : cd.getMethods()) {
			if (md.name.equals(methodName)) {
				return md;
			}
		}
		// else check parent
		if (cd.getParentName() != null) {
			return findMethodInClassOrAncestors(new ClassType(cd.getParentName()), methodName);
		}
		return null;
	}


	private boolean sameSignature(FunDef method, FunDef ancestorMethod) {
		// check return types
		if (!Objects.equals(method.type, ancestorMethod.type)) {
			return false;
		}
		// check param counts
		if (method.params.size() != ancestorMethod.params.size()) {
			return false;
		}
		// check param types pairwise
		for (int i = 0; i < method.params.size(); i++) {
			Type mp = visit(method.params.get(i));
			Type ap = visit(ancestorMethod.params.get(i));
			if (!Objects.equals(mp, ap)) {
				return false;
			}
		}
		return true;
	}
}
