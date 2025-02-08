package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemChainedTypecastAndAssign {
    public static void main(String[] args) {
        VarDecl varA = new VarDecl(BaseType.INT, "a");
        // expression: a = ( (int)(a = 5) + 10 )
        Assign innerAssign = new Assign(new VarExpr("a"), new IntLiteral(5));
        TypecastExpr castExpr = new TypecastExpr(BaseType.INT, innerAssign);
        BinOp addExpr = new BinOp(castExpr, Op.ADD, new IntLiteral(10));
        Assign outerAssign = new Assign(new VarExpr("a"), addExpr);
        ExprStmt stmt = new ExprStmt(outerAssign);
        Block block = new Block(new ArrayList<>(List.of(varA)), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemChainedTypecastAndAssign: Number of semantic errors: " + sem.getNumErrors());
    }
}