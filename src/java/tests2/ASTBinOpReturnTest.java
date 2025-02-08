package tests2;

import ast.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ASTBinOpReturnTest {
    public static void main(String[] args) {

        BinOp addExpr = new BinOp(new IntLiteral(10), Op.ADD, new IntLiteral(20));
        BinOp subExpr = new BinOp(new IntLiteral(30), Op.SUB, new IntLiteral(5));
        BinOp compExpr = new BinOp(addExpr, Op.GT, subExpr);
        BinOp orExpr = new BinOp(compExpr, Op.OR, new IntLiteral(0));
        BinOp mulExpr = new BinOp(orExpr, Op.MUL, new IntLiteral(2));
        BinOp modExpr = new BinOp(new IntLiteral(5), Op.MOD, new IntLiteral(2));
        BinOp finalExpr = new BinOp(mulExpr, Op.ADD, modExpr);

        Return ret = new Return(finalExpr);
        List<Stmt> stmts = new ArrayList<>();
        stmts.add(ret);
        Block block = new Block(new ArrayList<VarDecl>(), stmts);
        FunDef mainFun = new FunDef(BaseType.INT, "main", new ArrayList<>(), block);
        Program prog = new Program(new ArrayList<>(List.of(mainFun)));

        PrintWriter writer = new PrintWriter(System.out, true);
        ASTPrinter printer = new ASTPrinter(writer);
        printer.visit(prog);
        writer.println();
    }
}