package ast;

public sealed abstract class Stmt implements ASTNode
        permits Block, While, If, Return, Continue, Break, ExprStmt {
}
