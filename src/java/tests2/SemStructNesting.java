package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemStructNesting {
    public static void main(String[] args) {
        VarDecl fieldA = new VarDecl(BaseType.INT, "a");
        StructTypeDecl structA = new StructTypeDecl("A", new ArrayList<>(List.of(fieldA)));
        VarDecl fieldB = new VarDecl(new StructType("A"), "a");
        StructTypeDecl structB = new StructTypeDecl("B", new ArrayList<>(List.of(fieldB)));
        VarDecl varB = new VarDecl(new StructType("B"), "b");
        FieldAccessExpr accessA = new FieldAccessExpr(new VarExpr("b"), "a");
        FieldAccessExpr accessAA = new FieldAccessExpr(accessA, "a");
        VarDecl varD = new VarDecl(BaseType.INT, "d");
        Assign assign = new Assign(new VarExpr("d"), accessAA);
        ExprStmt stmt = new ExprStmt(assign);
        Block block = new Block(new ArrayList<>(List.of(varB, varD)), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(structA, structB, mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemStructNesting: Number of semantic errors: " + sem.getNumErrors());
    }
}