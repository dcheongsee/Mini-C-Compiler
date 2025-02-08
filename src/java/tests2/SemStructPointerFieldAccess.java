package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemStructPointerFieldAccess {
    public static void main(String[] args) {
        VarDecl fieldX = new VarDecl(BaseType.INT, "x");
        StructTypeDecl structS = new StructTypeDecl("S", new ArrayList<>(List.of(fieldX)));
        VarDecl varS = new VarDecl(new StructType("S"), "s");
        PointerType ptrToS = new PointerType(new StructType("S"));
        VarDecl varPS = new VarDecl(ptrToS, "ps");
        Assign assignPS = new Assign(new VarExpr("ps"), new AddressOfExpr(new VarExpr("s")));
        FieldAccessExpr fieldAccess = new FieldAccessExpr(new ValueAtExpr(new VarExpr("ps")), "x");
        Assign assignField = new Assign(fieldAccess, new IntLiteral(42));
        Block block = new Block(new ArrayList<>(List.of(varS, varPS)), List.of(new ExprStmt(assignPS), new ExprStmt(assignField)));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(structS, mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemStructPointerFieldAccess: Number of semantic errors: " + sem.getNumErrors());
    }
}