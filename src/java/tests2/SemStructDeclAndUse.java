package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemStructDeclAndUse {
    public static void main(String[] args) {
        VarDecl field = new VarDecl(BaseType.INT, "x");
        StructTypeDecl structS = new StructTypeDecl("S", new ArrayList<>(List.of(field)));
        VarDecl varS = new VarDecl(new StructType("S"), "s");
        FieldAccessExpr fieldAccess = new FieldAccessExpr(new VarExpr("s"), "x");
        Assign assign = new Assign(fieldAccess, new IntLiteral(5));
        ExprStmt stmt = new ExprStmt(assign);
        Block block = new Block(new ArrayList<>(List.of(varS)), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(structS, mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemStructDeclAndUse: Number of semantic errors: " + sem.getNumErrors());
    }
}