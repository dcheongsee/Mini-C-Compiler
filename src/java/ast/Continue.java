package ast;

import java.util.ArrayList;
import java.util.List;


// continue stmt
public final class Continue extends Stmt {
    @Override
    public List<ASTNode> children() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Continue()";
    }
}
