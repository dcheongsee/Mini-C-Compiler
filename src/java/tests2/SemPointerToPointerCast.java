package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemPointerToPointerCast {
    public static void main(String[] args) {
        VarDecl p = new VarDecl(new PointerType(BaseType.INT), "p");
        TypecastExpr castExpr = new TypecastExpr(new PointerType(BaseType.INT), new VarExpr("p"));
        VarDecl q = new VarDecl(new PointerType(BaseType.INT), "q");
        Assign assign = new Assign(new VarExpr("q"), castExpr);
        ExprStmt stmt = new ExprStmt(assign);
        Block block = new Block(new ArrayList<>(List.of(p, q)), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemPointerToPointerCast: Number of semantic errors: " + sem.getNumErrors());
    }
}