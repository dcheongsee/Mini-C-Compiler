package ast;

import java.util.List;

public sealed interface  ASTNode
        permits ArrayLength, Decl, Expr, Program, Stmt, Type {

    /**
     * Returns all the children of this ASTNode.
     * */
    abstract List<ASTNode> children();
}
