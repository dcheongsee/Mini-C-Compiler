package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstFieldAccessExpr {
    public static void main(String[] args) {
        // assume obj is represented as var expr
        VarExpr obj = new VarExpr("obj");
        FieldAccessExpr fieldAccess = new FieldAccessExpr(obj, "field");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(fieldAccess);
        pw.flush();
        System.out.println("TestAST_FieldAccessExpr output:");
        System.out.println(sw.toString());
        // expected output is FieldAccessExpr(VarExpr(obj),field)
    }
}

