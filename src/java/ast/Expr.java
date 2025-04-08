package ast;

public sealed abstract class Expr implements ASTNode
        permits AddressOfExpr, ArrayAccessExpr, Assign, BinOp, ChrLiteral, FieldAccessExpr, FunCallExpr,
        IntLiteral, SizeOfExpr, StrLiteral, TypecastExpr, ValueAtExpr, VarExpr,
        NewInstance, InstanceFunCallExpr {
    public Type type;
}