package ast;

import java.util.ArrayList;
import java.util.List;

public final class StructTypeDecl extends Decl {

    private final String name;
    private final List<Decl> fields;

    public StructTypeDecl(String name, List<Decl> fields) {
        this.name = name;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }
    public List<Decl> getFields() { return fields; }

    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.add(new StructType(this.name));
        children.addAll(fields);
        return children;
    }

}
