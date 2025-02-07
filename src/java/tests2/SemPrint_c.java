package tests2;
import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;
public class SemPrint_c {
    public static void main(String[] args) {
        FunCallExpr callPrintC = new FunCallExpr("print_c", List.of(new ChrLiteral('A')));
        ExprStmt stmt = new ExprStmt(callPrintC);
        Block block = new Block(new ArrayList<>(), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemPrint_c: Number of semantic errors: " + sem.getNumErrors());
    }
}