package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        VarDecl nDecl      = new VarDecl(BaseType.INT, "n");
        VarDecl firstDecl  = new VarDecl(BaseType.INT, "first");
        VarDecl secondDecl = new VarDecl(BaseType.INT, "second");
        VarDecl nextDecl   = new VarDecl(BaseType.INT, "next");
        VarDecl cDecl      = new VarDecl(BaseType.INT, "c");
        VarDecl tDecl      = new VarDecl(BaseType.CHAR, "t");

        FunCallExpr read_iCall = new FunCallExpr("read_i", List.of());
        ExprStmt assign_n = new ExprStmt(new Assign(new VarExpr("n"), read_iCall));

        ExprStmt assign_first = new ExprStmt(new Assign(new VarExpr("first"), new IntLiteral(0)));
        ExprStmt assign_second = new ExprStmt(new Assign(new VarExpr("second"), new IntLiteral(1)));

        ExprStmt print_s_first = new ExprStmt(new FunCallExpr("print_s", List.of(new StrLiteral("First "))));
        ExprStmt print_i_n = new ExprStmt(new FunCallExpr("print_i", List.of(new VarExpr("n"))));
        ExprStmt print_s_terms = new ExprStmt(new FunCallExpr("print_s", List.of(new StrLiteral(" terms of Fibonacci series are : "))));

        ExprStmt assign_c = new ExprStmt(new Assign(new VarExpr("c"), new IntLiteral(0)));

        BinOp whileCond = new BinOp(new VarExpr("c"), Op.LT, new VarExpr("n"));
        BinOp ifCond = new BinOp(new VarExpr("c"), Op.LE, new IntLiteral(1));
        ExprStmt thenAssign_next = new ExprStmt(new Assign(new VarExpr("next"), new VarExpr("c")));

        ExprStmt elseAssign_next = new ExprStmt(new Assign(new VarExpr("next"), new BinOp(new VarExpr("first"), Op.ADD, new VarExpr("second"))));
        ExprStmt elseAssign_first = new ExprStmt(new Assign(new VarExpr("first"), new VarExpr("second")));
        ExprStmt elseAssign_second = new ExprStmt(new Assign(new VarExpr("second"), new VarExpr("next")));
        Block elseBlock = new Block(new ArrayList<>(), List.of(elseAssign_next, elseAssign_first, elseAssign_second));

        If ifStmt = new If(ifCond, thenAssign_next, elseBlock);

        ExprStmt print_i_next = new ExprStmt(new FunCallExpr("print_i", List.of(new VarExpr("next"))));
        ExprStmt print_s_space = new ExprStmt(new FunCallExpr("print_s", List.of(new StrLiteral(" "))));
        ExprStmt assign_c_inc = new ExprStmt(new Assign(new VarExpr("c"), new BinOp(new VarExpr("c"), Op.ADD, new IntLiteral(1))));
        Block whileBody = new Block(new ArrayList<>(), List.of(ifStmt, print_i_next, print_s_space, assign_c_inc));
        While whileStmt = new While(whileCond, whileBody);

        List<VarDecl> decls = new ArrayList<>();
        decls.add(nDecl);
        decls.add(firstDecl);
        decls.add(secondDecl);
        decls.add(nextDecl);
        decls.add(cDecl);
        decls.add(tDecl);

        List<Stmt> stmts = new ArrayList<>();
        stmts.add(assign_n);
        stmts.add(assign_first);
        stmts.add(assign_second);
        stmts.add(print_s_first);
        stmts.add(print_i_n);
        stmts.add(print_s_terms);
        stmts.add(assign_c);
        stmts.add(whileStmt);

        Block mainBlock = new Block(decls, stmts);

        FunDef mainFun = new FunDef(BaseType.VOID, "main", new ArrayList<>(), mainBlock);

        Program program = new Program(List.of(mainFun));
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(program);
        pw.flush();
        String output = sw.toString();
        System.out.println("AST Printer Output:");
        System.out.println(output);


        String expected = "Program(FunDef(VOID,main,Block(" +
                "VarDecl(INT,n)," +
                "VarDecl(INT,first)," +
                "VarDecl(INT,second)," +
                "VarDecl(INT,next)," +
                "VarDecl(INT,c)," +
                "VarDecl(CHAR,t)," +
                "ExprStmt(Assign(VarExpr(n),FunCallExpr(read_i())))," +
                "ExprStmt(Assign(VarExpr(first),IntLiteral(0)))," +
                "ExprStmt(Assign(VarExpr(second),IntLiteral(1)))," +
                "ExprStmt(FunCallExpr(print_s,StrLiteral(First )))," +
                "ExprStmt(FunCallExpr(print_i,VarExpr(n)))," +
                "ExprStmt(FunCallExpr(print_s,StrLiteral( terms of Fibonacci series are : )))," +
                "ExprStmt(Assign(VarExpr(c),IntLiteral(0)))," +
                "ExprStmt(While(" +
                "BinOp(VarExpr(c),LT,VarExpr(n))," +
                "Block(" +
                "If(" +
                "BinOp(VarExpr(c),LE,IntLiteral(1))," +
                "ExprStmt(Assign(VarExpr(next),VarExpr(c)))," +
                "Block(" +
                "ExprStmt(Assign(VarExpr(next),BinOp(VarExpr(first),ADD,VarExpr(second))))," +
                "ExprStmt(Assign(VarExpr(first),VarExpr(second)))," +
                "ExprStmt(Assign(VarExpr(second),VarExpr(next)))" +
                ")" +
                ")," +
                "ExprStmt(FunCallExpr(print_i,VarExpr(next)))," +
                "ExprStmt(FunCallExpr(print_s,StrLiteral( )))," +
                "ExprStmt(Assign(VarExpr(c),BinOp(VarExpr(c),ADD,IntLiteral(1))))" +
                ")" +
                "))" +
                "))";
        System.out.println("\nExpected Output:");
        System.out.println(expected);

        if (output.equals(expected)) {
            System.out.println("\nTest Passed: Output matches expected output.");
        } else {
            System.out.println("\nTest Failed: Output does not match expected output.");
        }
    }
}