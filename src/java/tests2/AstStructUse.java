package tests2;

import ast.*;
import java.io.PrintWriter;
import java.util.List;

public class AstStructUse {
    public static void main(String[] args) {

        VarDecl field = new VarDecl(BaseType.INT, "field");
        StructTypeDecl myStructDecl = new StructTypeDecl("MyStruct", List.of(field));

        VarDecl varS = new VarDecl(new StructType("MyStruct"), "s");

        FieldAccessExpr lhs = new FieldAccessExpr(new VarExpr("s"), "field");
        Assign assign = new Assign(lhs, new IntLiteral(42));
        ExprStmt assignStmt = new ExprStmt(assign);

        Block block = new Block();
        block.vds.add(varS);
        block.stmts.add(assignStmt);

        FunDef useStructFun = new FunDef(BaseType.VOID, "useStruct", List.of(), block);

        Program program = new Program(List.of(myStructDecl, useStructFun));

        PrintWriter writer = new PrintWriter(System.out, true);
        ASTPrinter printer = new ASTPrinter(writer);
        printer.visit(program);
        writer.println();
    }
}