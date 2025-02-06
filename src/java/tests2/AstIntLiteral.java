package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstIntLiteral {
    public static void main(String[] args) {
        IntLiteral lit = new IntLiteral(42);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(lit);
        pw.flush();
        System.out.println("TestAST_IntLiteral output:");
        System.out.println(sw.toString());
    }
}