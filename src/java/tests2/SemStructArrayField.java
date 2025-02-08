package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemStructArrayField {
    public static void main(String[] args) {
        ArrayType arrType = new ArrayType(BaseType.INT, 5);
        VarDecl fieldArr = new VarDecl(arrType, "arr");
        StructTypeDecl structS = new StructTypeDecl("S", new ArrayList<>(List.of(fieldArr)));
        VarDecl varS = new VarDecl(new StructType("S"), "s");
        FieldAccessExpr accessArr = new FieldAccessExpr(new VarExpr("s"), "arr");
        ArrayAccessExpr arrayAccess = new ArrayAccessExpr(accessArr, new IntLiteral(2));
        Assign assign = new Assign(arrayAccess, new IntLiteral(100));
        Block block = new Block(new ArrayList<>(List.of(varS)), List.of(new ExprStmt(assign)));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(structS, mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemStructArrayField: Number of semantic errors: " + sem.getNumErrors());
    }
}