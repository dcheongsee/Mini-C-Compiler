package ast;

import java.util.ArrayList;
import java.util.List;


// struct type (by name)
public final class StructType implements Type {
    public final String name;

    public StructType(String name) {
        this.name = name;
    }

    @Override
    public List<ASTNode> children() {
        return new ArrayList<>();
    }

    @Override
    public String toString() { return "StructType(" + name + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StructType)) return false;
        StructType that = (StructType) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}