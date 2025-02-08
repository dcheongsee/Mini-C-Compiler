package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemStructRecursive {
    public static void main(String[] args) {
        VarDecl nodeValue = new VarDecl(BaseType.INT, "value");
        PointerType ptrToNode = new PointerType(new StructType("Node"));
        VarDecl nodeNext = new VarDecl(ptrToNode, "next");
        StructTypeDecl structNode = new StructTypeDecl("Node", new ArrayList<>(List.of(nodeValue, nodeNext)));
        VarDecl varN = new VarDecl(new StructType("Node"), "n");
        FieldAccessExpr accessValue = new FieldAccessExpr(new VarExpr("n"), "value");
        FieldAccessExpr accessNext = new FieldAccessExpr(new VarExpr("n"), "next");
        FieldAccessExpr nestedAccess = new FieldAccessExpr(new ValueAtExpr(accessNext), "value");
        Assign assignField1 = new Assign(accessValue, new IntLiteral(10));
        Assign assignField2 = new Assign(nestedAccess, new IntLiteral(20));
        Block block = new Block(new ArrayList<>(List.of(varN)), List.of(new ExprStmt(assignField1), new ExprStmt(assignField2)));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(structNode, mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemStructRecursive: Number of semantic errors: " + sem.getNumErrors());
    }
}