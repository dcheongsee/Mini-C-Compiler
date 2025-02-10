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

        if (node instanceof BaseType bt) {
            writer.print(bt.name());
            return;
        }

        // we do not want to print a wrapper for this also
        if (node instanceof ArrayLength al) {
            writer.print(al.value);
            return;
        }

        // print node's class name and opening paren
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

            case VarExpr v -> {
                writer.print(v.name);
            }

            case ChrLiteral cl -> {
                writer.print("'");
                char c = cl.value;
                switch (c) {
                    case '\n': writer.print("\\n"); break;
                    case '\t': writer.print("\\t"); break;
                    case '\r': writer.print("\\r"); break;
                    case '\\': writer.print("\\\\"); break;
                    case '\'': writer.print("\\'"); break;
                    case '\"': writer.print("\\\""); break;
                    case '\f': writer.print("\\f"); break;
                    case '\b': writer.print("\\b"); break;
                    case '\u000B': writer.print("\\v"); break;
                    case '\u0007': writer.print("\\a"); break;
                    case '\0': writer.print("\\0"); break;
                    default:
                        // if char is printable, just print it
                        writer.print(c);
                        break;
                }
                writer.print("'");
            }

            case IntLiteral il -> {
                writer.print(il.value);
            }

            case StrLiteral sl -> {
                writer.print(sl.value);
            }

            case BinOp bin -> {
                visit(bin.left);
                writer.print("," + bin.op + ",");
                visit(bin.right);
            }

            case AddressOfExpr ao -> {
                visit(ao.expr);
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

            case FunCallExpr fc -> {
                writer.print(fc.name);
                for (Expr arg : fc.args) {
                    writer.print(",");
                    visit(arg);
                }
            }

            case Assign assign -> {
                visit(assign.left);
                writer.print(",");
                visit(assign.right);
            }

            case Block block -> {
                String delimiter = "";
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

            case StructType st -> {
                writer.print(st.name);
            }

            case ArrayAccessExpr aa -> {
                visit(aa.array);
                writer.print(",");
                visit(aa.index);
            }

            case ValueAtExpr va -> {
                visit(va.expr);
            }

            case ExprStmt es -> {
                visit(es.expr);
            }

            case Continue c -> { }
            case Break b -> { }

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

        switch (node) {
            case Program ignored -> writer.flush();
            default -> { }
        }
    }
}