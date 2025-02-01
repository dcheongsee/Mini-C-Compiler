package sem;

import ast.*;

public class NameAnalyzer extends BaseSemanticAnalyzer {


	public void visit(ASTNode node) {
		switch(node) {
			case null -> {
				throw new IllegalStateException("Unexpected null value");
			}

			case Block b -> {
				// to complete
			}

			case FunDecl fd -> {
				// to complete
			}

			case FunDef fd -> {
				// to complete
			}

			case Program p -> {
				// to complete
			}

			case VarDecl vd -> {
				// to complete
			}

			case VarExpr v -> {
				// to complete
			}

			case StructTypeDecl std -> {
				// to complete
			}


			case Type t -> {}

			// to complete ...

        };

	}




}
