package sem;

import ast.*;


public class NameAnalyzer extends BaseSemanticAnalyzer {
	private Scope currentScope;

	private static class VarSymbol extends Symbol {
		public final VarDecl decl;
		public VarSymbol(String name, VarDecl decl) {
			super(name);
			this.decl = decl;
		}
	}

	private static class FunSymbol extends Symbol {
		public final Decl decl;
		public FunSymbol(String name, Decl decl) {
			super(name);
			this.decl = decl;
		}
	}

	public void visit(ASTNode node) {
		switch(node) {
			case null -> {
				throw new IllegalStateException("Unexpected null value");
			}

			case Block b -> {
				Scope saved = currentScope;
				currentScope = new Scope(currentScope);

				for (VarDecl vd : b.vds) {     // process var decl
					visit(vd);
				}

				for (Stmt s : b.stmts) {     // process stmts
					visit(s);
				}

				currentScope = saved;
			}

			case FunDecl fd -> {
				// check duplicate func decl
				if (currentScope.lookupCurrent(fd.name) != null) {
					error("Function " + fd.name + " is already declared in this scope.");
				} else {
					currentScope.put(new FunSymbol(fd.name, fd));
				}
				// process params in new inner scope
				Scope saved = currentScope;
				currentScope = new Scope(currentScope);
				for (VarDecl param : fd.params) {
					visit(param);
				}
				currentScope = saved;
			}

			case FunDef fd -> {
				// check duplicate func def
				if (currentScope.lookupCurrent(fd.name) != null) {
					error("Function " + fd.name + " is already declared in this scope.");
				} else {
					currentScope.put(new FunSymbol(fd.name, fd));
				}

				Scope saved = currentScope;
				currentScope = new Scope(currentScope);
				for (VarDecl param : fd.params) {
					visit(param);
				}
				visit(fd.block);
				currentScope = saved;
			}

			case Program p -> {
				currentScope = new Scope();     // create global scope
				// visit each top-level decl
				for (Decl d : p.decls) {
					visit(d);
				}
			}

			case VarDecl vd -> {
				// check duplicate var decl
				if (currentScope.lookupCurrent(vd.name) != null) {
					error("Variable " + vd.name + " is already declared in this scope.");
				} else {
					currentScope.put(new VarSymbol(vd.name, vd));
				}
			}

			case VarExpr v -> {
				// look up var in scope chain
				Symbol sym = currentScope.lookup(v.name);
				if (sym == null) {
					error("Variable " + v.name + " is not declared.");
				} else if (sym instanceof VarSymbol vs) {
					v.vd = vs.decl;
				} else {
					error("Identifier " + v.name + " is not a variable.");
				}
			}

			case StructTypeDecl std -> {
				Scope structScope = new Scope();
				for (Decl field : std.getFields()) {
					if (structScope.lookupCurrent(field.name) != null) {
						error("Field " + field.name + " is already declared in struct " + std.getName());
					} else {
						// assume all fields are VarDecls
						structScope.put(new VarSymbol(field.name, (VarDecl) field));
					}
				}
				// add the struct type itself to current scope
				if (currentScope.lookupCurrent(std.getName()) != null) {
					error("Struct " + std.getName() + " is already declared in this scope.");
				} else {
					currentScope.put(new FunSymbol(std.getName(), std));
				}
			}

			case Type t -> {}

			default -> {
				for (ASTNode child : node.children()) {
					visit(child);
				}
			}

        };

	}




}
