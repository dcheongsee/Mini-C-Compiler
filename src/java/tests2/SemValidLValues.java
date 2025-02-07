package tests2;
import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;
public class SemValidLValues {
    public static void main(String[] args) {
        VarDecl varA = new VarDecl(BaseType.INT, "a");
        VarDecl varB = new VarDecl(BaseType.INT, "b");
        Assign assign1 = new Assign(new VarExpr("a"), new IntLiteral(10));
        Assign assign2 = new Assign(new VarExpr("b"), new VarExpr("a"));
        Block block = new Block(new ArrayList<>(List.of(varA, varB)), List.of(new ExprStmt(assign1), new ExprStmt(assign2)));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemValidLValues: Number of semantic errors: " + sem.getNumErrors());
    }
}