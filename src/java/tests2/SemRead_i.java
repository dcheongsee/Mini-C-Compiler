package tests2;
import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;
public class SemRead_i {
    public static void main(String[] args) {
        VarDecl varX = new VarDecl(BaseType.INT, "x");
        FunCallExpr callReadI = new FunCallExpr("read_i", new ArrayList<>());
        Assign assign = new Assign(new VarExpr("x"), callReadI);
        ExprStmt stmt = new ExprStmt(assign);
        Block block = new Block(new ArrayList<>(List.of(varX)), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemRead_i: Number of semantic errors: " + sem.getNumErrors());
    }
}