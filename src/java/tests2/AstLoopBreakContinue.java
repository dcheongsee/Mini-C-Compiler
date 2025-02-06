package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class AstLoopBreakContinue {
    public static void main(String[] args) {
        VarExpr cond = new VarExpr("cond");

        Block body = new Block();
        body.vds = new ArrayList<>();
        body.stmts = new ArrayList<>();
        body.stmts.add(new Continue());
        body.stmts.add(new Break());

        While wh = new While(cond, body);

        Block block = new Block();
        block.vds = new ArrayList<>();
        block.stmts = new ArrayList<>();
        block.stmts.add(wh);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(block);
        pw.flush();

        System.out.println("AstLoopBreakContinue output:");
        System.out.println(sw.toString());
        // expected output
        // Block(While(VarExpr(cond),Block(Continue(),Break())))
    }
}