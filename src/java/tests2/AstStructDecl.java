package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class AstStructDecl {
    public static void main(String[] args) {

        VarDecl field1 = new VarDecl(BaseType.INT, "field1");
        VarDecl field2 = new VarDecl(BaseType.CHAR, "field2");
        ArrayList<Decl> fields = new ArrayList<>();
        fields.add(field1);
        fields.add(field2);
        StructTypeDecl structDecl = new StructTypeDecl("node_t", fields);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(structDecl);
        pw.flush();
        String output = sw.toString();

        System.out.println("TestAST_StructDecl output:");
        System.out.println(output);
    }
}