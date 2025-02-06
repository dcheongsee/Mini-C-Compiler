package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstStrliteral2 {
    public static void main(String[] args) {
        StrLiteral s = new StrLiteral("Hello, world!");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(s);
        pw.flush();
        System.out.println("AstStrliteral output:");
        System.out.println(sw.toString());
        // expected output
        // StrLiteral(Hello, world!)
    }
}