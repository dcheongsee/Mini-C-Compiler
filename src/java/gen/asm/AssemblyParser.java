// Authors: Jonathan Van der Cruysse

// DO NOT MODIFY THIS FILE. For technical grading reasons, we may roll back this file to the original version we
// provided. This will overwrite any and all local changes you made, likely breaking your compiler if you made
// changes.
//
// Open a question on Ed if you need additional features that the classes in this file do not support, such as an
// instruction/opcode that is essential but not currently exposed.

package gen.asm;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Parses {@link AssemblyProgram} instances from textual assembly files. Supports the same subset of MIPS assembly that
 * {@link AssemblyProgram#print(PrintWriter)} outputs.
 */
public final class AssemblyParser {
    /**
     * Reads a MIPS assembly program from a buffered reader.
     *
     * @param reader A buffered reader that reads from a textual MIPS assembly program.
     * @return An {@link AssemblyProgram} instance that corresponds to the text being read by {@code reader}.
     */
    public static AssemblyProgram readAssemblyProgram(final BufferedReader reader) {
        var program = new AssemblyProgram();
        AssemblyProgram.Section currentSection = program.dataSection; // always start in the data section
        for (var line : reader.lines().toList()) {
            var item = parseAssemblyItem(line);

            assert (currentSection != null);
            switch (item) {
                case null -> {/* nothing to do*/}
                case Directive d -> {
                    if (d.name.equals("data"))
                        currentSection = program.dataSection;
                     else if (d.name.equals("text"))
                        currentSection = program.emitNewTextSection();
                     else
                         currentSection.emit(d);
                }
                case AssemblyTextItem ati -> currentSection.emit(ati);
                case Instruction i -> {
                    switch (currentSection) {
                        case null -> {new Error("Empty section");}
                        case AssemblyProgram.DataSection ds -> {new Error("Found instruction in data section");}
                        case AssemblyProgram.TextSection ts -> ts.items.add(item);
                    }
                }
            }
        }

        return program;
    }

    /**
     * Parses a string as an {@link AssemblyItem}.
     *
     * @param line A single line of MIPS assembly.
     * @return An assembly item if the line is nonempty; otherwise, {@code null}.
     */
    public static AssemblyItem parseAssemblyItem(String line) {
        // Check if the line looks promising.
        if (line == null || line.isBlank()) {
            return null;
        }

        // Trim the line if it looks like an assembly item.
        line = line.trim();
        if (line.startsWith("#")) {
            // Comments start with a hashtag.
            return new Comment(line.substring(1).trim());
        } else if (line.startsWith(".")) {
            // Directives start with a dot.
            return new Directive(line.substring(1));
        } else if (line.endsWith(":")) {
            // Label definitions end with a colon and must be identifiers.
            String labelIdentifier = line.substring(0, line.length() - 1);
            if (!isLabel(labelIdentifier)) {
                throw new Error("Expected a label identifier; found " + labelIdentifier);
            }
            return Label.get(labelIdentifier);
        } else {
            // Anything else must be an instruction. The general format for instructions is `opcode arg1, arg2, ...`.
            String[] opcodeAndArgs = line.split(" ", 2);
            if (opcodeAndArgs.length > 2) {
                throw new Error("Expected an instruction; found " + line);
            } else if (opcodeAndArgs.length == 1) {
                opcodeAndArgs = new String[]{opcodeAndArgs[0], ""};
            }

            // Split and parse the arguments.
            var args = Arrays.stream(opcodeAndArgs[1].split(",")).map(String::trim).collect(Collectors.toList());
            if (args.size() == 1 && args.get(0).isBlank()) {
                args = List.of();
            }

            // We now branch based on the opcode.
            var op = OpCode.tryParse(opcodeAndArgs[0].trim());
            if (op.isEmpty()) {
                throw new Error("Ill-understood opcode " + opcodeAndArgs[0].trim());
            }

            var opcode = op.get();
            switch (opcode.kind()) {
                case LOAD_ADDRESS:
                    checkArity(args, 2, line);
                    return new Instruction.LoadAddress(parseRegister(args.get(0)), parseLabel(args.get(1)));

                case TERNARY_ARITHMETIC:
                    checkArity(args, 3, line);
                    return new Instruction.TernaryArithmetic(
                            (OpCode.TernaryArithmetic) opcode,
                            parseRegister(args.get(0)),
                            parseRegister(args.get(1)),
                            parseRegister(args.get(2)));

                case BINARY_ARITHMETIC:
                    checkArity(args, 2, line);
                    return new Instruction.BinaryArithmetic(
                            (OpCode.BinaryArithmetic) opcode,
                            parseRegister(args.get(0)),
                            parseRegister(args.get(1)));

                case UNARY_ARITHMETIC:
                    checkArity(args, 1, line);
                    return new Instruction.UnaryArithmetic(
                            (OpCode.UnaryArithmetic) opcode,
                            parseRegister(args.get(0)));

                case JUMP:
                    checkArity(args, 1, line);
                    return new Instruction.Jump(
                            (OpCode.Jump) opcode,
                            parseLabel(args.get(0)));

                case JUMP_REGISTER:
                    checkArity(args, 1, line);
                    return new Instruction.JumpRegister(
                            (OpCode.JumpRegister) opcode,
                            parseRegister(args.get(0)));

                case BINARY_BRANCH:
                    checkArity(args, 3, line);
                    return new Instruction.BinaryBranch(
                            (OpCode.BinaryBranch) opcode,
                            parseRegister(args.get(0)),
                            parseRegister(args.get(1)),
                            parseLabel(args.get(2)));

                case UNARY_BRANCH:
                    checkArity(args, 2, line);
                    return new Instruction.UnaryBranch(
                            (OpCode.UnaryBranch) opcode,
                            parseRegister(args.get(0)),
                            parseLabel(args.get(1)));

                case ARITHMETIC_WITH_IMMEDIATE:
                    checkArity(args, 3, line);
                    return new Instruction.ArithmeticWithImmediate(
                            (OpCode.ArithmeticWithImmediate) opcode,
                            parseRegister(args.get(0)),
                            parseRegister(args.get(1)),
                            parseImmediate(args.get(2)));

                case LOAD: {
                    checkArity(args, 2, line);
                    var memOperand = parseMemoryOperand(args.get(1));
                    return new Instruction.Load(
                            (OpCode.Load) opcode,
                            parseRegister(args.get(0)),
                            memOperand.getKey(),
                            memOperand.getValue());
                }

                case STORE:
                    checkArity(args, 2, line);
                    var memOperand = parseMemoryOperand(args.get(1));
                    return new Instruction.Store(
                            (OpCode.Store) opcode,
                            parseRegister(args.get(0)),
                            memOperand.getKey(),
                            memOperand.getValue());

                case LOAD_IMMEDIATE:
                    checkArity(args, 2, line);
                    return new Instruction.LoadImmediate(
                            (OpCode.LoadImmediate) opcode,
                            parseRegister(args.get(0)),
                            parseImmediate(args.get(1)));

                case NULLARY:
                    checkArity(args, 0, line);
                    return Instruction.Nullary.create((OpCode.Nullary) opcode);

                default:
                    throw new Error("Cannot parse ill-understood instruction " + line);
            }
        }
    }

    private static void checkArity(List<String> args, int expectedArity, String line) {
        if (args.size() != expectedArity) {
            throw new Error(
                    "The expected number of arguments was " + expectedArity + "; got " + args.size() + ": " + line);
        }
    }

    private static Register parseRegister(String name) {
        if (!name.codePoints().allMatch(Character::isJavaIdentifierPart)) {
            throw new Error("Expected a register name, got " + name);
        }

        if (name.startsWith("$")) {
            // We found an architectural register.
            var candidate = Register.Arch.allRegisters
                    .stream()
                    .filter(r -> r.toString().equals(name))
                    .findFirst();
            if (candidate.isEmpty()) {
                throw new Error("Expected an architectural register name, got " + name);
            }
            return candidate.get();
        } else {
            // Looks we just ran into a virtual register.
            return Register.Virtual.get(name);
        }
    }

    private static Label parseLabel(String name) {
        if (!isLabel(name)) {
            throw new Error("Expected a label, got " + name);
        }
        return Label.get(name);
    }

    private static boolean isLabel(String text) {
        return text.length() > 0
                && Character.isJavaIdentifierStart(text.codePointAt(0))
                && text.codePoints().allMatch(Character::isJavaIdentifierPart);
    }

    private static int parseImmediate(String text) {
        return Integer.parseInt(text);
    }

    private static Map.Entry<Register, Integer> parseMemoryOperand(String text) {
        if (text.startsWith("(")) {
            // If a memory operand starts with a parenthesis, then it must be an offset-free operand. That is, it must
            // have an offset of zero.
            if (text.charAt(text.length() - 1) != ')') {
                throw new Error("Expected a memory operand, got " + text);
            }

            var register = parseRegister(text.substring(1, text.length() - 1));
            return new AbstractMap.SimpleEntry<>(register, 0);
        }

        var pieces = text.split("\\(", 2);
        if (pieces.length != 2 || pieces[1].charAt(pieces[1].length() - 1) != ')') {
            throw new Error("Expected a memory operand, got " + text);
        }

        int immediate = parseImmediate(pieces[0]);
        var register = parseRegister(pieces[1].substring(0, pieces[1].length() - 1));
        return new AbstractMap.SimpleEntry<>(register, immediate);
    }
}
