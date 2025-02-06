package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstBinOp {
    public static void main(String[] args) {
        IntLiteral two = new IntLiteral(2);
        IntLiteral three = new IntLiteral(3);

        // binary operation node representing 2 + 3
        BinOp add = new BinOp(two, Op.ADD, three);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(add);
        pw.flush();
        String output = sw.toString();

        System.out.println("TestAST_BinOp output:");
        System.out.println(output);
    }
}