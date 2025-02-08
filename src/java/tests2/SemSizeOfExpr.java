package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemSizeOfExpr {
    public static void main(String[] args) {
        SizeOfExpr sizeExpr = new SizeOfExpr(new PointerType(BaseType.INT));
        VarDecl varI = new VarDecl(BaseType.INT, "i");
        Assign assign = new Assign(new VarExpr("i"), sizeExpr);
        ExprStmt stmt = new ExprStmt(assign);
        Block block = new Block(new ArrayList<>(List.of(varI)), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemSizeOfExpr: Number of semantic errors: " + sem.getNumErrors());
    }
}