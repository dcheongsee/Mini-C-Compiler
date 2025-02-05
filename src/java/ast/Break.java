package ast;

import java.util.ArrayList;
import java.util.List;


// break stmt
public final class Break extends Stmt {
    @Override
    public List<ASTNode> children() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Break()";
    }
}
