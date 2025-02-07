package tests2;

import ast.*;
import java.io.PrintWriter;
import java.util.List;

public class AstSortLinkedList {
    public static void main(String[] args) {
        VarDecl fieldData = new VarDecl(BaseType.INT, "data");
        PointerType nodePtrType = new PointerType(new StructType("Node"));
        VarDecl fieldNext = new VarDecl(nodePtrType, "next");
        StructTypeDecl nodeStruct = new StructTypeDecl("Node", List.of(fieldData, fieldNext));

        VarDecl paramHead = new VarDecl(new PointerType(new StructType("Node")), "head");

        BinOp condition = new BinOp(new VarExpr("head"), Op.NE, new IntLiteral(0));
        ValueAtExpr derefHead = new ValueAtExpr(new VarExpr("head"));
        FieldAccessExpr nextField = new FieldAccessExpr(derefHead, "next");
        Assign assign = new Assign(new VarExpr("head"), nextField);
        ExprStmt assignStmt = new ExprStmt(assign);

        While whileLoop = new While(condition, assignStmt);

        Block block = new Block();
        block.stmts.add(whileLoop);
        FunDef sortListFun = new FunDef(BaseType.VOID, "sortList", List.of(paramHead), block);

        Program program = new Program(List.of(nodeStruct, sortListFun));

        PrintWriter writer = new PrintWriter(System.out, true);
        ASTPrinter printer = new ASTPrinter(writer);
        printer.visit(program);
        writer.println();
    }
}