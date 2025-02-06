package tests2;

import ast.Program;
import lexer.Scanner;
import lexer.Tokeniser;
import parser.Parser;
import sem.SemanticAnalyzer;

import java.io.File;
import java.io.FileWriter;

public class SemAssignmentExpression {
    public static void main(String[] args) {
        try {
            String source = "int main() { char c; c = 100; return 0; }";
            File tempFile = File.createTempFile("source", ".txt");
            tempFile.deleteOnExit();

            FileWriter fw = new FileWriter(tempFile);
            fw.write(source);
            fw.close();

            Scanner scanner = new Scanner(tempFile);
            Tokeniser tokeniser = new Tokeniser(scanner);
            Parser parser = new Parser(tokeniser);
            Program prog = parser.parse();

            SemanticAnalyzer sa = new SemanticAnalyzer();
            sa.analyze(prog);

            System.out.println("SemAssignmentExpression error count: " + sa.getNumErrors());
            // expected output is error count = 1 (assignment type mismatch)
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}