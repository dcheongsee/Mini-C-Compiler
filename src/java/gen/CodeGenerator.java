// Authors: Christophe Dubach, Jonathan Van der Cruysse

package gen;

import ast.Program;
import gen.asm.AssemblyPass;
import gen.asm.AssemblyProgram;
import regalloc.NaiveRegAlloc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * A MIPS code generator for Mini C: An object that takes a Mini C AST, turns it into MIPS instructions, and writes
 * those instructions to an output file.
 */
public final class CodeGenerator {
    /**
     * Creates a code generator that relies on the default register allocator.
     *
     * @implNote To use your custom register allocator by default, replace {@code NaiveRegAlloc.INSTANCE} with an
     * instance of your register allocator pass for part 4.
     */
    public CodeGenerator() {
        this.registerAllocator = NaiveRegAlloc.INSTANCE;
    }

    /**
     * Creates a code generator that uses a custom register allocator.
     *
     * @param registerAllocator The register allocator to use.
     */
    public CodeGenerator(AssemblyPass registerAllocator) {
        this.registerAllocator = registerAllocator;
    }

    /**
     * The register allocator this code generator uses.
     */
    public final AssemblyPass registerAllocator;

    /**
     * Takes a Mini C program as an AST, turns it into a MIPS program, and writes a textual representation of that
     * program to a file.
     * @param astProgram The Mini C program to turn into MIPS instructions.
     * @param outputFile The output file to write MIPS assembly code to.
     * @throws FileNotFoundException If {@code outputFile} does not denote an existing, writable regular file and a new
     * regular file of that name cannot be created, or if some other error occurs while opening or creating the file.
     */
    public void emitProgram(Program astProgram, File outputFile) throws FileNotFoundException {

        // generate an assembly program with the code generator
        AssemblyProgram asmProgWithVirtualRegs = new AssemblyProgram();
        ProgramCodeGen progGen = new ProgramCodeGen(asmProgWithVirtualRegs);
        progGen.generate(astProgram);

        // run the register allocator which remove the virtual registers (unless there is no register allocator)
        AssemblyProgram asmProgAfterRegAlloc = registerAllocator.apply(asmProgWithVirtualRegs);

        // print the assembly program
        PrintWriter writer = new PrintWriter(outputFile);
        asmProgAfterRegAlloc.print(writer);
        writer.close();
    }
}
