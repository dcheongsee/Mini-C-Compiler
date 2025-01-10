import lexer.Scanner;
import lexer.Token;
import lexer.Tokeniser;
import parser.Parser;

import java.io.*;


/**
 * This is the entry point to the compiler. This files should not be modified.
 */
public class Main1 {

    private static final String LOGFILE = "out.log";
    private static final int UNKNOWN_EXCEPTION = 1;
    private static final int FILE_NOT_FOUND = 2;
    private static final int IO_EXCEPTION   = 3;
    private static final int MODE_FAIL      = 254;
    private static final int LEXER_FAIL     = 250;
    private static final int PARSER_FAIL    = 245;
    private static final int PASS           = 0;
    
    private enum Mode {
        LEXER, PARSER
    }


    private static void usage() {
        System.out.println("Usage: java "+ Main1.class.getSimpleName()+" pass inputfile");
        System.out.println("where pass is either: -lexer, -parser");
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

        else if (mode == Mode.PARSER) {
            Parser parser = new Parser(tokeniser);
            parser.parse();

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
            System.exit(PASS);

        }

        else {
        	System.exit(MODE_FAIL);
        }
    }
}
