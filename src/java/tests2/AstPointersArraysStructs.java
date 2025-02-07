package tests2;

import ast.*;
import java.io.PrintWriter;
import java.util.List;

public class AstPointersArraysStructs {
    public static void main(String[] args) {
        VarDecl fieldValue = new VarDecl(BaseType.INT, "value");
        StructTypeDecl itemStruct = new StructTypeDecl("Item", List.of(fieldValue));

        ArrayType itemsType = new ArrayType(new PointerType(new StructType("Item")), 5);
        VarDecl itemsDecl = new VarDecl(itemsType, "items");
        VarDecl itemDecl = new VarDecl(new PointerType(new StructType("Item")), "item");

        ArrayAccessExpr arrayAccess = new ArrayAccessExpr(new VarExpr("items"), new IntLiteral(0));
        AddressOfExpr addressOf = new AddressOfExpr(new VarExpr("item"));
        Assign assign = new Assign(arrayAccess, addressOf);
        ExprStmt assignStmt = new ExprStmt(assign);

        Block block = new Block();
        block.vds.add(itemsDecl);
        block.vds.add(itemDecl);
        block.stmts.add(assignStmt);

        FunDef processFun = new FunDef(BaseType.VOID, "process", List.of(), block);

        Program program = new Program(List.of(itemStruct, processFun));

        PrintWriter writer = new PrintWriter(System.out, true);
        ASTPrinter printer = new ASTPrinter(writer);
        printer.visit(program);
        writer.println();
    }
}