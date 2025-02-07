package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemStructDecl {
    public static void main(String[] args) {
        VarDecl field1 = new VarDecl(BaseType.INT, "x");
        VarDecl field2 = new VarDecl(BaseType.CHAR, "c");
        StructTypeDecl structS = new StructTypeDecl("S", new ArrayList<>(List.of(field1, field2)));
        Program prog = new Program(new ArrayList<>(List.of(structS)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemStructDecl: Number of semantic errors: " + sem.getNumErrors());
    }
}