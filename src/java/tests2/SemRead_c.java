package tests2;
import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;
public class SemRead_c {
    public static void main(String[] args) {
        VarDecl varC = new VarDecl(BaseType.CHAR, "c");
        FunCallExpr callReadC = new FunCallExpr("read_c", new ArrayList<>());
        Assign assign = new Assign(new VarExpr("c"), callReadC);
        ExprStmt stmt = new ExprStmt(assign);
        Block block = new Block(new ArrayList<>(List.of(varC)), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemRead_c: Number of semantic errors: " + sem.getNumErrors());
    }
}