package ast;

import java.util.ArrayList;
import java.util.List;


 // represents a class declaration in our language
public final class ClassDecl extends Decl {

    private final String name;
    private final String parentName;
    private final List<VarDecl> fields;
    private final List<FunDef> methods;
    public int objectSize;


    public ClassDecl(String name, String parentName, List<VarDecl> fields, List<FunDef> methods) {
        this.name = name;
        this.parentName = parentName;
        this.fields = fields;
        this.methods = methods;
        this.objectSize = 0; // initialize to 0, will be computed during MemAllocCodeGen
    }

    public String getName() {
        return name;
    }

    public String getParentName() {
        return parentName;
    }

    public List<VarDecl> getFields() {
        return fields;
    }

    public List<FunDef> getMethods() {
        return methods;
    }

    @Override
    public List<ASTNode> children() {
        // we'll store "this class" and parent as types
        // plus all field declarations, plus all method declarations
        List<ASTNode> result = new ArrayList<>();
        // add a ClassType node for this class's name
        result.add(new ClassType(name));

        // if there is a parent, add another ClassType node
        if (parentName != null) {
            result.add(new ClassType(parentName));
        }

        result.addAll(fields);
        result.addAll(methods);

        return result;
    }

}
