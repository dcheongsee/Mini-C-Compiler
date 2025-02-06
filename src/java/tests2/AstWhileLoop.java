package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstWhileLoop {
    public static void main(String[] args) {
        Expr condition = new BinOp(new VarExpr("x"), Op.LT, new IntLiteral(10));
        Expr increment = new BinOp(new VarExpr("x"), Op.ADD, new IntLiteral(1));
        Assign assign = new Assign(new VarExpr("x"), increment);
        ExprStmt stmt = new ExprStmt(assign);
        Block body = new Block();
        body.vds = new java.util.ArrayList<>();
        body.stmts = new java.util.ArrayList<>();
        body.stmts.add(stmt);

        While wh = new While(condition, body);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(wh);
        pw.flush();
        System.out.println("AstWhileLoop output:");
        System.out.println(sw.toString());
        // expected output
        // While(BinOp(VarExpr(x),LT,IntLiteral(10)),Block(ExprStmt(Assign(VarExpr(x),BinOp(VarExpr(x),ADD,IntLiteral(1))))))
    }
}