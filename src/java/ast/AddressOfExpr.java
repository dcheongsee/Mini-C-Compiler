package ast;

import java.util.ArrayList;
import java.util.List;

public final class AddressOfExpr extends Expr {
    public final Expr expr;

    public AddressOfExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(expr);
        return children;
    }
}