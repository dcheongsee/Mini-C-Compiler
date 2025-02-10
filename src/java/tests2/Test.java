package tests2;

import ast.*;
import java.util.*;
import java.io.*;

public class Test {
    public static void main(String[] args) {
        testComplexASTSorting();
    }

    /**
     * This test builds a complex AST that includes:
     *  - A global VarDecl "globalHead" of type PointerType(StructType("node_t"))
     *  - A function definition "insert" with:
     *       * Two parameters added unsorted: "head" of type PointerType(PointerType(StructType("node_t")))
     *         and "elem" of type PointerType(StructType("node_t"))
     *       * A block containing unsorted variable declarations:
     *             "temp" of type PointerType(StructType("node_t"))
     *             "nodeArr" of type ArrayType(PointerType(StructType("node_t")), 10)
     *             "i" of type INT
     *  - A struct declaration "node_t" with unsorted fields:
     *             "value" of type INT,
     *             "prev" of type PointerType(StructType("node_t")),
     *             "next" of type PointerType(StructType("node_t"))
     *
     * After constructing the AST with unsorted lists, we sort:
     *    - Program.decls by name (for VarDecl, FunDef, and StructTypeDecl),
     *    - FunDef parameters,
     *    - Block variable declarations, and
     *    - StructTypeDecl fields.
     *
     * The expected sorted order is:
     *  Program(
     *      VarDecl(PointerType(StructType(node_t)), globalHead),
     *      FunDef(VOID, insert,
     *             VarDecl(PointerType(StructType(node_t)), elem),
     *             VarDecl(PointerType(PointerType(StructType(node_t))), head),
     *             Block(
     *                   VarDecl(INT, i),
     *                   VarDecl(ArrayType(PointerType(StructType(node_t)),10), nodeArr),
     *                   VarDecl(PointerType(StructType(node_t)), temp)
     *             )
     *      ),
     *      StructTypeDecl(StructType(node_t),
     *             VarDecl(PointerType(StructType(node_t)), next),
     *             VarDecl(PointerType(StructType(node_t)), prev),
     *             VarDecl(INT, value)
     *      )
     *  )
     */
    private static void testComplexASTSorting() {
        System.out.println("=== Testing Complex AST Sorting with Pointers, Structs, and Linked Lists ===");

        // --- Global Declaration ---
        // globalHead: PointerType(StructType("node_t"))
        VarDecl globalHead = new VarDecl(new PointerType(new StructType("node_t")), "globalHead");

        // --- Function Definition "insert" ---
        // Parameters (unsorted order): add "head" then "elem"
        VarDecl paramHead = new VarDecl(new PointerType(new PointerType(new StructType("node_t"))), "head");
        VarDecl paramElem = new VarDecl(new PointerType(new StructType("node_t")), "elem");
        List<VarDecl> funParams = new ArrayList<>();
        funParams.add(paramHead);
        funParams.add(paramElem);

        // Create Block for "insert" with unsorted variable declarations:
        // Unsored order: "temp", "nodeArr", "i"
        VarDecl varTemp = new VarDecl(new PointerType(new StructType("node_t")), "temp");
        VarDecl varNodeArr = new VarDecl(new ArrayType(new PointerType(new StructType("node_t")), 10), "nodeArr");
        VarDecl varI = new VarDecl(BaseType.INT, "i");
        List<VarDecl> blockVars = new ArrayList<>();
        blockVars.add(varTemp);
        blockVars.add(varNodeArr);
        blockVars.add(varI);
        Block insertBlock = new Block();
        insertBlock.vds = blockVars; // Assume Block.vds is public and holds variable declarations.
        // (No statements added for this test.)

        // Create FunDef "insert" with return type VOID.
        FunDef funInsert = new FunDef(BaseType.VOID, "insert", funParams, insertBlock);

        // --- Struct Declaration for "node_t" ---
        // Fields unsorted: add "value", then "prev", then "next"
        VarDecl fieldValue = new VarDecl(BaseType.INT, "value");
        VarDecl fieldPrev = new VarDecl(new PointerType(new StructType("node_t")), "prev");
        VarDecl fieldNext = new VarDecl(new PointerType(new StructType("node_t")), "next");
        List<Decl> structFields = new ArrayList<>();
        structFields.add(fieldValue);
        structFields.add(fieldPrev);
        structFields.add(fieldNext);
        StructTypeDecl structNode = new StructTypeDecl("node_t", structFields);

        // --- Build the Program AST ---
        // Add unsorted: first funInsert, then globalHead, then structNode.
        List<Decl> programDecls = new ArrayList<>();
        programDecls.add(funInsert);
        programDecls.add(globalHead);
        programDecls.add(structNode);
        Program program = new Program(programDecls);

        System.out.println("Initial AST:");
        String initialAST = getASTString(program);
        System.out.println(initialAST);

        // --- Sorting ---
        // Sort Program.decls by name (using the name field; for StructTypeDecl use its struct name)
        Collections.sort(program.decls, Comparator.comparing(decl -> {
            if (decl instanceof VarDecl) {
                return ((VarDecl) decl).name;
            } else if (decl instanceof FunDef) {
                return ((FunDef) decl).name;
            } else if (decl instanceof StructTypeDecl) {
                return ((StructTypeDecl) decl).getName(); // Assuming getName() returns the struct's name.
            }
            return "";
        }));

        // Sort function parameters for "insert" (alphabetical order)
        Collections.sort(funInsert.params, Comparator.comparing(param -> param.name));

        // Sort variable declarations in the function block
        Collections.sort(insertBlock.vds, Comparator.comparing(var -> var.name));

        // Sort fields in the struct declaration
        Collections.sort(structNode.getFields(), Comparator.comparing(field -> ((VarDecl) field).name));

        System.out.println("\nSorted AST:");
        String sortedAST = getASTString(program);
        System.out.println(sortedAST);

        // Expected AST string (without extra spaces):
        String expected = "Program(" +
                "VarDecl(PointerType(StructType(node_t)),globalHead)," +
                "FunDef(VOID,insert,VarDecl(PointerType(StructType(node_t)),elem),VarDecl(PointerType(PointerType(StructType(node_t))),head),Block(VarDecl(INT,i),VarDecl(ArrayType(PointerType(StructType(node_t)),10),nodeArr),VarDecl(PointerType(StructType(node_t)),temp)))," +
                "StructTypeDecl(StructType(node_t),VarDecl(PointerType(StructType(node_t)),next),VarDecl(PointerType(StructType(node_t)),prev),VarDecl(INT,value))" +
                ")";

        if (sortedAST.equals(expected)) {
            System.out.println("\nTest Passed: Sorted Complex AST matches expected output.");
        } else {
            System.out.println("\nTest Failed: Sorted Complex AST does not match expected output.");
            System.out.println("Expected: " + expected);
            System.out.println("Got:      " + sortedAST);
        }
    }

    /**
     * Helper method that uses the ASTPrinter to convert an ASTNode into its string representation.
     */
    private static String getASTString(ASTNode node) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(node);
        pw.flush();
        return sw.toString();
    }
}