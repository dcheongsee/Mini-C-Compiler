package ast;

import java.util.ArrayList;
import java.util.List;


// int literal
public final class IntLiteral extends Expr {
    public final int value;

    public IntLiteral(int value) {
        this.value = value;
    }

    @Override
    public List<ASTNode> children() {
        return new ArrayList<>();
    }

}
