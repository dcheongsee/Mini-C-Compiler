package ast;

import java.util.ArrayList;
import java.util.List;

public final class ArrayLength implements ASTNode {
    public final int value;

    public ArrayLength(int value) {
        this.value = value;
    }

    @Override
    public List<ASTNode> children() {
        return new ArrayList<>();
    }

}