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
 * A comment in an assembly program. Comments do not change the meaning of programs, but may aid humans in their
 * understanding of programs.
 */
public final class Comment extends AssemblyTextItem {
    public final String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    public String toString() {
        return "# " + comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return comment.equals(comment1.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment);
    }
}
