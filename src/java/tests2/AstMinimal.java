package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class AstMinimal {
    public static void main(String[] args) {
        Return ret = new Return(new IntLiteral(0));

        Block block = new Block();
        block.vds = new ArrayList<>();
        block.stmts = new ArrayList<>();
        block.stmts.add(ret);

        FunDef mainFun = new FunDef(BaseType.INT, "main", new ArrayList<>(), block);

        ArrayList<Decl> decls = new ArrayList<>();
        decls.add(mainFun);
        Program prog = new Program(decls);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(prog);
        pw.flush();
        System.out.println("AstMinimal output:");
        System.out.println(sw.toString());
        // expected output
        // Program(FunDef(INT,main,Block(Return(IntLiteral(0)))))
    }
}