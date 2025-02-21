package gen.asm;

import regalloc.NaiveRegAlloc;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        AssemblyProgram prog = new AssemblyProgram();

        AssemblyProgram.TextSection text = prog.emitNewTextSection();
        Register v1 = Register.Virtual.create();
        Register v2 = Register.Virtual.create();
        Register v3 = Register.Virtual.create();
        text.emit(OpCode.PUSH_REGISTERS);
        text.emit(OpCode.ADDI, v1, Register.Arch.zero, 4);
        text.emit(OpCode.ADDI, v2, Register.Arch.zero, 8);
        text.emit(OpCode.ADD, v3, v1, v2);
        text.emit(OpCode.POP_REGISTERS);

        try {
            PrintWriter writer = new PrintWriter("t1.asm");
            prog.print(writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        AssemblyProgram newProg = NaiveRegAlloc.INSTANCE.apply(prog);

        try {
            PrintWriter writer = new PrintWriter("t2.asm");
            newProg.print(writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        canParseAssemblyItems();
        assertCanRoundTrip(prog);
        assertCanRoundTrip(newProg);
    }

    private static void canParseAssemblyItems() {
        assertEqual(AssemblyParser.parseAssemblyItem("# hello"), new Comment("hello"));
        assertEqual(AssemblyParser.parseAssemblyItem("label:"), Label.get("label"));
        assertEqual(AssemblyParser.parseAssemblyItem(".space 4"), new Directive("space 4"));
        assertEqual(
            AssemblyParser.parseAssemblyItem("add $t0, v0, $t2"),
            new Instruction.TernaryArithmetic(
                OpCode.ADD,
                Register.Arch.t0,
                Register.Virtual.get("v0"),
                Register.Arch.t2));
        assertEqual(
            AssemblyParser.parseAssemblyItem("addi $t0, v0, 15"),
            new Instruction.ArithmeticWithImmediate(
                OpCode.ADDI,
                Register.Arch.t0,
                Register.Virtual.get("v0"),
                15));
        assertEqual(
            AssemblyParser.parseAssemblyItem("beq $t0, v0, branch_target"),
            new Instruction.BinaryBranch(
                OpCode.BEQ,
                Register.Arch.t0,
                Register.Virtual.get("v0"),
                Label.get("branch_target")));
        assertEqual(
            AssemblyParser.parseAssemblyItem("bgtz $t0, branch_target"),
            new Instruction.UnaryBranch(
                OpCode.BGTZ,
                Register.Arch.t0,
                Label.get("branch_target")));
        assertEqual(
            AssemblyParser.parseAssemblyItem("jal jump_target"),
            new Instruction.Jump(
                OpCode.JAL,
                Label.get("jump_target")));
        assertEqual(
            AssemblyParser.parseAssemblyItem("jr $s0"),
            new Instruction.JumpRegister(
                OpCode.JR,
                Register.Arch.s0));
        assertEqual(
            AssemblyParser.parseAssemblyItem("la $s0, address"),
            new Instruction.LoadAddress(
                Register.Arch.s0,
                Label.get("address")));
        assertEqual(
            AssemblyParser.parseAssemblyItem("lui $s0, 42"),
            new Instruction.LoadImmediate(
                OpCode.LUI,
                Register.Arch.s0,
                42));
        assertEqual(
            AssemblyParser.parseAssemblyItem("lbu $t0, 16($t1)"),
            new Instruction.Load(
                OpCode.LBU,
                Register.Arch.t0,
                Register.Arch.t1,
                16));
        assertEqual(
            AssemblyParser.parseAssemblyItem("lbu $t0, ($t1)"),
            new Instruction.Load(
                OpCode.LBU,
                Register.Arch.t0,
                Register.Arch.t1,
                0));
        assertEqual(
            AssemblyParser.parseAssemblyItem("sw $t0, 16($t1)"),
            new Instruction.Store(
                OpCode.SW,
                Register.Arch.t0,
                Register.Arch.t1,
                16));
        assertEqual(
            AssemblyParser.parseAssemblyItem("multu $t0, $t1"),
            new Instruction.BinaryArithmetic(
                OpCode.MULTU,
                Register.Arch.t0,
                Register.Arch.t1));
        assertEqual(
            AssemblyParser.parseAssemblyItem("mfhi $t0"),
            new Instruction.UnaryArithmetic(
                OpCode.MFHI,
                Register.Arch.t0));
        assertEqual(
            AssemblyParser.parseAssemblyItem("pushRegisters"),
            Instruction.Nullary.pushRegisters);
        assertEqual(
            AssemblyParser.parseAssemblyItem("popRegisters"),
            Instruction.Nullary.popRegisters);
    }

    private static void assertEqual(AssemblyItem first, AssemblyItem second) {
        if (!first.equals(second)) {
            throw new Error(first + " does not equal " + second);
        }
    }

    private static void assertCanRoundTrip(AssemblyProgram program) {
        // First, we convert our AssemblyProgram to a textual MIPS assembly file. We will store the file in memory in
        // the form of a string.
        var out = new StringWriter();
        var writer = new PrintWriter(out);
        program.print(writer);

        // Next, we read the file we just created. This effectively creates a copy of `program`.
        var programCopy = AssemblyParser.readAssemblyProgram(
            new BufferedReader(new StringReader(out.toString())));

        // Now all we need to do is simply check that the programs are equivalent.
        if (!program.equals(programCopy)) {
            throw new Error("Cannot round-trip program:\n" + out);
        }
    }
}
