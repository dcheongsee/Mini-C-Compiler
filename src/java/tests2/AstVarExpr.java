package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstVarExpr {
    public static void main(String[] args) {
        VarExpr var = new VarExpr("x");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(var);
        pw.flush();
        System.out.println("TestAST_VarExpr output:");
        System.out.println(sw.toString());
        // expected output is VarExpr(x)
    }
}