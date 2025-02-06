package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class AstFunCallExpr {
    public static void main(String[] args) {
        // create arg expr
        IntLiteral arg1 = new IntLiteral(1);
        IntLiteral arg2 = new IntLiteral(2);
        ArrayList<Expr> argsList = new ArrayList<>();
        argsList.add(arg1);
        argsList.add(arg2);
        // build func call expr
        FunCallExpr fc = new FunCallExpr("foo", argsList);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(fc);
        pw.flush();
        System.out.println("TestAST_FunCallExpr output:");
        System.out.println(sw.toString());
        // expected output is FunCallExpr(foo,IntLiteral(1),IntLiteral(2))
    }
}