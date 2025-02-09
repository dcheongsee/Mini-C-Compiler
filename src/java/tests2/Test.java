package tests2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import lexer.Scanner;
import ast.Program;
import lexer.Tokeniser;
import parser.Parser;
import sem.SemanticAnalyzer;

public class Test {
    public static void main(String[] args) {
        // descriptions for each test
        String[] testDescriptions = {
                "Arithmetic interpreter: evaluate 2 + 3 * (4 - 1) / 2",
                "Sort linked list: define struct Node and call sortList",
                "Pass multi-dimensional array: process 2D array (2x3)",
                "Complex function call: nested calls with arithmetic expressions",
                "Valid L-values: assignments to variable, pointer, and array element",
                "Struct declaration and use: struct Point with field accesses",
                "Struct nesting: struct Outer contains struct Inner",
                "Complex assignment expression: chained assignment with arithmetic"
        };

        String[] testPrograms = {
                // Test 1: Arithmetic interpreter.
                "void main() { " +
                        "  int a; " +
                        "  a = 2 + 3 * (4 - 1) / 2; " +
                        "}",

                // Test 2: Sort linked list.
                "struct Node { " +
                        "  int data; " +
                        "  struct Node *next; " +
                        "}; " +
                        "struct Node* sortList(struct Node* head) { " +
                        "  return head; " +
                        "} " +
                        "void main() { " +
                        "  struct Node *head; " +
                        "  head = sortList(head); " +
                        "}",

                // Test 3: Pass multi-dimensional array.
                "void process(int arr[2][3]) { " +
                        "  // No operation needed " +
                        "} " +
                        "void main() { " +
                        "  int matrix[2][3]; " +
                        "  process(matrix); " +
                        "}",

                // Test 4: Complex function call.
                "int add(int x, int y) { " +
                        "  return x + y; " +
                        "} " +
                        "int complexFunc(int a, int b, int c, int d) { " +
                        "  return add(a, b) * add(c, d); " +
                        "} " +
                        "void main() { " +
                        "  int result; " +
                        "  result = complexFunc(1 + 2, 3 * 4, 5 - 6, 7 / 8); " +
                        "}",

                // Test 5: Valid L-values.
                "void main() { " +
                        "  int a; " +
                        "  int *p; " +
                        "  int arr[3]; " +
                        "  a = 5; " +
                        "  p = (int*)&a; " +
                        "  *p = a + 1; " +
                        "  arr[0] = a; " +
                        "}",

                // Test 6: Struct declaration and use.
                "struct Point { " +
                        "  int x; " +
                        "  int y; " +
                        "}; " +
                        "void main() { " +
                        "  struct Point p; " +
                        "  p.x = 10; " +
                        "  p.y = 20; " +
                        "}",

                // Test 7: Struct nesting.
                "struct Inner { " +
                        "  int value; " +
                        "}; " +
                        "struct Outer { " +
                        "  struct Inner inner; " +
                        "  int extra; " +
                        "}; " +
                        "void main() { " +
                        "  struct Outer o; " +
                        "  o.inner.value = 100; " +
                        "  o.extra = 200; " +
                        "}",

                // Test 8: Complex assignment expression.
                "void main() { " +
                        "  int a; " +
                        "  int b; " +
                        "  a = b = (2 + 3) * 4; " +
                        "}"
        };

        // run tests
        for (int i = 0; i < testPrograms.length; i++) {
            System.out.println("-------------------------------------------------");
            System.out.println("Test " + (i + 1) + ": " + testDescriptions[i]);
            boolean success = runTest(testPrograms[i]);
            System.out.println("Result: " + (success ? "Passed" : "Failed (semantic errors detected)"));
            System.out.println();
        }
    }

    private static boolean runTest(String source) {
        try {
            File tempFile = createTempFile(source);
            Scanner scanner = new Scanner(tempFile);
            Tokeniser tokeniser = new Tokeniser(scanner);
            Parser parser = new Parser(tokeniser);
            Program prog = parser.parse();

            SemanticAnalyzer semAnalyzer = new SemanticAnalyzer();
            semAnalyzer.analyze(prog);

            int errors = semAnalyzer.getNumErrors();
            return errors == 0;
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
}