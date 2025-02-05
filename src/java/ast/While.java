package ast;

import java.util.ArrayList;
import java.util.List;


// while loop i.e while (Expr) Stmt
public final class While extends Stmt {
    public final Expr cond;
    public final Stmt body;

    public While(Expr cond, Stmt body) {
        this.cond = cond;
        this.body = body;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(cond);
        children.add(body);
        return children;
    }

    @Override
    public String toString() {
        return "While(" + cond + ", " + body + ")";
    }
}
