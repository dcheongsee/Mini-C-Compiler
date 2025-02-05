package ast;

import java.util.ArrayList;
import java.util.List;


// binary op i.e Expr Op Expr
public final class BinOp extends Expr {
    public final Expr left;
    public final Op op;
    public final Expr right;

    public BinOp(Expr left, Op op, Expr right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(left);
        children.add(right);
        return children;
    }

    @Override
    public String toString() {
        return "BinOp(" + left + ", " + op + ", " + right + ")";
    }
}
