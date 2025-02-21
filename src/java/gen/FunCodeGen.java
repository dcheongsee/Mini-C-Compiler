package gen;

import ast.FunDef;
import gen.asm.AssemblyProgram;

/**
 * A visitor that produces code for a single function declaration
 */
public class FunCodeGen extends CodeGen {


    public FunCodeGen(AssemblyProgram asmProg) {
        this.asmProg = asmProg;
    }

    void visit(FunDef fd) {
        // Each function should be produced in its own section.
        // This is necessary for the register allocator.
        asmProg.emitNewTextSection();

        // TODO: to complete
        // 1) emit the prolog

        // 2) emit the body of the function
        StmtCodeGen scd = new StmtCodeGen(asmProg);
        scd.visit(fd.block);

        // 3) emit the epilog
    }



}
