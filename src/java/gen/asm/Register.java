// Authors: Jonathan Van der Cruysse, Christophe Dubach

// DO NOT MODIFY THIS FILE. For technical grading reasons, we may roll back this file to the original version we
// provided. This will overwrite any and all local changes you made, likely breaking your compiler if you made
// changes.
//
// Open a question on Ed if you need additional features that the classes in this file do not support, such as an
// instruction/opcode that is essential but not currently exposed.

package gen.asm;

import java.util.HashMap;
import java.util.List;

/**
 * A register, as seen from the compiler's point of view. Registers can be either architectural (part of the ISA) or
 * virtual (a fiction the compiler maintains until register allocation kicks in).
 */
public abstract class Register {

    /**
     * Tells if this register is virtual.
     * @return {@code true} if this register represents a virtual register, {@code false} if it represents a register
     * defined by the MIPS ISA.
     */
    abstract public boolean isVirtual();

    /**
     * A virtual register. That is, a pseudo-register the compiler uses for internal purposes. Virtual registers are
     * translated to architectural registers ({@link Arch}) by the register allocator.
     *
     * {@link Virtual} instances are flyweights. That is, the class' design makes it so that (barring multithreading
     * or reflection shenanigans) there can be at most one {@link Virtual} instance per name.  Use {@link #create()} to
     * generate fresh {@link Virtual} instances.
     */
    static public class Virtual extends Register {

        /**
         * The virtual register's name. This name is unique: no two {@link Virtual} instances will have the same name.
         */
        public final String name;

        private Virtual(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }

        public boolean isVirtual() {
            return true;
        }

        // This hash map interns flyweight instances to ensure that no two Virtual instances have the same name.
        private static final HashMap<String, Virtual> instances = new HashMap<>();

        /**
         * Gets the unique virtual register for a given name.
         * @param name The virtual register's name.
         * @return The unique {@link Virtual} instance with name {@code name}.
         */
        public static Virtual get(String name)
        {
            return instances.computeIfAbsent(name, Virtual::new);
        }

        /**
         * Creates a fresh virtual register with a unique name.
         * @return A unique {@link Virtual} instance.
         */
        public static Virtual create()
        {
            int counter = instances.size();
            String draftName;
            do {
                draftName = "v" + counter;
                counter++;
            } while (instances.containsKey(draftName));
            return get(draftName);
        }
    }

    /**
     * An architectural register. That is, one of the registers described by the MIPS ISA.
     */
    static public class Arch extends Register {

        public static final Arch zero = new Arch(0,"zero");
        public static final Arch v0 = new Arch(2,"v0");
        public static final Arch v1 = new Arch(3,"v1");
        public static final Arch a0 = new Arch(4,"a0");
        public static final Arch a1 = new Arch(5,"a1");
        public static final Arch a2 = new Arch(6,"a2");
        public static final Arch a3 = new Arch(7,"a3");
        public static final Arch t0 = new Arch(8,"t0");
        public static final Arch t1 = new Arch(9,"t1");
        public static final Arch t2 = new Arch(10,"t2");
        public static final Arch t3 = new Arch(11,"t3");
        public static final Arch t4 = new Arch(12,"t4");
        public static final Arch t5 = new Arch(13,"t5");
        public static final Arch t6 = new Arch(14,"t6");
        public static final Arch t7 = new Arch(15,"t7");
        public static final Arch s0 = new Arch(16,"s0");
        public static final Arch s1 = new Arch(17,"s1");
        public static final Arch s2 = new Arch(18,"s2");
        public static final Arch s3 = new Arch(19,"s3");
        public static final Arch s4 = new Arch(20,"s4");
        public static final Arch s5 = new Arch(21,"s5");
        public static final Arch s6 = new Arch(22,"s6");
        public static final Arch s7 = new Arch(23,"s7");
        public static final Arch t8 = new Arch(24,"t8");
        public static final Arch t9 = new Arch(25,"t9");
        public static final Arch gp = new Arch(28,"gp");
        public static final Arch sp = new Arch(29,"sp");
        public static final Arch fp = new Arch(30,"fp");
        public static final Arch ra = new Arch(31,"ra");

        private final int num;
        private final String name;

        private Arch(int num, String name) {
            this.num = num;
            this.name = name;
        }

        public String toString() {
            return "$"+name;
        }

        public boolean isVirtual() {
            return false;
        }

        /**
         * A list of all architectural registers known to the compiler.
         */
        public static final List<Arch> allRegisters = List.of(
            zero,
            v0, v1,
            a0, a1, a2, a3,
            t0, t1, t2, t3, t4, t5, t6, t7, t8, t9,
            s0, s1, s2, s3, s4, s5, s6, s7,
            gp, sp, fp, ra);
    }
}
