package sem;

import ast.*;

public class NameAnalyzer extends BaseSemanticAnalyzer {

	private Scope currentScope = new Scope();

	public void visit(ASTNode node) {
		switch (node) {
			case null -> {
				throw new IllegalStateException("Unexpected null value");
			}

			case Block b -> {
				// create new inner scope for block
				Scope previous = currentScope;
				currentScope = new Scope(previous);
				for (ASTNode child : b.children()) {
					visit(child);
				}
				currentScope = previous;
			}

			case FunDecl fd -> {
				// check duplicate func decl in current scope
				if (currentScope.lookupCurrent(fd.name) != null) {
					error("Function declaration " + fd.name + " is already declared in this scope.");
				} else {
					currentScope.put(new FunctionSymbol(fd.name, fd));
				}

				Scope prev = currentScope;
				currentScope = new Scope(prev);
				for (VarDecl param : fd.params) {
					visit(param);
				}
				currentScope = prev;
			}

			case FunDef fd -> {
				// check duplicate func def in current scope
				if (currentScope.lookupCurrent(fd.name) != null) {
					error("Function definition " + fd.name + " is already declared in this scope.");
				} else {
					currentScope.put(new FunctionSymbol(fd.name, fd));
				}

				Scope prev = currentScope;
				currentScope = new Scope(prev);
				for (VarDecl param : fd.params) {
					visit(param);
				}
				visit(fd.block);
				currentScope = prev;
			}

			case Program p -> {
				// process built‐in functions
				for (Decl d : p.decls) {
					if (isBuiltin(d)) {
						visit(d);
					}
				}
				// process all other declarations
				for (Decl d : p.decls) {
					if (!isBuiltin(d)) {
						visit(d);
					}
				}
			}

			case VarDecl vd -> {
				// check duplicate var decl in current scope
				if (currentScope.lookupCurrent(vd.name) != null) {
					error("Variable " + vd.name + " is already declared in this scope.");
				} else {
					currentScope.put(new VariableSymbol(vd.name, vd));
				}
			}

			case VarExpr v -> {
				// lookup var in current scope
				Symbol sym = currentScope.lookup(v.name);
				if (sym == null) {
					error("Variable " + v.name + " is not declared.");
				} else {
					if (sym instanceof VariableSymbol vs) {
						// link VarExpr with its corresponding VarDecl
						v.vd = vs.decl;
					} else {
						error("Identifier " + v.name + " does not refer to a variable.");
					}
				}
			}

			case StructTypeDecl std -> {
				// check duplicate struct type decl in current scope
				if (currentScope.lookupCurrent(std.getName()) != null) {
					error("Struct type " + std.getName() + " is already declared in this scope.");
				} else {
					currentScope.put(new StructSymbol(std.getName(), std));
				}
				Scope prev = currentScope;
				currentScope = new Scope(prev);
				// iterate over the actual fields
				for (Decl fieldDecl : std.getFields()) {
					VarDecl field = (VarDecl) fieldDecl;
					if (currentScope.lookupCurrent(field.name) != null) {
						error("Field " + field.name + " is already declared in struct " + std.getName());
					} else {
						currentScope.put(new VariableSymbol(field.name, field));
					}
				}
				currentScope = prev;
			}

			case FunCallExpr fc -> {
				Symbol sym = currentScope.lookup(fc.name);
				if (sym == null) {
					error("Function " + fc.name + " is not declared.");
				} else if (sym instanceof FunctionSymbol fs) {
					fc.decl = fs.decl;
				} else {
					error("Identifier " + fc.name + " does not refer to a function.");
				}
				// visit each arg
				for (Expr arg : fc.args) {
					visit(arg);
				}
			}

			case Type t -> {
				// nothing to do
			}

			default -> {
				for (ASTNode child : node.children()) {
					visit(child);
				}
			}
		}
	}

	// Inner classes
	static class VariableSymbol extends Symbol {
		public VarDecl decl;

		public VariableSymbol(String name, VarDecl decl) {
			super(name);
			this.decl = decl;
		}
	}

	static class FunctionSymbol extends Symbol {
		// is either a func decl or def
		public Decl decl;

		public FunctionSymbol(String name, Decl decl) {
			super(name);
			this.decl = decl;
		}
	}

	static class StructSymbol extends Symbol {
		public StructTypeDecl decl;

		public StructSymbol(String name, StructTypeDecl decl) {
			super(name);
			this.decl = decl;
		}
	}

	private boolean isBuiltin(Decl d) {
		if (d instanceof FunDecl fd) {
			String name = fd.name;
			return name.equals("read_i") ||
					name.equals("print_c") ||
					name.equals("read_c") ||
					name.equals("mcmalloc") ||
					name.equals("print_s") ||
					name.equals("print_i");
		}
		return false;
	}
}