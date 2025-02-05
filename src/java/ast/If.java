package ast;

import java.util.ArrayList;
import java.util.List;

public final class If extends Stmt {
    public final Expr expr;      // condition
    public final Stmt thenStmt;  // stmt executed if condition is true
    public final Stmt elseStmt;  // stmt executed if condition is false (can be null)

    public If(Expr expr, Stmt thenStmt, Stmt elseStmt) {
        this.expr = expr;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(expr);
        children.add(thenStmt);
        if (elseStmt != null)
            children.add(elseStmt);
        return children;
    }
}