package gen;

import ast.FunDef;
import gen.asm.AssemblyProgram;
import gen.asm.Label;
import gen.asm.OpCode;
import gen.asm.Register;
import sem.SemanticAnalyzer;

/**
 * A visitor that produces code for a single function declaration
 */
public class FunCodeGen extends CodeGen {

    public FunCodeGen(AssemblyProgram asmProg) {
        this.asmProg = asmProg;
    }

    public void visit(FunDef fd) {
        // Each function should be produced in its own section.
        // This is necessary for the register allocator.
        asmProg.emitNewTextSection();

        if (fd.name.equals("main")) {
            asmProg.getCurrentTextSection().emit(new gen.asm.Directive("globl main"));
        }

        // emit function label
        Label functionLabel = Label.get(fd.name);
        asmProg.getCurrentTextSection().emit(functionLabel);

        // prolog, push $ra onto the stack then set up frame pointer
        asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -8);
        asmProg.getCurrentTextSection().emit(OpCode.SW, Register.Arch.fp, Register.Arch.sp, 4); // Save old fp
        asmProg.getCurrentTextSection().emit(OpCode.SW, Register.Arch.ra, Register.Arch.sp, 0); // Save ra
        asmProg.getCurrentTextSection().emit(OpCode.ADD, Register.Arch.fp, Register.Arch.sp, Register.Arch.zero);

        int localSpace = fd.localVarSize;  // localVarSize computed by MemAllocCodeGen
        if (localSpace > 0) {
            asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -localSpace);
        }

        // set up return label
        StmtCodeGen scd = new StmtCodeGen(asmProg, SemanticAnalyzer.GlobalAST);
        Label retLabel = Label.create("return_" + fd.name);
        scd.returnLabel = retLabel;

        // emit function body
        scd.visit(fd.block);

        // epilog, emit return label then restore $ra
        asmProg.getCurrentTextSection().emit(retLabel);

        if (localSpace > 0) {
            asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, localSpace);
        }

        asmProg.getCurrentTextSection().emit(OpCode.LW, Register.Arch.ra, Register.Arch.sp, 0);
        asmProg.getCurrentTextSection().emit(OpCode.LW, Register.Arch.fp, Register.Arch.sp, 4);
        asmProg.getCurrentTextSection().emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, 8);

        if (fd.name.equals("main")) {
            // exit sequence for main
            asmProg.getCurrentTextSection().emit(OpCode.LI, Register.Arch.v0, 10);
            asmProg.getCurrentTextSection().emit(OpCode.SYSCALL);
        } else {
            asmProg.getCurrentTextSection().emit(OpCode.JR, Register.Arch.ra);
        }
    }
}
