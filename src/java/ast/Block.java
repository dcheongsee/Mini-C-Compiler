package ast;

import java.util.ArrayList;
import java.util.List;

public final class Block extends Stmt {

    public List<VarDecl> vds;
    public List<Stmt> stmts;

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.addAll(vds);
        children.addAll(stmts);
        return children;
    }

    // to complete ...


}
