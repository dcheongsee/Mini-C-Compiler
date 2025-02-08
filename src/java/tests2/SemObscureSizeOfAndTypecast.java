package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemObscureSizeOfAndTypecast {
    public static void main(String[] args) {
        VarDecl varX = new VarDecl(BaseType.INT, "x");
        SizeOfExpr sizeExpr = new SizeOfExpr(new PointerType(BaseType.INT));
        TypecastExpr castExpr = new TypecastExpr(BaseType.INT, new IntLiteral(5));
        BinOp addExpr = new BinOp(sizeExpr, Op.ADD, castExpr);
        Assign assign = new Assign(new VarExpr("x"), addExpr);
        ExprStmt stmt = new ExprStmt(assign);
        Block block = new Block(new ArrayList<>(List.of(varX)), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemObscureSizeOfAndTypecast: Number of semantic errors: " + sem.getNumErrors());
    }
}