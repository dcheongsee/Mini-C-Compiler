package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemAssignExpr {
    public static void main(String[] args) {
        VarDecl varA = new VarDecl(BaseType.INT, "a");
        Assign assign1 = new Assign(new VarExpr("a"), new IntLiteral(10));
        Assign assign2 = new Assign(new VarExpr("a"), new IntLiteral(20));
        Assign chainedAssign = new Assign(new VarExpr("a"), assign2);
        Block block = new Block(new ArrayList<>(List.of(varA)), List.of(new ExprStmt(assign1), new ExprStmt(chainedAssign)));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemAssignExpr: Number of semantic errors: " + sem.getNumErrors());
    }
}