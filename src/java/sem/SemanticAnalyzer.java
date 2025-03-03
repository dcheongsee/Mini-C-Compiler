package sem;

import util.CompilerPass;
import ast.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SemanticAnalyzer extends CompilerPass {

	private TypeAnalyzer typeAnalyzer;
	public static Program GlobalAST;

	public void analyze(ast.Program prog) {
		GlobalAST = prog;
		for (FunDecl builtin : List.of(
				new FunDecl(BaseType.INT, "read_i", List.of()),
				new FunDecl(BaseType.VOID, "print_c", List.of(new VarDecl(BaseType.CHAR, "c"))),
				new FunDecl(BaseType.CHAR, "read_c", List.of()),
				new FunDecl(BaseType.VOID, "mcmalloc", List.of(new VarDecl(BaseType.INT, "size"))),
				new FunDecl(BaseType.VOID, "print_s", List.of(new VarDecl(new PointerType(BaseType.CHAR), "s"))),
				new FunDecl(BaseType.VOID, "print_i", List.of(new VarDecl(BaseType.INT, "i")))
		))
		{
			if (!alreadyDeclared(prog, builtin.name)) {
				prog.decls.add(builtin);
			}
		}

		NameAnalyzer na = new NameAnalyzer();
		na.visit(prog);
		this.numErrors += na.getNumErrors();

		LValueLoopChecker ll = new LValueLoopChecker();
		ll.visit(prog, false);
		this.numErrors += ll.getNumErrors();

		typeAnalyzer = new TypeAnalyzer();
		typeAnalyzer.visit(prog);
		this.numErrors += typeAnalyzer.getNumErrors();

		ReturnChecker rc = new ReturnChecker(typeAnalyzer);
		rc.visit(prog);
		this.numErrors += rc.getNumErrors();
	}

	private void error(String message) {
		System.out.println("semantic error: " + message);
		incError();
	}


	private class LValueLoopChecker extends CompilerPass {
		public void visit(ASTNode node, boolean inLoop) {
			if (node == null) return;

			// handle specific constructs
			if (node instanceof Assign assign) {
				if (!isLValue(assign.left)) {
					SemanticAnalyzer.this.error("Left hand side of assignment is not an lvalue.");
				}
				// visit both sides
				visit(assign.left, inLoop);
				visit(assign.right, inLoop);
				return;
			}

			if (node instanceof AddressOfExpr ao) {
				if (!isLValue(ao.expr)) {
					SemanticAnalyzer.this.error("Address of operator applied to a non-lvalue.");
				}
				visit(ao.expr, inLoop);
				return;
			}

			if (node instanceof Continue) {
				if (!inLoop) {
					SemanticAnalyzer.this.error("Continue statement outside of a loop.");
				}
				return;
			}

			if (node instanceof Break) {
				if (!inLoop) {
					SemanticAnalyzer.this.error("Break statement outside of a loop.");
				}
				return;
			}

			if (node instanceof While wh) {
				// when inside a while loop, body is in loop context
				visit(wh.expr, inLoop);
				visit(wh.stmt, true);
				return;
			}

			// recursively visit children with current loop context
			for (ASTNode child : node.children()) {
				visit(child, inLoop);
			}
		}

		private boolean isLValue(ASTNode node) {
			return switch (node) {
				case VarExpr ve -> true;
				case FieldAccessExpr fa -> isLValue(fa.expr);
				case ArrayAccessExpr aa -> isLValue(aa.array);
				case ValueAtExpr va -> isLValue(va.expr);
				default -> false;
			};
		}
	}




	private class ReturnChecker extends CompilerPass {

		private final TypeAnalyzer ta;

		public ReturnChecker(TypeAnalyzer ta) {
			this.ta = ta;
		}

		public void visit(ASTNode node) {
			if (node == null) return;

			// when encountering func def, check its block
			if (node instanceof FunDef fd) {
				checkReturns(fd.block, fd.type);
			}

			for (ASTNode child : node.children()) {
				visit(child);
			}
		}

		private void checkReturns(ASTNode node, Type expected) {
			if (node == null) return;

			if (node instanceof Return ret) {
				if (expected == BaseType.VOID) {
					if (ret.expr != null) {
						SemanticAnalyzer.this.error("Void function returning a value.");
					}
				} else {
					if (ret.expr == null) {
						SemanticAnalyzer.this.error("Non-void function must return a value.");
					} else {
						Type retType = ta.visit(ret.expr);
						if (!retType.equals(expected)) {
							SemanticAnalyzer.this.error("Return expression type " + retType + " does not match declared type " + expected + ".");
						}
					}
				}
				// no need to traverse further into a Return node
				return;
			}

			for (ASTNode child : node.children()) {
				checkReturns(child, expected);
			}
		}
	}

	private boolean alreadyDeclared(Program prog, String funName) {
		for (Decl d : prog.decls) {
			if ((d instanceof FunDecl || d instanceof FunDef) && d.name.equals(funName)) {
				return true;
			}
		}
		return false;
	}


}
