package ast;

import java.util.ArrayList;
import java.util.List;


// typecast expr i.e TypecastExpr ::= Type Expr
public final class TypecastExpr extends Expr {
    public final Type castType;
    public final Expr expr;

    public TypecastExpr(Type castType, Expr expr) {
        this.castType = castType;
        this.expr = expr;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(castType);
        children.add(expr);
        return children;
    }

    @Override
    public String toString() {
        return "TypecastExpr(" + castType + ", " + expr + ")";
    }
}