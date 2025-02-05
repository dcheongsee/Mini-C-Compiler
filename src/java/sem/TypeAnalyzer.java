package sem;

import ast.*;

public class TypeAnalyzer extends BaseSemanticAnalyzer {

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
				// visit all decl in the program
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
				// visit each field
				for (ASTNode child : std.children()) {
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
			default -> {
				for (ASTNode child : node.children()) {
					visit(child);
				}
				yield BaseType.UNKNOWN;
			}
		};
	}

}