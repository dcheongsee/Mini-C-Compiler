package sem;

import ast.*;

public class TypeAnalyzer extends BaseSemanticAnalyzer {

	public Type visit(ASTNode node) {
		return switch(node) {
			case null -> {
				throw new IllegalStateException("Unexpected null value");
			}

			case Block b -> {
				// block is a stmt, so it doesn't produce an expr type
				for (VarDecl vd : b.vds) {
					visit(vd);
				}
				for (Stmt s : b.stmts) {
					visit(s);
				}
				yield BaseType.NONE;
			}

			case FunDecl fd -> {
				// check func return type
				visit(fd.type);
				for (VarDecl param : fd.params) {
					visit(param);
				}

				yield fd.type;
			}

			case FunDef fd -> {
				// also visit the func body
				visit(fd.type);
				for (VarDecl param : fd.params) {
					visit(param);
				}
				visit(fd.block);
				yield fd.type;
			}

			case Program p -> {
				// visit top-level decl, ignore return types for stmts
				for (Decl d : p.decls) {
					visit(d);
				}
				yield BaseType.NONE;
			}

			case VarDecl vd -> {
				// var decl not void
				Type declaredType = visit(vd.type);
				if (declaredType == BaseType.VOID) {
					error("Cannot declare variable of type void: " + vd.name);
				}

				yield declaredType;
			}

			case VarExpr v -> {
				// if name analysis linked vd, use that type, else unknown
				if (v.vd != null) {
					v.type = visit(v.vd.type);
				} else {
					v.type = BaseType.UNKNOWN;
					error("VarExpr " + v.name + " has no linked VarDecl.");
				}
				yield v.type;
			}

			case StructTypeDecl std -> {
				// visit each field's decl
				for (Decl field : std.getFields()) {
					visit(field);
				}

				yield BaseType.NONE;
			}

			case Type t -> {
				yield t;
			}

			default -> {
				// visit its children and yield none
				for (ASTNode child : node.children()) {
					visit(child);
				}
				yield BaseType.NONE;
			}

		};

	}


}
