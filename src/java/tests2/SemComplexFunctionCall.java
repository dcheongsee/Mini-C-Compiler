package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemComplexFunctionCall {
    public static void main(String[] args) {
        FunDecl fooDecl = new FunDecl(BaseType.INT, "foo", new ArrayList<>(List.of(
                new VarDecl(BaseType.INT, "a"),
                new VarDecl(BaseType.CHAR, "c"),
                new VarDecl(BaseType.INT, "b")
        )));
        VarDecl varRes = new VarDecl(BaseType.INT, "res");
        FunCallExpr callFoo = new FunCallExpr("foo", new ArrayList<>(List.of(
                new IntLiteral(10),
                new ChrLiteral('A'),
                new TypecastExpr(BaseType.INT, new BinOp(new IntLiteral(5), Op.ADD, new IntLiteral(7)))
        )));
        Assign assignRes = new Assign(new VarExpr("res"), callFoo);
        ExprStmt stmt = new ExprStmt(assignRes);
        Block block = new Block(new ArrayList<>(List.of(varRes)), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(fooDecl, mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemComplexFunctionCall: Number of semantic errors: " + sem.getNumErrors());
    }
}