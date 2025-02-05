package ast;

import java.util.ArrayList;
import java.util.List;


// field access expr i.e expr.fieldName
public final class FieldAccessExpr extends Expr {
    public final Expr struct;
    public final String field;

    public FieldAccessExpr(Expr struct, String field) {
        this.struct = struct;
        this.field = field;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(struct);
        return children;
    }

    @Override
    public String toString() {
        return "FieldAccessExpr(" + struct + ", " + field + ")";
    }
}
