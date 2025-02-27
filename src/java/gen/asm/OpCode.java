// Authors: Jonathan Van der Cruysse, Christophe Dubach

// DO NOT MODIFY THIS FILE. For technical grading reasons, we may roll back this file to the original version we
// provided. This will overwrite any and all local changes you made, likely breaking your compiler if you made
// changes.
//
// Open a question on Ed if you need additional features that the classes in this file do not support, such as an
// instruction/opcode that is essential but not currently exposed.

package gen.asm;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Identifies a MIPS integer opcode.
 */
@SuppressWarnings("StaticInitializerReferencesSubClass")
public abstract class OpCode {
    /**
     * The family of opcodes to which an opcode belongs. Each family of opcodes corresponds to a family of
     * {@link Instruction} subclasses.
     */
    public enum Kind {
        /**
         * A type R opcode that takes three register operands.
         */
        TERNARY_ARITHMETIC,

        /**
         * A type R opcode that takes two register operands.
         */
        BINARY_ARITHMETIC,

        /**
         * A type R opcode that takes one register operand.
         */
        UNARY_ARITHMETIC,

        /**
         * An unconditional branch opcode.
         */
        JUMP,

        /**
         * An unconditional branch opcode that jumps to a register.
         */
        JUMP_REGISTER,

        /**
         * A branch opcode that takes two register operands and a label.
         */
        BINARY_BRANCH,

        /**
         * A branch opcode that takes on register operand and a label.
         */
        UNARY_BRANCH,

        /**
         * A binary arithmetic opcode that takes a destination register, a source register, and an immediate operand.
         */
        ARITHMETIC_WITH_IMMEDIATE,

        /**
         * A load opcode.
         */
        LOAD,

        /**
         * A store opcode.
         */
        STORE,

        /**
         * A load immediate opcode. This is an opcode that discards its source register.
         */
        LOAD_IMMEDIATE,

        /**
         * The load address pseudo-op.
         */
        LOAD_ADDRESS,

        /**
         * An opcode that takes no operands.
         */
        NULLARY
    }

    /**
     * The opcode's mnemonic, i.e., the textual representation of the opcode in an assembly file.
     */
    public final String mnemonic;

    /**
     * Creates an {@link OpCode} instance from a mnemonic.
     *
     * @param mnemonic The mnemonic that identifies the opcode.
     */
    public OpCode(final String mnemonic) {
        this.mnemonic = mnemonic;
    }

    /**
     * Gets the family of opcodes this opcode belongs to.
     *
     * @return An opcode kind.
     */
    public abstract Kind kind();

    @Override
    public String toString() {
        return mnemonic;
    }

    /**
     * Tries to interpret a mnemonic as an opcode.
     *
     * @param mnemonic The mnemonic to interpret.
     * @return An opcode corresponding to {@code mnemonic} if the latter is well-understood; otherwise,
     * {@link Optional#empty()}.
     */
    public static Optional<OpCode> tryParse(String mnemonic) {
        return allOps().stream().filter(x -> x.mnemonic.equals(mnemonic)).findAny();
    }

    public static final TernaryArithmetic ADD = new TernaryArithmetic("add");
    public static final TernaryArithmetic ADDU = new TernaryArithmetic("addu");
    public static final TernaryArithmetic AND = new TernaryArithmetic("and");
    public static final TernaryArithmetic MOVN = new TernaryArithmetic("movn");
    public static final TernaryArithmetic MOVZ = new TernaryArithmetic("movz");
    public static final TernaryArithmetic MUL = new TernaryArithmetic("mul");
    public static final TernaryArithmetic NOR = new TernaryArithmetic("nor");
    public static final TernaryArithmetic OR = new TernaryArithmetic("or");
    public static final TernaryArithmetic SLT = new TernaryArithmetic("slt");
    public static final TernaryArithmetic SLTU = new TernaryArithmetic("sltu");
    public static final TernaryArithmetic SLLV = new TernaryArithmetic("sllv");
    public static final TernaryArithmetic SRLV = new TernaryArithmetic("srlv");
    public static final TernaryArithmetic SRAV = new TernaryArithmetic("srav");
    public static final TernaryArithmetic SUB = new TernaryArithmetic("sub");
    public static final TernaryArithmetic SUBU = new TernaryArithmetic("subu");
    public static final TernaryArithmetic XOR = new TernaryArithmetic("xor");

    /**
     * A list of all known ternary type R arithmetic opcodes.
     */
    public static final List<TernaryArithmetic> ternaryArithmeticOps =
        List.of(ADD, ADDU, AND, NOR, OR, MUL, MOVN, MOVZ, SLT, SLTU, SLLV, SRLV, SRAV, SUB, SUBU, XOR);

    public static final BinaryArithmetic DIV = new BinaryArithmetic("div");
    public static final BinaryArithmetic DIVU = new BinaryArithmetic("divu");
    public static final BinaryArithmetic MADD = new BinaryArithmetic("madd");
    public static final BinaryArithmetic MADDU = new BinaryArithmetic("maddu");
    public static final BinaryArithmetic MSUB = new BinaryArithmetic("msub");
    public static final BinaryArithmetic MSUBU = new BinaryArithmetic("msubu");
    public static final BinaryArithmetic MULT = new BinaryArithmetic("mult");
    public static final BinaryArithmetic MULTU = new BinaryArithmetic("multu");

    /**
     * A list of all known binary type R arithmetic opcodes.
     */
    public static final List<BinaryArithmetic> binaryArithmeticOps =
        List.of(DIV, DIVU, MADD, MADDU, MSUB, MSUBU, MULT, MULTU);

    public static final UnaryArithmetic MFHI = new UnaryArithmetic("mfhi");
    public static final UnaryArithmetic MFLO = new UnaryArithmetic("mflo");

    /**
     * A list of all known unary type R arithmetic opcodes.
     */
    public static final List<UnaryArithmetic> unaryArithmeticOps =
        List.of(MFHI, MFLO);

    public static final ArithmeticWithImmediate ADDI = new ArithmeticWithImmediate("addi");
    public static final ArithmeticWithImmediate ADDIU = new ArithmeticWithImmediate("addiu");
    public static final ArithmeticWithImmediate ANDI = new ArithmeticWithImmediate("andi");
    public static final ArithmeticWithImmediate ORI = new ArithmeticWithImmediate("ori");
    public static final ArithmeticWithImmediate SLL = new ArithmeticWithImmediate("sll");
    public static final ArithmeticWithImmediate SRA = new ArithmeticWithImmediate("sra");
    public static final ArithmeticWithImmediate SRL = new ArithmeticWithImmediate("srl");
    public static final ArithmeticWithImmediate SLTI = new ArithmeticWithImmediate("slti");
    public static final ArithmeticWithImmediate SLTIU = new ArithmeticWithImmediate("sltiu");
    public static final ArithmeticWithImmediate XORI = new ArithmeticWithImmediate("xori");

    /**
     * A list of all known type I arithmetic opcodes.
     */
    public static final List<ArithmeticWithImmediate> arithmeticWithImmediateOps =
        List.of(ADDI, ADDIU, ANDI, ORI, SLL, SRA, SRL, SLTI, SLTIU, XORI);

    public static final BinaryBranch BEQ = new BinaryBranch("beq");
    public static final BinaryBranch BNE = new BinaryBranch("bne");

    /**
     * A list of all binary control flow opcodes.
     */
    public static final List<BinaryBranch> binaryBranchOps = List.of(BEQ, BNE);

    public static final UnaryBranch BEQZ = new UnaryBranch("beqz");
    public static final UnaryBranch BGEZ = new UnaryBranch("bgez");
    public static final UnaryBranch BGEZAL = new UnaryBranch("bgezal");
    public static final UnaryBranch BGTZ = new UnaryBranch("bgtz");
    public static final UnaryBranch BLEZ = new UnaryBranch("blez");
    public static final UnaryBranch BLTZ = new UnaryBranch("bltz");
    public static final UnaryBranch BLTZAL = new UnaryBranch("bltzal");
    public static final UnaryBranch BNEZ = new UnaryBranch("bnez");

    /**
     * A list of all unary control flow opcodes.
     */
    public static final List<UnaryBranch> unaryBranchOps =
        List.of(BEQZ, BGEZ, BGEZAL, BGTZ, BLEZ, BLTZ, BLTZAL, BNEZ);

    public static final Jump B = new Jump("b");
    public static final Jump BAL = new Jump("bal");
    public static final Jump J = new Jump("j");
    public static final Jump JAL = new Jump("jal");

    public static final JumpRegister JR = new JumpRegister("jr");
    public static final JumpRegister JALR = new JumpRegister("jalr");

    /**
     * A list of unconditional branch opcodes.
     */
    public static final List<Jump> jumpOps = List.of(B, BAL, J, JAL);
    public static final List<JumpRegister> jumpRegisterOps = List.of(JR, JALR);


    public static final Nullary NOP = new Nullary("nop");
    public static final Nullary PUSH_REGISTERS = new Nullary("pushRegisters");
    public static final Nullary POP_REGISTERS = new Nullary("popRegisters");
    public static final Nullary SYSCALL = new Nullary("syscall");

    /**
     * A list of all known nullary opcodes.
     */
    public static final List<Nullary> nullaryOps = List.of(NOP, PUSH_REGISTERS, POP_REGISTERS, SYSCALL);

    public static final Load LB = new Load("lb");
    public static final Load LBU = new Load("lbu");
    public static final Load LH = new Load("lh");
    public static final Load LHU = new Load("lhu");
    public static final Load LW = new Load("lw");
    public static final Load LL = new Load("ll");
    public static final Load ULW = new Load("ulw");

    /**
     * A list of all known type I load opcodes.
     */
    public static final List<Load> loadOps = List.of(LB, LBU, LH, LHU, LW, LL, ULW);

    public static final Store SB = new Store("sb");
    public static final Store SH = new Store("sh");
    public static final Store SW = new Store("sw");
    public static final Store SC = new Store("sc");
    public static final Store USW = new Store("usw");

    /**
     * A list of all known type I store opcodes.
     */
    public static final List<Store> storeOps = List.of(SB, SH, SW, SC, USW);

    public static final LoadImmediate LUI = new LoadImmediate("lui");
    public static final LoadImmediate LI = new LoadImmediate("li");
    public static final LoadAddress LA = new LoadAddress("la");

    /**
     * Gets a list of all opcodes known to the compiler.
     */
    public static List<OpCode> allOps() {
        return Stream.of(
                ternaryArithmeticOps.stream().map(x -> (OpCode) x),
                binaryArithmeticOps.stream().map(x -> (OpCode) x),
                unaryArithmeticOps.stream().map(x -> (OpCode) x),
                arithmeticWithImmediateOps.stream().map(x -> (OpCode) x),
                binaryBranchOps.stream().map(x -> (OpCode) x),
                unaryBranchOps.stream().map(x -> (OpCode) x),
                jumpOps.stream().map(x -> (OpCode) x),
                jumpRegisterOps.stream().map(x -> (OpCode) x),
                loadOps.stream().map(x -> (OpCode) x),
                storeOps.stream().map(x -> (OpCode) x),
                nullaryOps.stream().map(x -> (OpCode) x),
                Stream.of(LUI, LI, LA, JR)
        ).flatMap(s -> s).collect(Collectors.toList());
    }

    /**
     * An opcode for ternary type R arithmetic instructions.
     */
    public static final class TernaryArithmetic extends OpCode {
        private TernaryArithmetic(String mnemonic) {
            super(mnemonic);
        }

        @Override
        public Kind kind() {
            return Kind.TERNARY_ARITHMETIC;
        }
    }

    /**
     * An opcode for binary type R arithmetic instructions.
     */
    public static final class BinaryArithmetic extends OpCode {
        private BinaryArithmetic(String mnemonic) {
            super(mnemonic);
        }

        @Override
        public Kind kind() {
            return Kind.BINARY_ARITHMETIC;
        }
    }

    /**
     * An opcode for unary type R arithmetic instructions.
     */
    public static final class UnaryArithmetic extends OpCode {
        private UnaryArithmetic(String mnemonic) {
            super(mnemonic);
        }

        @Override
        public Kind kind() {
            return Kind.UNARY_ARITHMETIC;
        }
    }

    /**
     * An opcode for type I arithmetic instructions.
     */
    public static final class ArithmeticWithImmediate extends OpCode {
        private ArithmeticWithImmediate(String mnemonic) {
            super(mnemonic);
        }

        @Override
        public Kind kind() {
            return Kind.ARITHMETIC_WITH_IMMEDIATE;
        }
    }

    /**
     * A pseudo-opcode for intrinsics.
     */
    public static final class Nullary extends OpCode {
        private Nullary(String mnemonic) {
            super(mnemonic);
        }

        @Override
        public Kind kind() {
            return Kind.NULLARY;
        }
    }

    /**
     * An opcode for unconditional branch instructions.
     */
    public static final class Jump extends OpCode {
        private Jump(String mnemonic) {
            super(mnemonic);
        }

        @Override
        public Kind kind() {
            return Kind.JUMP;
        }
    }

    /**
     * An opcode for the jump-register instruction.
     */
    public static final class JumpRegister extends OpCode {
        private JumpRegister(String mnemonic) {
            super(mnemonic);
        }

        @Override
        public Kind kind() {
            return Kind.JUMP_REGISTER;
        }
    }

    /**
     * An opcode for branch instructions that take two register operands and a label.
     */
    public static final class BinaryBranch extends OpCode {
        private BinaryBranch(String mnemonic) {
            super(mnemonic);
        }

        @Override
        public Kind kind() {
            return Kind.BINARY_BRANCH;
        }
    }

    /**
     * An opcode for branch instructions that take one register operand and a label.
     */
    public static final class UnaryBranch extends OpCode {
        private UnaryBranch(String mnemonic) {
            super(mnemonic);
        }

        @Override
        public Kind kind() {
            return Kind.UNARY_BRANCH;
        }
    }

    /**
     * An opcode for load instructions.
     */
    public static final class Load extends OpCode {
        private Load(String mnemonic) {
            super(mnemonic);
        }

        @Override
        public Kind kind() {
            return Kind.LOAD;
        }
    }

    /**
     * An opcode for store instructions.
     */
    public static final class Store extends OpCode {
        private Store(String mnemonic) {
            super(mnemonic);
        }

        @Override
        public Kind kind() {
            return Kind.STORE;
        }
    }

    /**
     * An opcode for the load upper immediate (lui) instruction.
     */
    public static final class LoadImmediate extends OpCode {
        private LoadImmediate(String mnemonic) {
            super(mnemonic);
        }

        @Override
        public Kind kind() {
            return Kind.LOAD_IMMEDIATE;
        }
    }

    /**
     * An opcode for the load address (la) pseudo-instruction.
     */
    public static final class LoadAddress extends OpCode {
        private LoadAddress(String mnemonic) {
            super(mnemonic);
        }

        @Override
        public Kind kind() {
            return Kind.LOAD_ADDRESS;
        }
    }
}
