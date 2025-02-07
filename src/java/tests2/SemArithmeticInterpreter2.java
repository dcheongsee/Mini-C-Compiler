package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemArithmeticInterpreter2 {
    public static void main(String[] args) {
        VarDecl varA = new VarDecl(BaseType.INT, "a");
        VarDecl varB = new VarDecl(BaseType.INT, "b");
        Assign assignA8 = new Assign(new VarExpr("a"), new IntLiteral(8));
        Assign assignB3 = new Assign(new VarExpr("b"), new IntLiteral(3));
        BinOp aTimes2 = new BinOp(new VarExpr("a"), Op.MUL, new IntLiteral(2));
        BinOp bPlus4 = new BinOp(new VarExpr("b"), Op.ADD, new IntLiteral(4));
        Assign assignBPlus4 = new Assign(new VarExpr("b"), bPlus4);
        BinOp innerAdd = new BinOp(aTimes2, Op.ADD, assignBPlus4);
        Assign assignAComplex = new Assign(new VarExpr("a"), innerAdd);
        BinOp aDivB = new BinOp(new VarExpr("a"), Op.DIV, new VarExpr("b"));
        TypecastExpr castAdivB = new TypecastExpr(BaseType.INT, aDivB);
        BinOp subtraction = new BinOp(assignAComplex, Op.SUB, castAdivB);
        BinOp modulo = new BinOp(subtraction, Op.MOD, new IntLiteral(7));
        BinOp aPlusB = new BinOp(new VarExpr("a"), Op.ADD, new VarExpr("b"));
        BinOp aMinusB = new BinOp(new VarExpr("a"), Op.SUB, new IntLiteral(0));
        BinOp multiplication = new BinOp(aPlusB, Op.MUL, aMinusB);
        BinOp outerAdd = new BinOp(modulo, Op.ADD, multiplication);
        Return retStmt = new Return(outerAdd);
        Block block = new Block(new ArrayList<>(List.of(varA, varB)), List.of(new ExprStmt(assignA8), new ExprStmt(assignB3), retStmt));
        FunDef arithmeticFun = new FunDef(BaseType.INT, "arithmetic", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(arithmeticFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemArithmeticInterpreter: Number of semantic errors: " + sem.getNumErrors());
    }
}