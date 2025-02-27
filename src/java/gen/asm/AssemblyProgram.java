// Authors: Jonathan Van der Cruysse, Christophe Dubach

// DO NOT MODIFY THIS FILE. For technical grading reasons, we may roll back this file to the original version we
// provided. This will overwrite any and all local changes you made, likely breaking your compiler if you made
// changes.
//
// Open a question on Ed if you need additional features that the classes in this file do not support, such as an
// instruction/opcode that is essential but not currently exposed.

package gen.asm;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A MIPS assembly program.
 */
public final class AssemblyProgram {

    public sealed static abstract class Section {

        @SuppressWarnings("unused")
        public abstract void emit(AssemblyTextItem ati);

        @SuppressWarnings("unused")
        public void emit(String comment) {
            emit(new Comment(comment));
        }

        public abstract void print(final PrintWriter writer);

        @Override
        public String toString()
        {
            var sw = new StringWriter();
            var pw = new PrintWriter(sw);
            print(pw);
            return sw.toString();
        }


    }

    public static final class DataSection extends Section {

        public final List<AssemblyTextItem> items = new ArrayList<>();

        public void emit(AssemblyTextItem ati) {
            items.add(ati);
        }

        @Override
        public boolean equals(Object o) {
            return switch (o) {
                case DataSection s -> items.equals(s.items);
                case null, default -> false;
            };
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.getClass().getName(),items);
        }

        public void print(final PrintWriter writer) {
            writer.println(".data");
            items.forEach(item -> {
                        switch (item) {
                            case Comment comment -> writer.println(comment);
                            case Label label -> writer.println(label + ":");
                            case Directive directive -> writer.println(directive);
                        }
                    }
            );
        }

    }

    public static final class TextSection extends Section {

        public final List<AssemblyItem> items = new ArrayList<>();

        @Override
        public boolean equals(Object o) {
            return switch (o) {
                case TextSection s -> items.equals(s.items);
                case null, default -> false;
            };
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.getClass().getName(),items);
        }

        @SuppressWarnings("unused")
        public void emit(AssemblyTextItem ati) {
            items.add(ati);
        }

        @SuppressWarnings("unused")
        public void emit(Instruction instruction) {
            items.add(instruction);
        }

        @SuppressWarnings("unused")
        public void emit(OpCode.TernaryArithmetic opcode, Register dst, Register src1, Register src2) {
            emit(new Instruction.TernaryArithmetic(opcode, dst, src1, src2));
        }

        @SuppressWarnings("unused")
        public void emit(OpCode.BinaryArithmetic opcode, Register src1, Register src2) {
            emit(new Instruction.BinaryArithmetic(opcode, src1, src2));
        }

        @SuppressWarnings("unused")
        public void emit(OpCode.UnaryArithmetic opcode, Register dst) {
            emit(new Instruction.UnaryArithmetic(opcode, dst));
        }

        @SuppressWarnings("unused")
        public void emit(OpCode.BinaryBranch opcode, Register src1, Register src2, Label label) {
            emit(new Instruction.BinaryBranch(opcode, src1, src2, label));
        }

        @SuppressWarnings("unused")
        public void emit(OpCode.UnaryBranch opcode, Register src, Label label) {
            emit(new Instruction.UnaryBranch(opcode, src, label));
        }

        @SuppressWarnings("unused")
        public void emit(OpCode.Jump opcode, Label label) {
            emit(new Instruction.Jump(opcode, label));
        }

        @SuppressWarnings("unused")
        public void emit(OpCode.JumpRegister opcode, Register address) {
            emit(new Instruction.JumpRegister(opcode, address));
        }

        @SuppressWarnings("unused")
        public void emit(OpCode.ArithmeticWithImmediate opcode, Register dst, Register src, int imm) {
            emit(new Instruction.ArithmeticWithImmediate(opcode, dst, src, imm));
        }

        @SuppressWarnings("unused")
        public void emit(OpCode.LoadAddress ignoredOpcode, Register dst, Label label) {
            emit(new Instruction.LoadAddress(dst, label));
        }

        @SuppressWarnings("unused")
        public void emit(OpCode.LoadImmediate opcode, Register dst, int immediate) {
            emit(new Instruction.LoadImmediate(opcode, dst, immediate));
        }

        @SuppressWarnings("unused")
        public void emit(OpCode.Load opcode, Register val, Register addr, int imm) {
            emit(new Instruction.Load(opcode, val, addr, imm));
        }

        @SuppressWarnings("unused")
        public void emit(OpCode.Store opcode, Register val, Register addr, int imm) {
            emit(new Instruction.Store(opcode, val, addr, imm));
        }

        @SuppressWarnings("unused")
        public void emit(OpCode.Nullary opcode) {
            emit(Instruction.Nullary.create(opcode));
        }


        public void print(final PrintWriter writer) {
            writer.println(".text");
            items.forEach(item -> {
                switch(item) {
                    case Comment comment -> writer.println(comment);
                    case Label label -> writer.println(label + ":");
                    case Directive directive -> writer.println(directive);
                    case Instruction instruction -> writer.println(instruction);
                }}
            );
        }


    }

    /**
     * We restrict an assembly program to have a single data section.
     * This should contain all the global variable declarations.
     */
    public final DataSection dataSection = new DataSection();

    /**
     * Each TextSection represents a single function.
     */
    public final List<TextSection> textSections = new ArrayList<>();



    private TextSection currFunSection;

    /**
     * Gets the current section. That is, the section that was last added to this {@link AssemblyProgram}.
     * @return This program's current section.
     */
    public TextSection getCurrentTextSection() {
        return currFunSection;
    }

    /**
     * Appends a given section to this program. The section will become the current section, as produced by
     * {@link #getCurrentTextSection()}.
     * @param ts The section to append to this program.
     */
    public void emitTextSection(TextSection ts) {
        currFunSection = ts;
        textSections.add(ts);
    }

    /**
     * Creates a new section and appends it to this program. The new section will become the current section, as
     * produced by {@link #getCurrentTextSection()}.
     * @return The newly created section.
     */
    public TextSection emitNewTextSection() {
        emitTextSection(new TextSection());
        return getCurrentTextSection();
    }

    /**
     * Sends a textual representation of this assembly program to a {@link PrintWriter}.
     * @param writer The writer to send a textual version of this program to.
     */
    public void print(final PrintWriter writer) {
        dataSection.print(writer);
        writer.println();
        textSections.forEach(ts -> {
            ts.print(writer);
            writer.println();
        });

        writer.close();
    }

    @Override
    public boolean equals(Object o) {
        return switch (o) {
            case AssemblyProgram as -> dataSection.equals(as.dataSection) && textSections.equals(as.textSections);
            case null, default -> false;
        };
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataSection,textSections);
    }
}
