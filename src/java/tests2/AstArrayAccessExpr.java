package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstArrayAccessExpr {
    public static void main(String[] args) {
        VarExpr arr = new VarExpr("arr");
        IntLiteral index = new IntLiteral(0);
        ArrayAccessExpr arrayAccess = new ArrayAccessExpr(arr, index);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(arrayAccess);
        pw.flush();
        System.out.println("TestAST_ArrayAccessExpr output:");
        System.out.println(sw.toString());
        // expected output is ArrayAccessExpr(VarExpr(arr),IntLiteral(0))
    }
}