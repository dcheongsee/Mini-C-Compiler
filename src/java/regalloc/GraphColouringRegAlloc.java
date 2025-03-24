package regalloc;

import gen.asm.*;

import java.util.*;

public class GraphColouringRegAlloc implements AssemblyPass {

    public static final GraphColouringRegAlloc INSTANCE = new GraphColouringRegAlloc();

    private static final Register SPILL_REG1 = Register.Arch.t8;
    private static final Register SPILL_REG2 = Register.Arch.t9;

    @Override
    public AssemblyProgram apply(AssemblyProgram program) {
        AssemblyProgram newProg = new AssemblyProgram();

        // process each text section (function)
        for (AssemblyProgram.TextSection section : program.textSections) {
            ControlFlowGraph cfg = new ControlFlowGraph(section.items);
            cfg.computeLiveness();
            InterferenceGraph ig = new InterferenceGraph(cfg);
            ig.color();

            Map<Register, Register> coloring = ig.getColoring();
            Set<Register> spilled = ig.getSpilled();

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
                    if (instr == Instruction.Nullary.pushRegisters) {
                        expandPushRegisters(newSection, coloring.values());
                    } else if (instr == Instruction.Nullary.popRegisters) {
                        expandPopRegisters(newSection, coloring.values());
                    } else {
                        patchInstruction(newSection, instr, coloring, spillLabels);
                    }
                } else {
                    if (item instanceof Instruction) {
                        newSection.emit((Instruction) item);
                    } else if (item instanceof AssemblyTextItem) {
                        newSection.emit((AssemblyTextItem) item);
                    } else {
                        throw new IllegalArgumentException("Unsupported AssemblyItem type: " + item.getClass());
                    }
                }
            }
        }

        return newProg;
    }

    private void patchInstruction(AssemblyProgram.TextSection section, Instruction instr, Map<Register, Register> coloring, Map<Register, Label> spillLabels) {
        Map<Register, Register> regMap = new HashMap<>();

        // handle uses
        for (Register r : instr.uses()) {
            if (spillLabels.containsKey(r)) {
                Label label = spillLabels.get(r);
                section.emit(OpCode.LA, SPILL_REG1, label);           // load address of label into SPILL_REG1
                section.emit(OpCode.LW, SPILL_REG1, SPILL_REG1, 0);   // load word from that address into SPILL_REG1
                regMap.put(r, SPILL_REG1);
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
                regMap.put(def, SPILL_REG2);
            } else if (coloring.containsKey(def)) {
                regMap.put(def, coloring.get(def));
            } else {
                regMap.put(def, def);
            }
        }

        // rebuild instruction with new registers
        Instruction patched = instr.rebuild(regMap);
        section.emit(patched);

        // store spilled def
        if (def != null && spillLabels.containsKey(def)) {
            Label label = spillLabels.get(def);
            section.emit(OpCode.LA, SPILL_REG1, label);           // load address of label into SPILL_REG1
            section.emit(OpCode.SW, SPILL_REG2, SPILL_REG1, 0);   // store SPILL_REG2’s value at that address
        }
    }

    private void expandPushRegisters(AssemblyProgram.TextSection section, Collection<Register> regs) {
        int offset = 0;
        for (Register r : regs) {
            if (r == SPILL_REG1 || r == SPILL_REG2) continue;
            section.emit(OpCode.SW, r, Register.Arch.sp, offset);
            offset -= 4;
        }
        if (offset != 0) {
            section.emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, offset);
        }
    }

    private void expandPopRegisters(AssemblyProgram.TextSection section, Collection<Register> regs) {
        int offset = -4 * (regs.size() - 2); // adjust for spill registers
        for (Register r : regs) {
            if (r == SPILL_REG1 || r == SPILL_REG2) continue;
            section.emit(OpCode.LW, r, Register.Arch.sp, offset);
            offset += 4;
        }
        if (offset != 0) {
            section.emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -offset);
        }
    }
}