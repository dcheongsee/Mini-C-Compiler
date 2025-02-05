package ast;

import java.util.ArrayList;
import java.util.List;


// func call expr i.e func name, arg list
public final class FunCallExpr extends Expr {
    public final String name;
    public final List<Expr> args;

    public FunCallExpr(String name, List<Expr> args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public List<ASTNode> children() {
        return new ArrayList<>(args);
    }

}
