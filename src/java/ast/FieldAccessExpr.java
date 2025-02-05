package ast;
import java.util.List;
import java.util.ArrayList;


// field access expr i.e expr.fieldName
public final class FieldAccessExpr extends Expr {
    public final Expr expr;
    public final String field;

    public FieldAccessExpr(Expr expr, String field) {
        this.expr = expr;
        this.field = field;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(expr);
        return children;
    }
}