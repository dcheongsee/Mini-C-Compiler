package ast;

import java.util.ArrayList;
import java.util.List;


// if stmt with optional else branch
public final class If extends Stmt {
    public final Expr cond;
    public final Stmt thenStmt;
    public final Stmt elseStmt; // may be null

    public If(Expr cond, Stmt thenStmt, Stmt elseStmt) {
        this.cond = cond;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(cond);
        children.add(thenStmt);
        if (elseStmt != null) children.add(elseStmt);
        return children;
    }

    @Override
    public String toString() {
        return "If(" + cond + ", " + thenStmt + ", " + elseStmt + ")";
    }
}
