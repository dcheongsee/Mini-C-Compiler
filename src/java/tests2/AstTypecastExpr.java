package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstTypecastExpr {
    public static void main(String[] args) {
        VarExpr x = new VarExpr("x");
        TypecastExpr typecast = new TypecastExpr(BaseType.INT, x);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(typecast);
        pw.flush();
        System.out.println("TestAST_TypecastExpr output:");
        System.out.println(sw.toString());
        // expected output is TypecastExpr(INT,VarExpr(x))
    }
}