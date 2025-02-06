package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstArithmetic {
    public static void main(String[] args) {
        // expression 2 + 3 * 4
        IntLiteral two = new IntLiteral(2);
        IntLiteral three = new IntLiteral(3);
        IntLiteral four = new IntLiteral(4);
        BinOp mult = new BinOp(three, Op.MUL, four);
        BinOp add = new BinOp(two, Op.ADD, mult);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(add);
        pw.flush();
        System.out.println("AstArithmetic output:");
        System.out.println(sw.toString());
        // expected output
        // BinOp(IntLiteral(2),ADD,BinOp(IntLiteral(3),MUL,IntLiteral(4)))
    }
}