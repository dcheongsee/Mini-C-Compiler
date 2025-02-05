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
    public String toString() {
        return "StructType(" + name + ")";
    }
}