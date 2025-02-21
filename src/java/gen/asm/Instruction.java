// Authors: Jonathan Van der Cruysse, Christophe Dubach

// DO NOT MODIFY THIS FILE. For technical grading reasons, we may roll back this file to the original version we
// provided. This will overwrite any and all local changes you made, likely breaking your compiler if you made
// changes.
//
// Open a question on Ed if you need additional features that the classes in this file do not support, such as an
// instruction/opcode that is essential but not currently exposed.

package gen.asm;

import java.util.*;

/**
 * An instruction in a MIPS assembly program.
 */
public abstract sealed class Instruction extends AssemblyItem {

    public final OpCode opcode;

    public Instruction(OpCode opcode) {
        this.opcode = opcode;
    }

    /**
     * @return register that this instructions modifies (if none, returns null)
     */
    public abstract Register def();

    /**
     * @return list of registers that this instruction uses
     */
    public abstract List<Register> uses();

    /**
     * @return list of registers that are used as operands for this instruction
     */
    public List<Register> registers() {
        List<Register> regs = new ArrayList<>(uses());
        if (def() != null)
            regs.add(def());
        return regs;
    }

    /**
     * @param regMap replacement map for register
     * @return a new instruction where the registers have been replaced based on the regMap
     */
    public abstract Instruction rebuild(Map<Register, Register> regMap);


    /**
     * A type R arithmetic instruction that takes three register arguments.
     */
    public static final class TernaryArithmetic extends Instruction {
        public final Register dst;
        public final Register src1;
        public final Register src2;

        public TernaryArithmetic(OpCode.TernaryArithmetic opcode, Register dst, Register src1, Register src2) {
            super(opcode);
            this.dst = dst;
            this.src1 = src1;
            this.src2 = src2;
        }

        public String toString() {
            return opcode + " " + dst + "," + src1 + "," + src2;
        }

        public Register def() {
            return dst;
        }

        public List<Register> uses() {
            return List.of(src1, src2);
        }

        public TernaryArithmetic rebuild(Map<Register, Register> regMap) {
            return new TernaryArithmetic(
                    (OpCode.TernaryArithmetic) opcode,
                    regMap.getOrDefault(dst, dst),
                    regMap.getOrDefault(src1, src1),
                    regMap.getOrDefault(src2, src2));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TernaryArithmetic that = (TernaryArithmetic) o;
            return opcode == that.opcode && dst.equals(that.dst) && src1.equals(that.src1) && src2.equals(that.src2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(opcode, dst, src1, src2);
        }
    }

    /**
     * A type R arithmetic instruction that takes two register arguments.
     */
    public static final class BinaryArithmetic extends Instruction {
        public final Register src1;
        public final Register src2;

        public BinaryArithmetic(OpCode.BinaryArithmetic opcode, Register src1, Register src2) {
            super(opcode);
            this.src1 = src1;
            this.src2 = src2;
        }

        public String toString() {
            return opcode + " " + src1 + "," + src2;
        }

        public Register def() {
            return null;
        }

        public List<Register> uses() {
            return List.of(src1, src2);
        }

        public BinaryArithmetic rebuild(Map<Register, Register> regMap) {
            return new BinaryArithmetic(
                    (OpCode.BinaryArithmetic) opcode,
                    regMap.getOrDefault(src1, src1),
                    regMap.getOrDefault(src2, src2));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BinaryArithmetic that = (BinaryArithmetic) o;
            return opcode == that.opcode && src1.equals(that.src1) && src2.equals(that.src2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(opcode, src1, src2);
        }
    }

    /**
     * A type R arithmetic instruction that takes one register argument.
     */
    public static final class UnaryArithmetic extends Instruction {
        public final Register dst;

        public UnaryArithmetic(OpCode.UnaryArithmetic opcode, Register dst) {
            super(opcode);
            this.dst = dst;
        }

        public String toString() {
            return opcode + " " + dst;
        }

        public Register def() {
            return dst;
        }

        public List<Register> uses() {
            return List.of();
        }

        public UnaryArithmetic rebuild(Map<Register, Register> regMap) {
            return new UnaryArithmetic(
                    (OpCode.UnaryArithmetic) opcode,
                    regMap.getOrDefault(dst, dst));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UnaryArithmetic that = (UnaryArithmetic) o;
            return opcode == that.opcode && dst.equals(that.dst);
        }

        @Override
        public int hashCode() {
            return Objects.hash(opcode, dst);
        }
    }

    /**
     * An instruction that affects control flow.
     */
    public sealed static abstract class ControlFlow extends Instruction {
        public ControlFlow(OpCode opcode) {
            super(opcode);
        }
    }

    /**
     * A binary branch instruction, that is, a type I instruction that takes two {@link Register} operands and one
     * {@link Label} as immediate operand.
     */
    public static final class BinaryBranch extends ControlFlow {
        public final Label label;
        public final Register src1;
        public final Register src2;

        public BinaryBranch(OpCode.BinaryBranch opcode, Register src1, Register src2, Label label) {
            super(opcode);
            this.label = label;
            this.src1 = src1;
            this.src2 = src2;
        }

        public String toString() {
            return opcode + " " + src1 + "," + src2 + "," + label;
        }

        public Register def() {
            return null;
        }

        public List<Register> uses() {
            return List.of(src1, src2);
        }

        public BinaryBranch rebuild(Map<Register, Register> regMap) {
            return new BinaryBranch(
                    (OpCode.BinaryBranch) opcode,
                    regMap.getOrDefault(src1, src1),
                    regMap.getOrDefault(src2, src2), label);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BinaryBranch branch = (BinaryBranch) o;
            return label.equals(branch.label) && src1.equals(branch.src1) && src2.equals(branch.src2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label, src1, src2);
        }
    }

    /**
     * A unary branch instruction, that is, an instruction that takes one {@link Register} operand and one
     * {@link Label} as immediate operand.
     */
    public static final class UnaryBranch extends ControlFlow {
        public final Label label;
        public final Register src;

        public UnaryBranch(OpCode.UnaryBranch opcode, Register src, Label label) {
            super(opcode);
            this.label = label;
            this.src = src;
        }

        public String toString() {
            return opcode + " " + src + "," + label;
        }

        public Register def() {
            return null;
        }

        public List<Register> uses() {
            return List.of(src);
        }

        public UnaryBranch rebuild(Map<Register, Register> regMap) {
            return new UnaryBranch(
                    (OpCode.UnaryBranch) opcode,
                    regMap.getOrDefault(src, src),
                    label);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UnaryBranch branch = (UnaryBranch) o;
            return label.equals(branch.label) && src.equals(branch.src);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label, src);
        }
    }

    /**
     * A core arithmetic instruction with two register operands and one immediate operand. This is a type I instruction.
     */
    public static final class ArithmeticWithImmediate extends Instruction {
        public final int imm;
        public final Register dst;
        public final Register src;

        public ArithmeticWithImmediate(OpCode.ArithmeticWithImmediate opcode, Register dst, Register src, int imm) {
            super(opcode);
            this.imm = imm;
            this.src = src;
            this.dst = dst;
        }

        public String toString() {
            return opcode + " " + dst + "," + src + "," + imm;
        }

        public Register def() {
            return dst;
        }

        public List<Register> uses() {
            Register[] uses = {src};
            return Arrays.asList(uses);
        }

        public ArithmeticWithImmediate rebuild(Map<Register, Register> regMap) {
            return new ArithmeticWithImmediate(
                    (OpCode.ArithmeticWithImmediate) opcode,
                    regMap.getOrDefault(dst, dst),
                    regMap.getOrDefault(src, src),
                    imm);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArithmeticWithImmediate that = (ArithmeticWithImmediate) o;
            return opcode == that.opcode && imm == that.imm && dst.equals(that.dst) && src.equals(that.src);
        }

        @Override
        public int hashCode() {
            return Objects.hash(opcode, imm, dst, src);
        }
    }

    /**
     * A jump instruction, an unconditional branch that consists of an opcode and an address operand, encoded as a
     * {@link Label}.
     */
    public static final class Jump extends ControlFlow {
        /**
         * The jump instruction's address operand, encoded as a label.
         */
        public final Label label;

        /**
         * Creates a new jump instruction from an opcode and a label.
         *
         * @param opcode The opcode that defines the type of the instruction.
         * @param label  The label that serves as the address operand of the instruction.
         */
        public Jump(OpCode.Jump opcode, Label label) {
            super(opcode);
            this.label = label;
        }

        @Override
        public Register def() {
            return null;
        }

        @Override
        public List<Register> uses() {
            return List.of();
        }

        @Override
        public Instruction rebuild(Map<Register, Register> regMap) {
            return this;
        }

        @Override
        public String toString() {
            return opcode + " " + label;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Jump jump = (Jump) o;
            return opcode == jump.opcode && label.equals(jump.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(opcode, label);
        }
    }

    /**
     * A jump-register instruction, an unconditional branch that jumps to the address defined by a {@link Register}
     * operand.
     */
    public static final class JumpRegister extends ControlFlow {
        /**
         * The jump-register instruction's address operand, encoded as the contents of a register.
         */
        public final Register address;

        /**
         * Creates a new jump-register instruction from an opcode and a register.
         *
         * @param opcode  The opcode that defines the type of the instruction.
         * @param address The register that serves as the address operand of the instruction.
         */
        public JumpRegister(OpCode.JumpRegister opcode, Register address) {
            super(opcode);
            this.address = address;
        }

        @Override
        public Register def() {
            return null;
        }

        @Override
        public List<Register> uses() {
            return List.of(address);
        }

        @Override
        public Instruction rebuild(Map<Register, Register> regMap) {
            return new JumpRegister((OpCode.JumpRegister) opcode, regMap.getOrDefault(address, address));
        }

        @Override
        public String toString() {
            return opcode + " " + address;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            JumpRegister jump = (JumpRegister) o;
            return opcode == jump.opcode && address.equals(jump.address);
        }

        @Override
        public int hashCode() {
            return Objects.hash(opcode, address);
        }
    }

    /**
     * An instruction that loads or stores an address in memory. The address is computed based on a register and an
     * immediate operand.
     */
    public sealed abstract static class MemIndirect extends Instruction {
        public final Register op1;
        public final Register op2;
        public final int imm;

        public MemIndirect(OpCode opcode, Register op1, Register op2, int imm) {
            super(opcode);
            this.op1 = op1;
            this.op2 = op2;
            this.imm = imm;
        }

        public String toString() {
            return opcode + " " + op1 + "," + imm + "(" + op2 + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MemIndirect that = (MemIndirect) o;
            return opcode == that.opcode && imm == that.imm && op1.equals(that.op1) && op2.equals(that.op2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(opcode, op1, op2, imm);
        }
    }

    /**
     * An instruction that stores a value in memory. This is a type I instruction.
     */
    public static final class Store extends MemIndirect {
        public Store(OpCode.Store opcode, Register op1, Register op2, int imm) {
            super(opcode, op1, op2, imm);
        }

        public Store rebuild(Map<Register, Register> regMap) {
            return new Store((OpCode.Store) opcode, regMap.getOrDefault(op1, op1), regMap.getOrDefault(op2, op2), imm);
        }

        public Register def() {
            return null;
        }

        public List<Register> uses() {
            Register[] uses = {op1, op2};
            return Arrays.asList(uses);
        }
    }

    /**
     * An instruction that loads a value from memory and stores it in a register. This is a type I instruction.
     */
    public static final class Load extends MemIndirect {
        public Load(OpCode.Load opcode, Register op1, Register op2, int imm) {
            super(opcode, op1, op2, imm);
        }

        public Load rebuild(Map<Register, Register> regMap) {
            return new Load((OpCode.Load) opcode, regMap.getOrDefault(op1, op1), regMap.getOrDefault(op2, op2), imm);
        }

        public Register def() {
            return op1;
        }

        public List<Register> uses() {
            Register[] uses = {op2};
            return Arrays.asList(uses);
        }
    }

    /**
     * An instruction that loads its immediate operand a destination register.
     */
    public static final class LoadImmediate extends Instruction {
        public final Register dst;
        public final int imm;

        public LoadImmediate(OpCode.LoadImmediate opcode, Register dst, int imm) {
            super(opcode);
            this.dst = dst;
            this.imm = imm;
        }

        @Override
        public Register def() {
            return dst;
        }

        @Override
        public List<Register> uses() {
            return List.of();
        }

        @Override
        public Instruction rebuild(Map<Register, Register> regMap) {
            return new LoadImmediate((OpCode.LoadImmediate)opcode, regMap.getOrDefault(dst, dst), imm);
        }

        @Override
        public String toString() {
            return opcode + " " + dst + "," + imm;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LoadImmediate that = (LoadImmediate) o;
            return opcode == that.opcode && imm == that.imm && dst.equals(that.dst);
        }

        @Override
        public int hashCode() {
            return Objects.hash(opcode, dst, imm);
        }
    }

    /**
     * A pseudo-instruction that loads the address of its label operand into its destination register.
     */
    public static final class LoadAddress extends Instruction {
        public final Label label;
        public final Register dst;

        public LoadAddress(Register dst, Label label) {
            super(OpCode.LA);
            this.label = label;
            this.dst = dst;
        }

        public String toString() {
            return "la " + dst + "," + label;
        }

        public Register def() {
            return dst;
        }

        public List<Register> uses() {
            return List.of();
        }

        public LoadAddress rebuild(Map<Register, Register> regMap) {
            return new LoadAddress(regMap.getOrDefault(dst, dst), label);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LoadAddress that = (LoadAddress) o;
            return opcode == that.opcode && label.equals(that.label) && dst.equals(that.dst);
        }

        @Override
        public int hashCode() {
            return Objects.hash(opcode, label, dst);
        }
    }

    /**
     * A nullary instruction, i.e., an instruction that takes no arguments. Use {@link #create(OpCode.Nullary)} to
     * create nullary instructions, or use the pre-generated instructions in static fields {@link #nop},
     * {@link #pushRegisters}, and {@link #popRegisters}.
     */
    public static final class Nullary extends Instruction {
        private Nullary(OpCode.Nullary opcode) {
            super(opcode);
        }

        /**
         * An instruction that performs no action.
         */
        public static final Nullary nop = new Nullary(OpCode.NOP);

        /**
         * An intrinsic instruction that pushes onto the stack all registers currently in use by the compiler.
         */
        public static final Nullary pushRegisters = new Nullary(OpCode.PUSH_REGISTERS);

        /**
         * An intrinsic instruction that pops from the stack all registers currently in use by the compiler.
         */
        public static final Nullary popRegisters = new Nullary(OpCode.POP_REGISTERS);

        /**
         * An instruction that performs a system call.
         */
        public static final Nullary syscall = new Nullary(OpCode.SYSCALL);

        /**
         * Creates a nullary instruction from an opcode.
         *
         * @param opcode The opcode to instantiate.
         * @return A nullary instruction for {@code opcode}.
         */
        public static Nullary create(OpCode.Nullary opcode) {
            if (opcode == OpCode.NOP) {
                return nop;
            } else if (opcode == OpCode.PUSH_REGISTERS) {
                return pushRegisters;
            } else if (opcode == OpCode.POP_REGISTERS) {
                return popRegisters;
            } else if (opcode == OpCode.SYSCALL) {
                return syscall;
            } else {
                throw new Error("Cannot instantiate ill-understood opcode " + opcode);
            }
        }

        @Override
        public Register def() {
            return null;
        }

        @Override
        public List<Register> uses() {
            return List.of();
        }

        @Override
        public Instruction rebuild(Map<Register, Register> regMap) {
            return this;
        }

        @Override
        public String toString() {
            return opcode.toString();
        }
    }
}
