package ast;

import java.util.ArrayList;
import java.util.List;

public final class While extends Stmt {
    public final Expr expr;  // loop condition
    public final Stmt stmt;  // body of loop

    public While(Expr expr, Stmt stmt) {
        this.expr = expr;
        this.stmt = stmt;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(expr);
        children.add(stmt);
        return children;
    }
}