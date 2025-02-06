package ast;

import java.io.PrintWriter;

public class ASTPrinter {

    private final PrintWriter writer;

    public ASTPrinter(PrintWriter writer) {
        this.writer = writer;
    }

    public void visit(ASTNode node) {
        if (node == null)
            throw new IllegalStateException("Unexpected null value");

        if (node instanceof VarExpr ve) {
            writer.print("VarExpr(" + ve.name + ")");
            return;
        }

        if (node instanceof BaseType bt) {
            writer.print(bt);
            return;
        }

        if (node instanceof StructType st) {
            writer.print(st);
            return;
        }
        writer.print(node.getClass().getSimpleName() + "(");

        switch (node) {
            case FunDef fd -> {
                visit(fd.type);
                writer.print("," + fd.name);
                for (VarDecl vd : fd.params) {
                    writer.print(",");
                    visit(vd);
                }
                writer.print(",");
                visit(fd.block);
            }
            case FunDecl fd -> {
                visit(fd.type);
                writer.print("," + fd.name);
                for (VarDecl vd : fd.params) {
                    writer.print(",");
                    visit(vd);
                }
            }
            case VarDecl vd -> {
                visit(vd.type);
                writer.print("," + vd.name);
            }
            case FunCallExpr fc -> {
                writer.print(fc.name);
                for (Expr arg : fc.args) {
                    writer.print(",");
                    visit(arg);
                }
            }
            case BinOp bin -> {
                visit(bin.left);
                writer.print("," + bin.op);
                writer.print(",");
                visit(bin.right);
            }
            case ChrLiteral cl -> {
                writer.print(cl.value);
            }
            case IntLiteral il -> {
                writer.print(il.value);
            }
            case StrLiteral sl -> {
                writer.print(sl.value);
            }
            case ArrayAccessExpr aa -> {
                visit(aa.array);
                writer.print(",");
                visit(aa.index);
            }
            case ValueAtExpr va -> {
                visit(va.expr);
            }
            case FieldAccessExpr fa -> {
                visit(fa.expr);
                writer.print("," + fa.field);
            }
            case TypecastExpr tc -> {
                visit(tc.castType);
                writer.print(",");
                visit(tc.expr);
            }
            case SizeOfExpr so -> {
                visit(so.type);
            }
            case AddressOfExpr ao -> {
                visit(ao.expr);
            }
            case Assign assign -> {
                visit(assign.left);
                writer.print(",");
                visit(assign.right);
            }
            case Block block -> {
                String delimiter = "";
                // print var decl first
                for (ASTNode child : block.children()) {
                    writer.print(delimiter);
                    delimiter = ",";
                    visit(child);
                }
            }
            case If i -> {
                visit(i.expr);
                writer.print(",");
                visit(i.thenStmt);
                if (i.elseStmt != null) {
                    writer.print(",");
                    visit(i.elseStmt);
                }
            }
            case While wh -> {
                visit(wh.expr);
                writer.print(",");
                visit(wh.stmt);
            }
            case Return r -> {
                if (r.expr != null) {
                    visit(r.expr);
                }
            }
            case Continue c -> {
                // nothing
            }
            case Break b -> {
                // nothing
            }
            case ExprStmt es -> {
                visit(es.expr);
            }

            default -> {
                String delimiter = "";
                for (ASTNode child : node.children()) {
                    writer.print(delimiter);
                    delimiter = ",";
                    visit(child);
                }
            }
        }

        writer.print(")");

        switch(node) {
            case Program ignored -> {
                writer.flush(); // ensures the writer flushes all the writes at the end of our program
            }
            default -> {}
        }

    }
}