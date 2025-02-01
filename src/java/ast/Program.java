package ast;

import java.util.ArrayList;
import java.util.List;

public final class Program implements ASTNode {

    public final List<Decl> decls;


    public Program(List<Decl> decls) {
        this.decls = decls;
    }

    public List<ASTNode> children() {
        return new ArrayList<>(decls);
    }

}
