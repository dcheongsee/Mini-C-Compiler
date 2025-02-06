package tests2;

import ast.Program;
import lexer.Scanner;
import lexer.Tokeniser;
import parser.Parser;
import sem.SemanticAnalyzer;

import java.io.File;
import java.io.FileWriter;

public class SemFunctionReturn {
    public static void main(String[] args) {
        try {
            String source = "void foo() { return 0; } int main() { return 0; }";
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

            System.out.println("SemFunctionReturn error count: " + sa.getNumErrors());
            // expected output is error count = 1 (void function returning a value)
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}