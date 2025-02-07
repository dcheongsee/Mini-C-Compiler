package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemPassIncorrectArrayDim {
    public static void main(String[] args) {
        VarDecl arrParam = new VarDecl(new ArrayType(BaseType.INT, 5), "arr");
        FunDecl fooDecl = new FunDecl(BaseType.VOID, "foo", new ArrayList<>(List.of(arrParam)));
        VarDecl myArr = new VarDecl(new ArrayType(BaseType.INT, 3), "myArr");
        FunCallExpr callFoo = new FunCallExpr("foo", List.of(new VarExpr("myArr")));
        ExprStmt stmtCallFoo = new ExprStmt(callFoo);
        Block block = new Block(new ArrayList<>(List.of(myArr)), List.of(stmtCallFoo));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(fooDecl, mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemPassIncorrectArrayDim: Number of semantic errors: " + sem.getNumErrors());
    }
}