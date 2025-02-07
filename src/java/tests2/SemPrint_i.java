package tests2;
import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;
public class SemPrint_i {
    public static void main(String[] args) {
        FunCallExpr callPrintI = new FunCallExpr("print_i", List.of(new IntLiteral(123)));
        ExprStmt stmt = new ExprStmt(callPrintI);
        Block block = new Block(new ArrayList<>(), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemPrint_i: Number of semantic errors: " + sem.getNumErrors());
    }
}