package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class AstNestedBlocks {
    public static void main(String[] args) {
        VarDecl varDecl = new VarDecl(BaseType.INT, "x");
        Block innerBlock = new Block();
        innerBlock.vds = new ArrayList<>();
        innerBlock.stmts = new ArrayList<>();
        innerBlock.stmts.add(new Return(new IntLiteral(0)));

        Block outerBlock = new Block();
        outerBlock.vds = new ArrayList<>();
        outerBlock.vds.add(varDecl);
        outerBlock.stmts = new ArrayList<>();
        outerBlock.stmts.add(innerBlock);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(outerBlock);
        pw.flush();
        System.out.println("AstNestedBlocks output:");
        System.out.println(sw.toString());
        // expected output
        // Block(VarDecl(INT,x),Block(Return(IntLiteral(0))))
    }
}