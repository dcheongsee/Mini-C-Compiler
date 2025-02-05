package ast;

import java.util.ArrayList;
import java.util.List;


// expression stmt
public final class ExprStmt extends Stmt {
    public final Expr expr;

    public ExprStmt(Expr expr) {
        this.expr = expr;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(expr);
        return children;
    }

    @Override
    public String toString() {
        return "ExprStmt(" + expr + ")";
    }
}
