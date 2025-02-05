package ast;

public sealed abstract class Expr implements ASTNode
        permits ArrayAccessExpr, Assign, BinOp, ChrLiteral, FieldAccessExpr, FunCallExpr, IntLiteral, StrLiteral, VarExpr, TypecastExpr, SizeOfExpr {

    public Type type; // to be filled in by the type analyser
}