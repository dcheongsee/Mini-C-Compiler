package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstReturn {
    public static void main(String[] args) {
        Return ret = new Return(new IntLiteral(5));

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(ret);
        pw.flush();
        System.out.println("AstReturn output:");
        System.out.println(sw.toString());
        // expected output
        // Return(IntLiteral(5))
    }
}