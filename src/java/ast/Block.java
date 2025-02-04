package ast;

import java.util.ArrayList;
import java.util.List;

public final class Block extends Stmt {

    public List<VarDecl> vds;
    public List<Stmt> stmts;

    // default constructor for empty list
    public Block() {
        this.vds = new ArrayList<>();
        this.stmts = new ArrayList<>();
    }

    // constructor with initial list
    public Block(List<VarDecl> vds, List<Stmt> stmts) {
        this.vds = vds;
        this.stmts = stmts;
    }

    @Override
    public List<ASTNode> children() {
        List<ASTNode> children = new ArrayList<>();
        children.addAll(vds);
        children.addAll(stmts);
        return children;
    }

    // helper to add a vd
    public void addVarDecl(VarDecl vd) {
        vds.add(vd);
    }

    // helper to add a stmt
    public void addStmt(Stmt stmt) {
        stmts.add(stmt);
    }

    @Override
    public String toString() {
        return "Block(" + vds.toString() + "," + stmts.toString() + ")";
    }

}
