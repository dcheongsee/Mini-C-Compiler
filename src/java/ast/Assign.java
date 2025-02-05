package ast;

import java.util.ArrayList;
import java.util.List;

public final class Assign extends Expr {
    public final Expr left;
    public final Expr right;

    public Assign(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(left);
        children.add(right);
        return children;
    }
}