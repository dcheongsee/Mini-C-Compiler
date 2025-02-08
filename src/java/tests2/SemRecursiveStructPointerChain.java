package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemRecursiveStructPointerChain {
    public static void main(String[] args) {
        VarDecl ageField = new VarDecl(BaseType.INT, "age");
        VarDecl friendField = new VarDecl(new PointerType(new StructType("Person")), "friend");
        StructTypeDecl structPerson = new StructTypeDecl("Person", new ArrayList<>(List.of(ageField, friendField)));
        VarDecl varP = new VarDecl(new StructType("Person"), "p");
        Assign assignAge = new Assign(new FieldAccessExpr(new VarExpr("p"), "age"), new IntLiteral(30));
        Assign assignFriend = new Assign(new FieldAccessExpr(new VarExpr("p"), "friend"), new AddressOfExpr(new VarExpr("p")));
        FieldAccessExpr p_age = new FieldAccessExpr(new VarExpr("p"), "age");
        FieldAccessExpr p_friend = new FieldAccessExpr(new VarExpr("p"), "friend");
        ValueAtExpr derefFriend = new ValueAtExpr(p_friend);
        FieldAccessExpr friend_age = new FieldAccessExpr(derefFriend, "age");
        BinOp addExpr = new BinOp(p_age, Op.ADD, friend_age);
        VarDecl varResult = new VarDecl(BaseType.INT, "result");
        Assign assignResult = new Assign(new VarExpr("result"), addExpr);
        ExprStmt stmtResult = new ExprStmt(assignResult);
        Block block = new Block(new ArrayList<>(List.of(varP, varResult)),
                List.of(new ExprStmt(assignAge), new ExprStmt(assignFriend), stmtResult));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(structPerson, mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemRecursiveStructPointerChain: Number of semantic errors: " + sem.getNumErrors());
    }
}