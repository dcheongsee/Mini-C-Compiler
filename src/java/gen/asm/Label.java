// Authors: Jonathan Van der Cruysse, Christophe Dubach

// DO NOT MODIFY THIS FILE. For technical grading reasons, we may roll back this file to the original version we
// provided. This will overwrite any and all local changes you made, likely breaking your compiler if you made
// changes.
//
// Open a question on Ed if you need additional features that the classes in this file do not support, such as an
// instruction/opcode that is essential but not currently exposed.

package gen.asm;

import java.util.HashMap;

/**
 * A label in a MIPS assembly program.
 *
 * {@link Label} instances are flyweights. That is, the class' design makes it so that (barring multithreading
 * or reflection shenanigans) there can be at most one {@link Label} instance per name. Use {@link #create(String)}
 * and {@link #create()} to generate fresh {@link Label} instances.
 */
public final class Label extends AssemblyTextItem {
    /**
     * The label's unique name.
     */
    public final String name;

    private Label(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }


    // This hash map interns flyweight instances to ensure that no two Virtual instances have the same name.
    private static final HashMap<String, Label> instances = new HashMap<>();

    /**
     * Gets the unique label for a given name.
     *
     * @param name The label's name.
     * @return The unique {@link Label} instance with name {@code name}.
     */
    public static Label get(String name) {
        return instances.computeIfAbsent(name, Label::new);
    }

    /**
     * Creates a fresh label with a unique name.
     *
     * @param nameSuffix A suffix to append to the label's name.
     * @return A unique {@link Label} instance.
     */
    public static Label create(String nameSuffix) {
        int counter = instances.size();
        String draftName;
        do {
            draftName = "label_" + counter + "_" + nameSuffix;
            counter++;
        } while (instances.containsKey(draftName));
        return get(draftName);
    }

    /**
     * Creates a fresh label with a unique name.
     *
     * @return A unique {@link Label} instance.
     */
    public static Label create() {
        return create("");
    }
}
