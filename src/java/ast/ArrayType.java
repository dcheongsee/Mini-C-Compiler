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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayType)) return false;
        ArrayType other = (ArrayType) o;
        // check that lengths are equal and element types are equal (recursively)
        return this.length == other.length && this.elementType.equals(other.elementType);
    }

    @Override
    public int hashCode() {
        int result = elementType.hashCode();
        result = 31 * result + length;
        return result;
    }


}