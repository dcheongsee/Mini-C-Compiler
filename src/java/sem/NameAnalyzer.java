package sem;

import ast.*;

import java.util.ArrayDeque;
import java.util.Deque;

public class NameAnalyzer extends BaseSemanticAnalyzer {

	private Scope currentScope = new Scope();
	// stack to track the current enclosing class
	private Deque<ClassDecl> classStack = new ArrayDeque<>();

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
				Symbol existingSym = currentScope.lookupCurrent(fd.name);
				if (existingSym != null) {
					// another function symbol with same name in same scope => error
					error("Function declaration " + fd.name + " is already declared in this scope.");
				} else {
					// insert new function symbol
					currentScope.put(new FunctionSymbol(fd.name, fd));
				}

				// scope for parameters
				Scope prev = currentScope;
				currentScope = new Scope(prev);
				for (VarDecl param : fd.params) {
					visit(param);
				}
				currentScope = prev;
			}

			case FunDef fd -> {
				Symbol existingSym = currentScope.lookupCurrent(fd.name);
				if (existingSym == null) {
					// no function with that name exists yet, add it
					currentScope.put(new FunctionSymbol(fd.name, fd));
				} else if (existingSym instanceof FunctionSymbol fs) {
					// if the existing symbol already has a definition (FunDef), that's a duplicate
					if (fs.decl instanceof FunDef) {
						error("Function definition " + fd.name + " is already defined in this scope.");
					} else {
						// otherwise, it was only declared (FunDecl) earlier. Now update the symbol
						// to the definition
						fs.decl = fd;
					}
				} else {
					error("Identifier " + fd.name + " does not refer to a function.");
				}

				// create a new scope for the function's body
				Scope prev = currentScope;
				currentScope = new Scope(prev);

				// for instance methods, inject the enclosing class's fields
				if (fd.isInstanceMethod && !classStack.isEmpty()) {
					ClassDecl enclosing = classStack.peek();
					// inject inherited fields from all ancestors
					injectInheritedFields(currentScope, enclosing);
					// inject fields declared in the current (enclosing) class
					for (VarDecl field : enclosing.getFields()) {
						currentScope.put(new VariableSymbol(field.name, field));
					}
				}

				// process parameters
				for (VarDecl param : fd.params) {
					visit(param);
				}
				// process the function body
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


			// class declaration
			case ClassDecl cd -> {
				// check for duplicate class name in current scope
				Symbol existingSym = currentScope.lookupCurrent(cd.getName());
				if (existingSym != null) {
					error("Class " + cd.getName() + " is already declared in this scope.");
				} else {
					currentScope.put(new ClassSymbol(cd.getName(), cd));
				}

				// if it has a parent, ensure that parent is declared as a class
				if (cd.getParentName() != null) {
					Symbol parentSym = currentScope.lookup(cd.getParentName());
					if (parentSym == null) {
						error("Parent class " + cd.getParentName() + " is not declared.");
					} else if (!(parentSym instanceof ClassSymbol)) {
						error("Identifier " + cd.getParentName() + " does not refer to a class.");
					} else {
						// check for inheritance cycle
						checkInheritanceCycle(cd.getName(), ((ClassSymbol) parentSym).decl);
					}
				}

				// create an inner scope for class members and push this class on the classStack
				Scope prev = currentScope;
				currentScope = new Scope(prev);
				classStack.push(cd);

				// add field declarations
				for (VarDecl field : cd.getFields()) {
					if (currentScope.lookupCurrent(field.name) != null) {
						error("Field " + field.name + " is already declared in class " + cd.getName());
					} else {
						field.isInstanceField = true; // mark as instance field
						currentScope.put(new VariableSymbol(field.name, field));
					}
				}

				// add method declarations
				for (FunDef method : cd.getMethods()) {
					Symbol mSym = currentScope.lookupCurrent(method.name);
					if (mSym != null) {
						error("Method " + method.name + " is already declared in class " + cd.getName());
					} else {
						currentScope.put(new FunctionSymbol(method.name, method));
					}
				}

				// process each method body while the class scope (with the fields) is active
				for (FunDef method : cd.getMethods()) {
					visit(method);
				}

				classStack.pop();
				currentScope = prev;
			}


			// instance function call
			case InstanceFunCallExpr ifce -> {
				// visit the object expression
				visit(ifce.object);
				// visit the arguments
				for (Expr arg : ifce.args) {
					visit(arg);
				}
			}

			// new instance creation
			case NewInstance ni -> {
				// check that ni.classType is declared as a class
				Symbol s = currentScope.lookup(ni.classType.getName());
				if (s == null) {
					error("Class " + ni.classType.getName() + " is not declared.");
				} else if (!(s instanceof ClassSymbol)) {
					error("Identifier " + ni.classType.getName() + " does not refer to a class.");
				}
			}

			// all other ASTNodes (including Type, BinOp, etc.)
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


	 // new class symbol for part V
	static class ClassSymbol extends Symbol {
		public ClassDecl decl;

		public ClassSymbol(String name, ClassDecl decl) {
			super(name);
			this.decl = decl;
		}
	}


	 // ensures that class 'childName' is not among the ancestors of 'parentDecl' to avoid inheritance cycles
	private void checkInheritanceCycle(String childName, ClassDecl parentDecl) {
		// if parent's name is exactly child's name then direct cycle
		if (parentDecl.getName().equals(childName)) {
			error("Inheritance cycle: " + childName + " cannot inherit from itself.");
			return;
		}
		// if parent's parent is null then no cycle
		if (parentDecl.getParentName() == null) {
			return;
		}
		// otherwise look up parent's parent
		Symbol grandSym = currentScope.lookup(parentDecl.getParentName());
		if (grandSym instanceof ClassSymbol cs) {
			checkInheritanceCycle(childName, cs.decl);
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


	// recursively injects fields declared in ancestor classes into the given scope
	private void injectInheritedFields(Scope scope, ClassDecl cd) {
		String parentName = cd.getParentName();
		if (parentName != null) {
			// lookup the parent class symbol in an outer scope
			Symbol parentSym = scope.lookup(parentName);
			if (parentSym instanceof ClassSymbol ps) {
				ClassDecl parentDecl = ps.decl;
				// first, recursively inject fields from ancestors of the parent
				injectInheritedFields(scope, parentDecl);
				// then, inject the parent’s own fields
				for (VarDecl field : parentDecl.getFields()) {
					scope.put(new VariableSymbol(field.name, field));
				}
			}
		}
	}

}