package ast;

import java.util.ArrayList;
import java.util.List;

public final class VarDecl extends Decl {
    // new fields for memory allocation
    public int offset;         // for local variables
    public String globalLabel; // for global variables

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
