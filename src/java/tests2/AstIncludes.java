package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class AstIncludes {
    public static void main(String[] args) {
        Program prog = new Program(new ArrayList<>());

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(prog);
        pw.flush();
        System.out.println("AstIncludes output:");
        System.out.println(sw.toString());
        // expected output
        // Program()
    }
}