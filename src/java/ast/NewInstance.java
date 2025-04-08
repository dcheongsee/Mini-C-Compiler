package ast;

import java.util.Collections;
import java.util.List;


// represents an expression for creating a new instance of a class eg new class Course()
public final class NewInstance extends Expr {

    public final ClassType classType;

    public NewInstance(ClassType classType) {
        this.classType = classType;
    }

    @Override
    public List<ASTNode> children() {
        // we can consider the classType as a child
        // to keep it consistent with how we handle type references in the AST
        return Collections.singletonList(classType);
    }

    @Override
    public String toString() {
        return "new " + classType;
    }
}
