package ast;

import java.util.ArrayList;
import java.util.List;


// sizeof expr i.e SizeOfExpr ::= Type
public final class SizeOfExpr extends Expr {
    public final Type type;

    public SizeOfExpr(Type type) {
        this.type = type;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(type);
        return children;
    }

    @Override
    public String toString() {
        return "SizeOfExpr(" + type + ")";
    }
}