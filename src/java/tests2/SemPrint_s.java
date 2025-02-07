package tests2;
import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;
public class SemPrint_s {
    public static void main(String[] args) {
        FunCallExpr callPrintS = new FunCallExpr("print_s", List.of(new ChrLiteral('H')));
        ExprStmt stmt = new ExprStmt(callPrintS);
        Block block = new Block(new ArrayList<>(), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemPrint_s: Number of semantic errors: " + sem.getNumErrors());
    }
}