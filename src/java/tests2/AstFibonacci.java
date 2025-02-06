package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class AstFibonacci {
    public static void main(String[] args) {
        VarDecl param = new VarDecl(BaseType.INT, "n");
        List<VarDecl> params = new ArrayList<>();
        params.add(param);

        Expr condition = new BinOp(new VarExpr("n"), Op.LT, new IntLiteral(2));

        Return thenReturn = new Return(new VarExpr("n"));

        Expr nMinus1 = new BinOp(new VarExpr("n"), Op.SUB, new IntLiteral(1));
        FunCallExpr callFib1 = new FunCallExpr("fib", List.of(nMinus1));

        Expr nMinus2 = new BinOp(new VarExpr("n"), Op.SUB, new IntLiteral(2));
        FunCallExpr callFib2 = new FunCallExpr("fib", List.of(nMinus2));

        Expr addCalls = new BinOp(callFib1, Op.ADD, callFib2);
        Return elseReturn = new Return(addCalls);

        If ifStmt = new If(condition, thenReturn, elseReturn);

        Block block = new Block();
        block.vds = new ArrayList<>();
        block.stmts = new ArrayList<>();
        block.stmts.add(ifStmt);

        FunDef fibDef = new FunDef(BaseType.INT, "fib", params, block);

        List<Decl> decls = new ArrayList<>();
        decls.add(fibDef);
        Program prog = new Program(decls);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(prog);
        pw.flush();

        System.out.println("AstFibonacci output:");
        System.out.println(sw.toString());
        // expected
        // Program(FunDef(INT,fib,VarDecl(INT,n),Block(If(BinOp(VarExpr(n),LT,IntLiteral(2)),Return(VarExpr(n)),Return(BinOp(FunCallExpr(fib,BinOp(VarExpr(n),SUB,IntLiteral(1))),ADD,FunCallExpr(fib,BinOp(VarExpr(n),SUB,IntLiteral(2)))))))))
    }
}