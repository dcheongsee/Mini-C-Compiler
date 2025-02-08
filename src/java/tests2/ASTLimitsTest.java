package tests2;

import ast.*;
import java.io.PrintWriter;

public class ASTLimitsTest {
    public static void main(String[] args) {
        VarExpr a = new VarExpr("a");
        VarExpr b = new VarExpr("b");
        BinOp aTimes2 = new BinOp(a, Op.MUL, new IntLiteral(2));
        BinOp bPlus4 = new BinOp(b, Op.ADD, new IntLiteral(4));
        Assign assignBPlus4 = new Assign(b, bPlus4);
        BinOp innerAdd = new BinOp(aTimes2, Op.ADD, assignBPlus4);
        Assign assignAComplex = new Assign(a, innerAdd);
        BinOp aDivB = new BinOp(a, Op.DIV, b);
        TypecastExpr castAdivB = new TypecastExpr(BaseType.INT, aDivB);
        BinOp subtraction = new BinOp(assignAComplex, Op.SUB, castAdivB);
        Return ret = new Return(subtraction);

        PrintWriter writer = new PrintWriter(System.out, true);
        ASTPrinter printer = new ASTPrinter(writer);
        printer.visit(ret);
        writer.println();
    }
}