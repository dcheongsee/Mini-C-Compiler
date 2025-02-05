package ast;

import java.util.ArrayList;
import java.util.List;


//str literal
public final class StrLiteral extends Expr {
    public final String value;

    public StrLiteral(String value) {
        this.value = value;
    }

    @Override
    public List<ASTNode> children() {
        return new ArrayList<>();
    }

}
