package ast;

import java.util.ArrayList;
import java.util.List;


// char literal
public final class ChrLiteral extends Expr {
    public final char value;

    public ChrLiteral(char value) {
        this.value = value;
    }

    @Override
    public List<ASTNode> children() {
        return new ArrayList<>();
    }

}
