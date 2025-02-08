package tests2;

import ast.*;
import sem.SemanticAnalyzer;
import java.util.ArrayList;
import java.util.List;

public class SemStructNesting {
    public static void main(String[] args) {
        VarDecl leafField = new VarDecl(BaseType.INT, "x");
        StructTypeDecl structLeaf = new StructTypeDecl("Leaf", new ArrayList<>(List.of(leafField)));
        VarDecl nodeFieldLeaf = new VarDecl(new StructType("Leaf"), "leaf");
        VarDecl nodeFieldX = new VarDecl(BaseType.INT, "x");
        StructTypeDecl structNode = new StructTypeDecl("Node", new ArrayList<>(List.of(nodeFieldLeaf, nodeFieldX)));
        VarDecl rootFieldNode = new VarDecl(new StructType("Node"), "node");
        VarDecl rootFieldX = new VarDecl(BaseType.INT, "x");
        StructTypeDecl structRoot = new StructTypeDecl("Root", new ArrayList<>(List.of(rootFieldNode, rootFieldX)));
        VarDecl varR = new VarDecl(new StructType("Root"), "r");
        FieldAccessExpr accessLeafX = new FieldAccessExpr(new FieldAccessExpr(new FieldAccessExpr(new VarExpr("r"), "node"), "leaf"), "x");
        FieldAccessExpr accessNodeX = new FieldAccessExpr(new FieldAccessExpr(new VarExpr("r"), "node"), "x");
        FieldAccessExpr accessRootX = new FieldAccessExpr(new VarExpr("r"), "x");
        BinOp addExpr1 = new BinOp(accessLeafX, Op.ADD, accessNodeX);
        BinOp addExpr2 = new BinOp(addExpr1, Op.ADD, accessRootX);
        VarDecl varResult = new VarDecl(BaseType.INT, "result");
        Assign assign = new Assign(new VarExpr("result"), addExpr2);
        ExprStmt stmt = new ExprStmt(assign);
        Block block = new Block(new ArrayList<>(List.of(varR, varResult)), List.of(stmt));
        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(structLeaf, structNode, structRoot, mainFun)));
        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.analyze(prog);
        System.out.println("SemStructNestingTricky2: Number of semantic errors: " + sem.getNumErrors());
    }
}