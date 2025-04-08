package ast;

import java.util.Collections;
import java.util.List;


// represents a class type in our language eg "class Course"
public final class ClassType implements Type {

    private final String className;

    public ClassType(String className) {
        this.className = className;
    }

    public String getName() {
        return className;
    }

    @Override
    public List<ASTNode> children() {
        // classType is a leaf node in the AST (it has no children)
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "class " + className;
    }
}
