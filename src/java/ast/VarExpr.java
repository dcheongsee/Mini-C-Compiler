package ast;

import java.util.ArrayList;
import java.util.List;

public final class VarExpr extends Expr {
    public final String name;
    public VarDecl vd; // to be filled in by the name analyser
    
    public VarExpr(String name){
	this.name = name;
    }

    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        return children;
    }

}
