package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemFibonacci {
    public static void main(String[] args) {
        VarDecl paramN = new VarDecl(BaseType.INT, "n");
        BinOp condition = new BinOp(new VarExpr("n"), Op.LT, new IntLiteral(2));
        Return retThen = new Return(new VarExpr("n"));
        BinOp nMinus1 = new BinOp(new VarExpr("n"), Op.SUB, new IntLiteral(1));
        FunCallExpr callFib1 = new FunCallExpr("fibonacci", List.of(nMinus1));
        BinOp nMinus2 = new BinOp(new VarExpr("n"), Op.SUB, new IntLiteral(2));
        FunCallExpr callFib2 = new FunCallExpr("fibonacci", List.of(nMinus2));
        BinOp elseAdd = new BinOp(callFib1, Op.ADD, callFib2);
        Return retElse = new Return(elseAdd);
        If ifStmt = new If(condition, retThen, retElse);
        Block block = new Block(new ArrayList<>(), List.of(ifStmt));
        FunDef fibDef = new FunDef(BaseType.INT, "fibonacci", new ArrayList<>(List.of(paramN)), block);
        Program prog = new Program(new ArrayList<>(List.of(fibDef)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemFibonacci: Number of semantic errors: " + sem.getNumErrors());
    }
}