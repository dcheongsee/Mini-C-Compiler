package gen;

import ast.*;
import gen.asm.AssemblyProgram;
import gen.asm.Label;
import gen.asm.OpCode;
import gen.asm.Register;
import java.util.Deque;
import java.util.ArrayDeque;

public class StmtCodeGen extends CodeGen {

    private final ASTNode astRoot;
    private final Deque<Label> breakStack = new ArrayDeque<>();
    private final Deque<Label> continueStack = new ArrayDeque<>();
    // should be set by FunCodeGen before processing a function body
    public Label returnLabel;

    public StmtCodeGen(AssemblyProgram asmProg, ASTNode astRoot) {
        this.asmProg = asmProg;
        this.astRoot = astRoot;
    }

    public void visit(Stmt s) {
        switch (s) {
            case Block b -> {
                for (Stmt inner : b.stmts) {
                    visit(inner);
                }
            }
            case ExprStmt es -> {
                new ExprValCodeGen(asmProg, astRoot).visit(es.expr);
            }
            case If i -> {
                Label elseLabel = Label.create("if_else");
                Label endLabel = Label.create("if_end");
                Register cond = new ExprValCodeGen(asmProg, astRoot).visit(i.expr);
                asmProg.getCurrentTextSection().emit(OpCode.BEQZ, cond, elseLabel);
                visit(i.thenStmt);
                asmProg.getCurrentTextSection().emit(OpCode.J, endLabel);
                asmProg.getCurrentTextSection().emit(elseLabel);
                if (i.elseStmt != null) {
                    visit(i.elseStmt);
                }
                asmProg.getCurrentTextSection().emit(endLabel);
            }
            case While wh -> {
                Label startLabel = Label.create("while_start");
                Label exitLabel = Label.create("while_exit");
                breakStack.push(exitLabel);
                continueStack.push(startLabel);
                asmProg.getCurrentTextSection().emit(startLabel);
                Register cond = new ExprValCodeGen(asmProg, astRoot).visit(wh.expr);
                asmProg.getCurrentTextSection().emit(OpCode.BEQZ, cond, exitLabel);
                visit(wh.stmt);
                asmProg.getCurrentTextSection().emit(OpCode.J, startLabel);
                asmProg.getCurrentTextSection().emit(exitLabel);
                breakStack.pop();
                continueStack.pop();
            }
            case Break b -> {
                if (breakStack.isEmpty()) {
                    throw new RuntimeException("Break statement not within a loop");
                }
                asmProg.getCurrentTextSection().emit(OpCode.J, breakStack.peek());
            }
            case Continue c -> {
                if (continueStack.isEmpty()) {
                    throw new RuntimeException("Continue statement not within a loop");
                }
                asmProg.getCurrentTextSection().emit(OpCode.J, continueStack.peek());
            }
            case Return r -> {
                if (r.expr != null) {
                    Register retVal = new ExprValCodeGen(asmProg, astRoot).visit(r.expr);
                    asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.v0, retVal, Register.Arch.zero);
                }
                if (returnLabel == null) {
                    throw new RuntimeException("Return label not set");
                }
                asmProg.getCurrentTextSection().emit(OpCode.J, returnLabel);
            }
            default -> {
                for (ASTNode child : s.children()) {
                    if (child instanceof Stmt stmtChild) {
                        visit(stmtChild);
                    }
                }
            }
        }
    }
}
