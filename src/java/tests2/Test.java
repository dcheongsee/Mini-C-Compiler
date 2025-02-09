package tests2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import lexer.Scanner;  // using your custom lexer.Scanner
import ast.Program;
import lexer.Tokeniser;
import parser.Parser;
import sem.SemanticAnalyzer;

public class Test {
    public static void main(String[] args) {
        String[] testDescriptions = {
                "Final Extreme-Tricky Sort Linked List AST Test – Extra Whitespace Edition"
        };

        String[] testPrograms = {
                // MiniC source code as a String:
                "#include \"minic-stdlib.h\"\n" +
                        "\n" +
                        "struct Node {\n" +
                        "    int data;\n" +
                        "    struct Node *next;\n" +
                        "};\n" +
                        "\n" +
                        "struct Node* sortList(struct Node *head) {\n" +
                        "    struct Node *current;\n" +
                        "    int swapped;\n" +
                        "    int temp;\n" +
                        "\n" +
                        "    if (head == (struct Node *)0) {\n" +
                        "        return head;\n" +
                        "    }\n" +
                        "\n" +
                        "    while (1) {\n" +
                        "        swapped = 0;\n" +
                        "        current = head;\n" +
                        "        while (((*current).next) != (struct Node *)0) {\n" +
                        "            if ((( ( int ) ((*current).data) )) > (( ( int ) ((*((*current).next)).data) ))) {\n" +
                        "                temp = (( ( int ) ((*current).data) ));\n" +
                        "                (*current).data = (( ( int ) ((*((*current).next)).data) ));\n" +
                        "                (*((*current).next)).data = (( ( int ) (temp) ));\n" +
                        "                swapped = 1;\n" +
                        "            }\n" +
                        "            current = (*current).next;\n" +
                        "        }\n" +
                        "        if (swapped == 0) {\n" +
                        "            break;\n" +
                        "        }\n" +
                        "    }\n" +
                        "    return (struct Node*)head;\n" +
                        "}\n" +
                        "\n" +
                        "void main() {\n" +
                        "    /* Declarations for linked list nodes (each declared separately) */\n" +
                        "    struct Node n1;\n" +
                        "    struct Node n2;\n" +
                        "    struct Node n3;\n" +
                        "    struct Node *head;\n" +
                        "\n" +
                        "    /* Assignments for linked list nodes (occur after declarations) */\n" +
                        "    n1.data = 30;\n" +
                        "    n2.data = 10;\n" +
                        "    n3.data = 20;\n" +
                        "\n" +
                        "    n1.next = &n2;\n" +
                        "    n2.next = &n3;\n" +
                        "    n3.next = (struct Node *)0;\n" +
                        "\n" +
                        "    head = &n1;\n" +
                        "    head = sortList(head);\n" +
                        "\n" +
                        "    /* Print sorted list values with explicit casts and nested field accesses */\n" +
                        "    print_i((int)((( (*head).data) )));\n" +
                        "    print_i((int)((( (*((*head).next)).data) )));\n" +
                        "    print_i((int)((( (*((*((*head).next)).next)).data) )));\n" +
                        "}\n"
        };

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
        } catch(Exception e) {
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