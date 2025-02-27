// Author: Jonathan Van der Cruysse

package gen.asm;

/**
 * A pass that operates on an {@link AssemblyProgram}.
 */
public interface AssemblyPass {
    /**
     * Applies this pass to an {@link AssemblyProgram}. Returns a transformed version of that program. This method may
     * modify {@code program}.
     * @param program An {@link AssemblyProgram} to transform.
     * @return A transformed version of {@code program}.
     */
    AssemblyProgram apply(AssemblyProgram program);

    /**
     * An assembly pass that simply returns its input.
     */
    AssemblyPass NOP = program -> program;
}
