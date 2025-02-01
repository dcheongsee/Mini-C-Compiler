package ast;

import java.util.ArrayList;
import java.util.List;

public final class VarDecl extends Decl {

    public VarDecl(Type type, String name) {
	    this.type = type;
	    this.name = name;
    }

    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(type);
        return children;
    }

}
