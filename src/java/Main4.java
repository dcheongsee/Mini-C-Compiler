import ast.ASTPrinter;
import ast.Program;
import gen.CodeGenerator;
import gen.asm.AssemblyParser;
import gen.asm.AssemblyPass;
import gen.asm.AssemblyProgram;
import lexer.Scanner;
import lexer.Token;
import lexer.Tokeniser;
import parser.Parser;
import regalloc.GraphColouringRegAlloc;
import regalloc.NaiveRegAlloc;
import sem.SemanticAnalyzer;

import java.io.*;


/**
 * This is the entry point to the compiler. This files should not be modified.
 */
public class Main4 {

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
        LEXER, PARSER, AST, SEMANTICANALYSIS, GEN, REGALLOC
    }

    private enum RegAllocMode {
        NONE, NAIVE, GRAPH_COLOURING
    }


    private static void usage() {
        System.out.println("Usage: java "+ Main4.class.getSimpleName()+" pass inputfile [outputfile]");
        System.out.println("where pass is either: -lexer, -parser, -ast, -sem, -gen [naive|colour], -regalloc naive|colour");
        System.out.println("if -ast, -gen or -regalloc is chosen, the output file must be specified");
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
        RegAllocMode regAllocMode = RegAllocMode.NONE;
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
                ensureArgExists(args, curArgCnt);
                if (args[curArgCnt].equals("naive")) {
                    regAllocMode = RegAllocMode.NAIVE;
                    curArgCnt++;
                } else if (args[curArgCnt].equals("colour")) {
                    regAllocMode = RegAllocMode.GRAPH_COLOURING;
                    curArgCnt++;
                }
                break;
            case "-regalloc":
                mode = Mode.REGALLOC;
                curArgCnt++;
                ensureArgExists(args, curArgCnt);
                if (args[curArgCnt].equals("naive")) {
                    regAllocMode = RegAllocMode.NAIVE;
                    curArgCnt++;
                } else if (args[curArgCnt].equals("colour")) {
                    regAllocMode = RegAllocMode.GRAPH_COLOURING;
                    curArgCnt++;
                }
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

            AssemblyPass regAlloc = switch (regAllocMode) {
                case NONE -> AssemblyPass.NOP;
                case NAIVE -> NaiveRegAlloc.INSTANCE;
                case GRAPH_COLOURING -> GraphColouringRegAlloc.INSTANCE;
            };
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

         else if (mode == Mode.REGALLOC) {
            ensureArgExists(args, curArgCnt);
            File outputFile = new File(args[curArgCnt]);
            curArgCnt++;

            AssemblyPass regAlloc = switch (regAllocMode) {
                case NONE -> AssemblyPass.NOP;
                case NAIVE -> NaiveRegAlloc.INSTANCE;
                case GRAPH_COLOURING -> GraphColouringRegAlloc.INSTANCE;
            };

            AssemblyProgram program;
            try {
                var reader = new FileReader(inputFile);
                program = AssemblyParser.readAssemblyProgram(new BufferedReader(reader));
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File " + inputFile + " does not exist.");
                System.exit(FILE_NOT_FOUND);
                return;
            } catch (IOException e) {
                System.out.println("An I/O exception occurred when reading " + inputFile + ".");
                System.exit(IO_EXCEPTION);
                return;
            }

            var programWithoutVRegs = regAlloc.apply(program);

            PrintWriter writer;
            try {
                writer = new PrintWriter(outputFile);
            } catch (FileNotFoundException e) {
                System.out.println("Cannot write to output file " + outputFile + ".");
                System.exit(FILE_NOT_FOUND);
                return;
            }
            programWithoutVRegs.print(writer);
            writer.close();
        }

        else {
            System.exit(MODE_FAIL);
        }
    }
}
