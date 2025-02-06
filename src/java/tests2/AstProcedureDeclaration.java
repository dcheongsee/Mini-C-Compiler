package tests2;

import ast.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

public class AstProcedureDeclaration {
    public static void main(String[] args) {
        // build param int x
        VarDecl param = new VarDecl(BaseType.INT, "x");
        ArrayList<VarDecl> params = new ArrayList<>();
        params.add(param);

        // build func decl with no body
        FunDecl funDecl = new FunDecl(BaseType.INT, "foo", params);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ASTPrinter printer = new ASTPrinter(pw);
        printer.visit(funDecl);
        pw.flush();
        System.out.println("AstProcedureDeclaration output:");
        System.out.println(sw.toString());
        // expected output
        // FunDecl(INT,foo,VarDecl(INT,x))
    }
}