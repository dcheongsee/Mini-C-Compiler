import ast.ASTPrinter;
import ast.Program;
import gen.CodeGenerator;
import gen.asm.AssemblyPass;
import lexer.Scanner;
import lexer.Token;
import lexer.Tokeniser;
import parser.Parser;
import regalloc.NaiveRegAlloc;
import sem.SemanticAnalyzer;

import java.io.*;


/**
 * This is the entry point to the compiler. This files should not be modified.
 */
public class Main3 {

    private static final String LOGFILE = "out.log";
    private static final int UNKNOWN_EXCEPTION = 1;
    private static final int FILE_NOT_FOUND = 2;
    private static final int IO_EXCEPTION   = 3;
    private static final int MODE_FAIL      = 254;
    private static final int LEXER_FAIL     = 250;
    private static final int PARSER_FAIL    = 245;
    private static final int SEM_FAIL       = 240;
    private static final int PASS           = 0;

    private enum Mode {
        LEXER, PARSER, AST, SEMANTICANALYSIS, GEN
    }


    private static void usage() {
        System.out.println("Usage: java "+ Main3.class.getSimpleName()+" pass inputfile [outputfile]");
        System.out.println("where pass is either: -lexer, -parser, -ast, -sem, -gen");
        System.out.println("if -ast or -gen is chosen, the output file must be specified");
        System.exit(-1);
    }

    private static void ensureArgExists(String[] args, int num) {
        if (num >= args.length)
            usage();
    }


    private static void logThrowableWithoutMessage(Throwable t) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(LOGFILE));
            bw.write("java.lang.Throwable:");
            bw.newLine();
            for (StackTraceElement ste : t.getStackTrace()) {
                bw.write(ste.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(IO_EXCEPTION);
        }
    }
    public static void main(String[] args)  {
        try {
            compile(args);
        }  catch (Throwable t) {
            t.printStackTrace();
            logThrowableWithoutMessage(t);
            System.exit(UNKNOWN_EXCEPTION);
        }
    }

    public static void compile(String[] args) {

        ensureArgExists(args, 0);

        Mode mode = null;
        int curArgCnt = 0;
        switch (args[curArgCnt]) {
            case "-lexer":
                mode = Mode.LEXER;
                curArgCnt++;
                break;
            case "-parser":
                mode = Mode.PARSER;
                curArgCnt++;
                break;
            case "-ast":
                if (args.length < 3)
                    usage();
                mode = Mode.AST;
                curArgCnt++;
                break;
            case "-sem":
                mode = Mode.SEMANTICANALYSIS;
                curArgCnt++;
                break;
            case "-gen":
                mode = Mode.GEN;
                curArgCnt++;
                break;
            default:
                usage();
                break;
        }

        ensureArgExists(args, curArgCnt);
        File inputFile = new File(args[curArgCnt]);
        curArgCnt++;

        Scanner scanner;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("File "+inputFile+" does not exist.");
            System.exit(FILE_NOT_FOUND);
            return;
        }

        Tokeniser tokeniser = new Tokeniser(scanner);
        if (mode == Mode.LEXER) {
            for (Token t = tokeniser.nextToken(); t.category != Token.Category.EOF; t = tokeniser.nextToken())
                System.out.println(t);

            if (tokeniser.hasErrors()) {
                System.out.println("Lexing: failed (" + tokeniser.getNumErrors() + " errors)");
                System.exit(LEXER_FAIL);
                return;
            }

            System.out.println("Lexing: pass");
            System.exit(PASS);
        }

        else if (mode == Mode.PARSER || mode == Mode.AST || mode == Mode.SEMANTICANALYSIS || mode == Mode.GEN) {
            Parser parser = new Parser(tokeniser);
            Program programAst = parser.parse();

            if (tokeniser.hasErrors()) {
                System.out.println("Lexing: failed (" + tokeniser.getNumErrors() + " errors)");
                System.exit(LEXER_FAIL);
                return;
            }
            if (parser.hasErrors()) {
                System.out.println("Parsing: failed (" + parser.getNumErrors() + " errors)");
                System.exit(PARSER_FAIL);
                return;
            }

            System.out.println("Parsing: pass");

            if (mode == Mode.PARSER) { // nothing more to do
                System.exit(PASS);
                return;
            }

            if (mode == Mode.AST) { // dump the AST to a file and exit
                ensureArgExists(args, curArgCnt);
                File outputFile = new File(args[curArgCnt]);
                curArgCnt++;

                PrintWriter writer;
                try {
                    writer = new PrintWriter(outputFile);
                } catch (FileNotFoundException e) {
                    System.out.println("File "+outputFile+" does not exist.");
                    System.exit(FILE_NOT_FOUND);
                    return;
                }
                new ASTPrinter(writer).visit(programAst);
                writer.close();

                System.exit(PASS);
                return;
            }

            assert(mode == Mode.SEMANTICANALYSIS || mode == Mode.GEN);

            SemanticAnalyzer sem = new SemanticAnalyzer();
            sem.analyze(programAst);
            if (sem.hasErrors()) {
                System.out.println("Semantic analysis: Failed (" + sem.getNumErrors() + " errors)");
                System.exit(SEM_FAIL);
            }

            System.out.println("Semantic analysis: Pass");
            if (mode == Mode.SEMANTICANALYSIS) { // nothing more to do
                System.exit(PASS);
                return;
            }


            assert(mode == mode.GEN);

            AssemblyPass regAlloc = NaiveRegAlloc.INSTANCE;
            CodeGenerator codegen = new CodeGenerator(regAlloc);

            ensureArgExists(args, curArgCnt);
            File outputFile = new File(args[curArgCnt]);
            curArgCnt++;

            try {
                codegen.emitProgram(programAst, outputFile);
            } catch (FileNotFoundException e) {
                System.out.println("File "+outputFile.toString()+" does not exist.");
                System.exit(FILE_NOT_FOUND);
            }

            System.exit(PASS);
        }

        else {
            System.exit(MODE_FAIL);
        }
    }
}
