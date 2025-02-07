package tests2;

import ast.*;
import java.io.PrintWriter;
import java.util.List;

public class AstStructDecl2 {
    public static void main(String[] args) {

        VarDecl fieldAge = new VarDecl(BaseType.INT, "age");
        ArrayType nameType = new ArrayType(BaseType.CHAR, 30);
        VarDecl fieldName = new VarDecl(nameType, "name");
        PointerType personPtr = new PointerType(new StructType("Person"));
        VarDecl fieldFriend = new VarDecl(personPtr, "friend");
        StructTypeDecl personStruct = new StructTypeDecl("Person", List.of(fieldAge, fieldName, fieldFriend));

        VarDecl varP = new VarDecl(new StructType("Person"), "p");
        VarExpr pExpr = new VarExpr("p");
        Return ret = new Return(pExpr);

        Block block = new Block();
        block.vds.add(varP);
        block.stmts.add(ret);

        FunDef createPersonFun = new FunDef(new StructType("Person"), "createPerson", List.of(), block);

        Program program = new Program(List.of(personStruct, createPersonFun));

        PrintWriter writer = new PrintWriter(System.out, true);
        ASTPrinter printer = new ASTPrinter(writer);
        printer.visit(program);
        writer.println();
    }
}