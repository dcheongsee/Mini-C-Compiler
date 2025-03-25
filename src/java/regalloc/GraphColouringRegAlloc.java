package regalloc;

import gen.asm.*;

import java.util.*;

public class GraphColouringRegAlloc implements AssemblyPass {

    public static final GraphColouringRegAlloc INSTANCE = new GraphColouringRegAlloc();

    // reserve two registers for spilled operand loads
    private static final Register SPILL_REG1 = Register.Arch.t8;
    private static final Register SPILL_REG2 = Register.Arch.t9;
    // reserve third register solely for spilled def (used as temp for storing computed value into memory)
    private static final Register SPILL_REG3 = Register.Arch.s7;

    @Override
    public AssemblyProgram apply(AssemblyProgram program) {
        AssemblyProgram newProg = new AssemblyProgram();

        // copy original data section
        newProg.dataSection.items.addAll(program.dataSection.items);

        // process each text section
        for (AssemblyProgram.TextSection section : program.textSections) {
            ControlFlowGraph cfg = new ControlFlowGraph(section.items);
            cfg.computeLiveness();

            // dump cfg dot if debugging is enabled
            if (System.getProperty("debug.dot") != null) {
                System.out.println("CFG DOT output:\n" + cfg.toDot());
            }

            // first allocation attempt with full registers
            InterferenceGraph ig = new InterferenceGraph(cfg);
            ig.color();

            Map<Register, Register> coloring = ig.getColoring();
            Set<Register> spilled = ig.getSpilled();

            // if spilling, re-run allocation with reserved spill registers
            if (!spilled.isEmpty()) {
                System.out.println("Spilling detected. Re-running allocation with reserved spill registers.");
                ig = new InterferenceGraph(cfg, 14); // reserve 2 registers for spilling
                ig.color();
                coloring = ig.getColoring();
                spilled = ig.getSpilled();
            }

            // allocate spill labels in data section
            Map<Register, Label> spillLabels = new HashMap<>();
            for (Register vr : spilled) {
                Label label = Label.create("spill_" + vr.toString());
                spillLabels.put(vr, label);
                newProg.dataSection.emit(label);
                newProg.dataSection.emit(new Directive("word 0"));
            }

            // patch instructions
            AssemblyProgram.TextSection newSection = newProg.emitNewTextSection();
            for (AssemblyItem item : section.items) {
                if (item instanceof Instruction) {
                    Instruction instr = (Instruction) item;
                    // Skip dead arithmetic instructions
                    if (cfg.isDead(instr)) {
                        continue;
                    }
                    if (instr == Instruction.Nullary.pushRegisters) {
                        expandPushRegisters(newSection, coloring.values());
                    } else if (instr == Instruction.Nullary.popRegisters) {
                        expandPopRegisters(newSection, coloring.values());
                    } else {
                        patchInstruction(newSection, instr, coloring, spillLabels);
                    }
                } else {
                    if (item instanceof AssemblyTextItem) {
                        newSection.emit((AssemblyTextItem) item);
                    } else {
                        throw new IllegalArgumentException("Unsupported AssemblyItem type: " + item.getClass());
                    }
                }
            }

            // dump interference graph dot if debugging enabled
            if (System.getProperty("debug.dot") != null) {
                InterferenceGraph igForDot = new InterferenceGraph(cfg, coloring.size() + spilled.size());
                System.out.println("Interference Graph DOT output:\n" + igForDot.toDot());
            }
        }

        return newProg;
    }


    private void patchInstruction(AssemblyProgram.TextSection section, Instruction instr, Map<Register, Register> coloring, Map<Register, Label> spillLabels) {
        Map<Register, Register> regMap = new HashMap<>();
        int spilledUseCount = 0;
        // handle uses, replace each spilled operand
        for (Register r : instr.uses()) {
            if (spillLabels.containsKey(r)) {
                Label label = spillLabels.get(r);
                // allow at most two spilled uses
                if (spilledUseCount == 0) {
                    // use SPILL_REG1 for first spilled use
                    regMap.put(r, SPILL_REG1);
                    section.emit(OpCode.LA, SPILL_REG1, label); // Load spill address.
                    section.emit(OpCode.LW, SPILL_REG1, SPILL_REG1, 0); // Load value.
                } else if (spilledUseCount == 1) {
                    // use SPILL_REG2 for second spilled use
                    regMap.put(r, SPILL_REG2);
                    section.emit(OpCode.LA, SPILL_REG2, label);
                    section.emit(OpCode.LW, SPILL_REG2, SPILL_REG2, 0);
                } else {
                    throw new RuntimeException("Instruction has more than 2 spilled operands, which should not happen.");
                }
                spilledUseCount++;
            } else if (coloring.containsKey(r)) {
                regMap.put(r, coloring.get(r));
            } else {
                regMap.put(r, r);
            }
        }
        // handle def
        Register def = instr.def();
        if (def != null) {
            if (spillLabels.containsKey(def)) {
                // for spilled def, if no spilled use encountered, use SPILL_REG2;
                // if one spilled use occurred, use SPILL_REG1
                // if two spilled uses occurred, we reserve SPILL_REG3
                if (spilledUseCount == 0) {
                    regMap.put(def, SPILL_REG2);
                } else if (spilledUseCount == 1) {
                    regMap.put(def, SPILL_REG1);
                } else if (spilledUseCount == 2) {
                    regMap.put(def, SPILL_REG3);
                } else {
                    throw new RuntimeException("Unexpected number of spilled uses.");
                }
            } else if (coloring.containsKey(def)) {
                regMap.put(def, coloring.get(def));
            } else {
                regMap.put(def, def);
            }
        }
        // rebuild and emit patched instruction
        Instruction patched = instr.rebuild(regMap);
        section.emit(patched);

        // for spilled defs, emit a store to update its spill slot
        if (def != null && spillLabels.containsKey(def)) {
            Label label = spillLabels.get(def);
            // use temp to load spill slot address
            section.emit(OpCode.LA, SPILL_REG3, label);
            section.emit(OpCode.SW, regMap.get(def), SPILL_REG3, 0);
        }
    }

    private void expandPushRegisters(AssemblyProgram.TextSection section, Collection<Register> regs) {
        int offset = 0;
        for (Register r : regs) {
            if (r == SPILL_REG1 || r == SPILL_REG2 || r == SPILL_REG3) continue;
            section.emit(OpCode.SW, r, Register.Arch.sp, offset);
            offset -= 4;
        }
        if (offset != 0) {
            section.emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, offset);
        }
    }

    private void expandPopRegisters(AssemblyProgram.TextSection section, Collection<Register> regs) {
        int offset = -4 * (regs.size() - 3); // adjust for reserved spill registers
        for (Register r : regs) {
            if (r == SPILL_REG1 || r == SPILL_REG2 || r == SPILL_REG3) continue;
            section.emit(OpCode.LW, r, Register.Arch.sp, offset);
            offset += 4;
        }
        if (offset != 0) {
            section.emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -offset);
        }
    }
}