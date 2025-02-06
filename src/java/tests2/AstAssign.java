package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstAssign {
    public static void main(String[] args) {
        VarExpr x = new VarExpr("x");
        IntLiteral hundred = new IntLiteral(100);
        Assign assign = new Assign(x, hundred);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(assign);
        pw.flush();
        System.out.println("TestAST_Assign output:");
        System.out.println(sw.toString());
        // expected output is Assign(VarExpr(x),IntLiteral(100))
    }
}