package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstChrLiteral {
    public static void main(String[] args) {
        ChrLiteral lit = new ChrLiteral('a');
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(lit);
        pw.flush();
        System.out.println("TestAST_ChrLiteral output:");
        System.out.println(sw.toString());
        // expected output is ChrLiteral(a)
    }
}