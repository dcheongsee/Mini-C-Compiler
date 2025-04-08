package ast;

import java.util.ArrayList;
import java.util.List;


// represents a call to an instance's method eg obj.someMethod(arg1, arg2, ...)
public final class InstanceFunCallExpr extends Expr {

    public final Expr object;
    public final String methodName;
    public final List<Expr> args;

    public InstanceFunCallExpr(Expr object, String methodName, List<Expr> args) {
        this.object = object;
        this.methodName = methodName;
        this.args = args;
    }

    @Override
    public List<ASTNode> children() {
        // combine the object plus all the argument expressions
        List<ASTNode> result = new ArrayList<>();
        result.add(object);
        result.addAll(args);
        return result;
    }

    @Override
    public String toString() {
        return "InstanceFunCall(" + object + "." + methodName + "(...))";
    }
}
