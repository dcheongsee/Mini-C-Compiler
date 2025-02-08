package ast;

import java.util.ArrayList;
import java.util.List;


// pointer type pointing to base type
public final class PointerType implements Type {
    public final Type base;

    public PointerType(Type base) {
        this.base = base;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(base);
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointerType)) return false;
        PointerType that = (PointerType) o;
        return base.equals(that.base);
    }

    @Override
    public int hashCode() {
        return base.hashCode();
    }

}