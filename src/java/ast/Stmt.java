package ast;

public sealed abstract class Stmt implements ASTNode
        permits Block, Break, ExprStmt, Return, While, Continue, If {
}
