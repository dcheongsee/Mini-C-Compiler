package ast;

import java.util.ArrayList;
import java.util.List;

public final class ArrayType implements Type {
    public final Type elementType;
    public final int length;

    public ArrayType(Type elementType, int length) {
        this.elementType = elementType;
        this.length = length;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(elementType);
        children.add(new ArrayLength(length));
        return children;
    }

}