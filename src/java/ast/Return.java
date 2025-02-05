package ast;

import java.util.ArrayList;
import java.util.List;


// return stmt
public final class Return extends Stmt {
    public final Expr expr; // may be null

    public Return(Expr expr) {
        this.expr = expr;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        if (expr != null) children.add(expr);
        return children;
    }

    @Override
    public String toString() {
        return "Return(" + expr + ")";
    }
}
