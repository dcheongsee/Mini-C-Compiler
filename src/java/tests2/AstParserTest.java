package tests2;

import ast.ASTPrinter;
import ast.Program;
import lexer.Scanner;
import lexer.Tokeniser;
import parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AstParserTest {
    public static void main(String[] args) {
        try {
            String source = "struct node_t { int field1; char field2; }; int main() { return 0; }";

            File tempFile = File.createTempFile("source", ".txt");
            tempFile.deleteOnExit();

            FileWriter fw = new FileWriter(tempFile);
            fw.write(source);
            fw.close();

            Scanner scanner = new Scanner(tempFile);

            Tokeniser tokeniser = new Tokeniser(scanner);

            Parser parser = new Parser(tokeniser);

            Program prog = parser.parse();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ASTPrinter printer = new ASTPrinter(pw);
            printer.visit(prog);
            pw.flush();

            System.out.println("AstParserTest2 output:");
            System.out.println(sw.toString());
            // expected output
            // Program(StructTypeDecl(StructType(node_t),VarDecl(INT,field1),VarDecl(CHAR,field2)),FunDef(INT,main,Block(Return(IntLiteral(0)))))
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}