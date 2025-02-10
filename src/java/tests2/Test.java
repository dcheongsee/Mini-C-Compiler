package tests2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import lexer.Scanner;
import lexer.Tokeniser;
import parser.Parser;
import sem.SemanticAnalyzer;
import ast.*;

public class Test {

    public static void main(String[] args) {
        System.out.println("===== Tricky Arithmetic Interpreter Test =====");
        boolean arithmeticSuccess = runArithmeticTest();
        System.out.println("Arithmetic Interpreter Test: " + (arithmeticSuccess ? "Passed" : "Failed"));

        System.out.println("\n===== Tricky Semantic Analyzer Test =====");
        boolean semanticSuccess = runSemanticTest();
        System.out.println("Semantic Analyzer Test: " + (semanticSuccess ? "Passed" : "Failed"));

        System.out.println("\n===== Escaped Characters Test =====");
        boolean escapedCharSuccess = runEscapedCharTest();
        System.out.println("Escaped Characters Test: " + (escapedCharSuccess ? "Passed" : "Failed"));
    }

    private static boolean runArithmeticTest() {
        String source =
                "void main() {\n" +
                        "    int result;\n" +
                        "    result = -1 + +2 * (3 + (int)(4 / 2) - -5) % 3;\n" +
                        "    print_i(result);\n" +
                        "}\n";
        try {
            File tempFile = createTempFile(source);
            Scanner scanner = new Scanner(tempFile);
            Tokeniser tokeniser = new Tokeniser(scanner);
            Parser parser = new Parser(tokeniser);
            Program prog = parser.parse();

            SemanticAnalyzer semAnalyzer = new SemanticAnalyzer();
            semAnalyzer.analyze(prog);
            if (semAnalyzer.getNumErrors() != 0) {
                System.out.println("Semantic errors detected in arithmetic test.");
                return false;
            }

            FunDef mainFun = null;
            for (Decl d : prog.decls) {
                if (d instanceof FunDef) {
                    FunDef fd = (FunDef) d;
                    if ("main".equals(fd.name)) {
                        mainFun = fd;
                        break;
                    }
                }
            }
            if (mainFun == null) {
                System.out.println("No main function found in arithmetic test.");
                return false;
            }

            Expr arithmeticExpr = null;
            for (Stmt s : mainFun.block.stmts) {
                if (s instanceof ExprStmt) {
                    Expr e = ((ExprStmt) s).expr;
                    if (e instanceof Assign) {
                        arithmeticExpr = ((Assign) e).right;
                        break;
                    }
                }
            }
            if (arithmeticExpr == null) {
                System.out.println("No arithmetic expression found in arithmetic test.");
                return false;
            }

            int result = evaluateExpr(arithmeticExpr);
            System.out.println("Evaluated result: " + result);
            return result == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean runSemanticTest() {
        String source =
                "struct Node {\n" +
                        "    int data;\n" +
                        "    struct Node * next;\n" +
                        "};\n" +
                        "\n" +
                        "int getData(struct Node * n) {\n" +
                        "    return (*n).data;\n" +
                        "}\n" +
                        "\n" +
                        "void main() {\n" +
                        "    struct Node n1;\n" +
                        "    struct Node n2;\n" +
                        "    n1.data = 100;\n" +
                        "    n2.data = 200;\n" +
                        "    n1.next = &n2;\n" +
                        "    print_i(getData(&n1));\n" +
                        "    print_i(getData(n1.next));\n" +
                        "}\n";
        try {
            File tempFile = createTempFile(source);
            Scanner scanner = new Scanner(tempFile);
            Tokeniser tokeniser = new Tokeniser(scanner);
            Parser parser = new Parser(tokeniser);
            Program prog = parser.parse();

            SemanticAnalyzer semAnalyzer = new SemanticAnalyzer();
            semAnalyzer.analyze(prog);
            int errors = semAnalyzer.getNumErrors();
            if (errors != 0) {
                System.out.println("Semantic errors detected in semantic test: " + errors);
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean runEscapedCharTest() {
        String source =
                "void   main()   {\n" +
                        "    char newline  ;\n" +
                        "    char tab ;\n" +
                        "    char backslash ;\n" +
                        "    newline = '\\n' ;\n" +
                        "    tab = '\\t' ;\n" +
                        "    backslash = '\\\\' ;\n" +
                        "    print_i((int)newline) ;\n" +
                        "    print_i((int)tab) ;\n" +
                        "    print_i((int)backslash) ;\n" +
                        "    print_i( sizeof( \"Hello\\nWorld\\t!\" ) ) ;\n" +
                        "}\n";
        try {
            File tempFile = createTempFile(source);
            Scanner scanner = new Scanner(tempFile);
            Tokeniser tokeniser = new Tokeniser(scanner);
            Parser parser = new Parser(tokeniser);
            Program prog = parser.parse();

            SemanticAnalyzer semAnalyzer = new SemanticAnalyzer();
            semAnalyzer.analyze(prog);
            int errors = semAnalyzer.getNumErrors();
            if (errors != 0) {
                System.out.println("Semantic errors detected in escaped characters test: " + errors);
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static File createTempFile(String source) throws IOException {
        File tempFile = File.createTempFile("testProgram", ".c");
        tempFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(source);
        }
        return tempFile;
    }

    private static int evaluateExpr(Expr expr) {
        if (expr instanceof IntLiteral) {
            return ((IntLiteral) expr).value;
        } else if (expr instanceof BinOp) {
            BinOp bin = (BinOp) expr;
            int left = evaluateExpr(bin.left);
            int right = evaluateExpr(bin.right);
            switch (bin.op) {
                case ADD: return left + right;
                case SUB: return left - right;
                case MUL: return left * right;
                case DIV: return left / right;
                case MOD: return left % right;
                default:
                    throw new RuntimeException("Unsupported operator in evaluation: " + bin.op);
            }
        } else if (expr instanceof TypecastExpr) {
            return evaluateExpr(((TypecastExpr) expr).expr);
        } else {
            throw new RuntimeException("Unsupported expression type for evaluation: " + expr.getClass());
        }
    }
}