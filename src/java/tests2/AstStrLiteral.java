package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstStrLiteral {
    public static void main(String[] args) {
        StrLiteral lit = new StrLiteral("Hello");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(lit);
        pw.flush();
        System.out.println("TestAST_StrLiteral output:");
        System.out.println(sw.toString());
        // expected output is StrLiteral(Hello)
    }
}