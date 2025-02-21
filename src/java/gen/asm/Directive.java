// Authors: Jonathan Van der Cruysse, Christophe Dubach

// DO NOT MODIFY THIS FILE. For technical grading reasons, we may roll back this file to the original version we
// provided. This will overwrite any and all local changes you made, likely breaking your compiler if you made
// changes.
//
// Open a question on Ed if you need additional features that the classes in this file do not support, such as an
// instruction/opcode that is essential but not currently exposed.

package gen.asm;

import java.util.Objects;

/**
 * An assembler directive in a MIPS assembly program.
 */
public final class Directive extends AssemblyTextItem {
    public final String name;

    public Directive(String name) {
        this.name = name;
    }

    public String toString() {
        return "." + name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directive directive = (Directive) o;
        return Objects.equals(name, directive.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
