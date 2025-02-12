package tests2;

import ast.*;
import java.io.PrintWriter;
import java.util.List;

public class AstEscapedChars {
    public static void main(String[] args) {
        StrLiteral strLit = new StrLiteral("Hello\nWorld");
        ChrLiteral chrLit = new ChrLiteral('\\');
        ExprStmt exprStmt1 = new ExprStmt(strLit);
        ExprStmt exprStmt2 = new ExprStmt(chrLit);

        Block block = new Block();
        block.stmts.add(exprStmt1);
        block.stmts.add(exprStmt2);
        FunDef funDef = new FunDef(BaseType.VOID, "main", List.of(), block);

        Program program = new Program(List.of(funDef));

        PrintWriter writer = new PrintWriter(System.out, true);
        ASTPrinter printer = new ASTPrinter(writer);
        printer.visit(program);
        writer.println();
    }
}