package ast;

import java.util.ArrayList;
import java.util.List;


// assignment expr i.e Expr = Expr
public final class Assign extends Expr {
    public final Expr lhs;
    public final Expr rhs;

    public Assign(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(lhs);
        children.add(rhs);
        return children;
    }

    @Override
    public String toString() {
        return "Assign(" + lhs + ", " + rhs + ")";
    }
}
