package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemTypecastArrayToPointer {
    public static void main(String[] args) {
        VarDecl arr = new VarDecl(new ArrayType(BaseType.INT, 10), "arr");
        TypecastExpr castExpr = new TypecastExpr(new PointerType(BaseType.INT), new VarExpr("arr"));
        VarDecl ptr = new VarDecl(new PointerType(BaseType.INT), "ptr");
        Assign assign = new Assign(new VarExpr("ptr"), castExpr);
        ExprStmt stmt = new ExprStmt(assign);
        Block block = new Block(new ArrayList<>(List.of(arr, ptr)), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemTypecastArrayToPointer: Number of semantic errors: " + sem.getNumErrors());
    }
}