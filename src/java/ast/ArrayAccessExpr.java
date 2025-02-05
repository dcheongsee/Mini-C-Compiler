package ast;

import java.util.ArrayList;
import java.util.List;


// array access expr i.e arrayExpr[indexExpr]
public final class ArrayAccessExpr extends Expr {
    public final Expr array;
    public final Expr index;

    public ArrayAccessExpr(Expr array, Expr index) {
        this.array = array;
        this.index = index;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(array);
        children.add(index);
        return children;
    }

}
