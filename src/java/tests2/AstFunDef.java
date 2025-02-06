package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class AstFunDef {
    public static void main(String[] args) {
        Return ret = new Return(null);

        // block with no var decl and single stmt (the return)
        Block block = new Block();
        block.vds = new ArrayList<>();
        block.stmts = new ArrayList<>();
        block.stmts.add(ret);

        FunDef funDef = new FunDef(BaseType.VOID, "foo", new ArrayList<>(), block);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(funDef);
        pw.flush();
        String output = sw.toString();

        System.out.println("TestAST_FunDef output:");
        System.out.println(output);
    }
}