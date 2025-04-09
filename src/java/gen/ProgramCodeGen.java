package gen;

import ast.FunDef;
import ast.Program;
import gen.asm.AssemblyProgram;

/**
 * This visitor should produce a program.
 */
public class ProgramCodeGen extends CodeGen {

    public ProgramCodeGen(AssemblyProgram asmProg) {
        this.asmProg = asmProg;
    }

    void generate(Program p) {
        // allocate all variables
        MemAllocCodeGen allocator = new MemAllocCodeGen(asmProg);
        allocator.visit(p);

        // generate the code for each function
        p.decls.forEach((d) -> {
            switch(d) {
                case FunDef fd -> {
                    FunCodeGen fcg = new FunCodeGen(asmProg);
                    fcg.visit(fd);
                }
                default -> {}// nothing to do
            }});
    }





}
