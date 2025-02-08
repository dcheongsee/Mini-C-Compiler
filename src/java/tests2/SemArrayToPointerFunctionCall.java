package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemArrayToPointerFunctionCall {
    public static void main(String[] args) {
        FunDecl fooDecl = new FunDecl(BaseType.INT, "foo", new ArrayList<>(List.of(
                new VarDecl(new PointerType(BaseType.INT), "p")
        )));
        VarDecl varRes = new VarDecl(BaseType.INT, "res");
        VarDecl arrDecl = new VarDecl(new ArrayType(BaseType.INT, 5), "arr");
        TypecastExpr castArr = new TypecastExpr(new PointerType(BaseType.INT), new VarExpr("arr"));
        FunCallExpr callFoo = new FunCallExpr("foo", new ArrayList<>(List.of(castArr)));
        Assign assignRes = new Assign(new VarExpr("res"), callFoo);
        ExprStmt stmt = new ExprStmt(assignRes);
        Block block = new Block(new ArrayList<>(List.of(arrDecl, varRes)), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(fooDecl, mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemArrayToPointerFunctionCall: Number of semantic errors: " + sem.getNumErrors());
    }
}