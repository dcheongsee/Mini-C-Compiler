package regalloc;

import gen.asm.*;

import java.util.*;

public class GraphColouringRegAlloc implements AssemblyPass {

    public static final GraphColouringRegAlloc INSTANCE = new GraphColouringRegAlloc();

    // track spilled registers mapped to data labels
    private final Map<Register, Label> spilledLabelMap = new HashMap<>();

    // define allowed physical registers for allocation (and for push/pop)
    private static final List<Register> ALLOWED_REGS = List.of(
            Register.Arch.t0, Register.Arch.t1, Register.Arch.t2, Register.Arch.t3,
            Register.Arch.t4, Register.Arch.t5, Register.Arch.t6, Register.Arch.t7,
            Register.Arch.s0, Register.Arch.s1, Register.Arch.s2, Register.Arch.s3,
            Register.Arch.s4, Register.Arch.s5, Register.Arch.s6, Register.Arch.s7
    );

    @Override
    public AssemblyProgram apply(AssemblyProgram program) {
        // create final program
        AssemblyProgram newProg = new AssemblyProgram();

        // copy original data items
        newProg.dataSection.items.addAll(program.dataSection.items);

        // process each function
        for (AssemblyProgram.TextSection section : program.textSections) {
            // multi-pass rewriting in memory, then produce final text items
            List<AssemblyItem> currentItems = new ArrayList<>(section.items);

            boolean changed = true;
            while (changed) {
                changed = false;
                // build CFG, liveness is computed in the constructor
                ControlFlowGraph cfg = new ControlFlowGraph(currentItems);

                // build interference graph using the CFG (which now provides instruction-level liveness)
                InterferenceGraph ig = new InterferenceGraph(cfg, 16);
                ig.color();

                Set<Register> spills = ig.getSpilled();
                if (!spills.isEmpty()) {
                    // pick one register to rewrite
                    Register toSpill = pickOneSpill(spills);
                    currentItems = rewriteOneSpilledVR(currentItems, toSpill);
                    changed = true;
                } else {
                    // patch final
                    Map<Register, Register> coloring = ig.getColoring();
                    currentItems = patchAll(currentItems, coloring);
                    break;
                }
            }

            // expand push/pop pseudo instructions in the currentItems
            currentItems = expandPushPop(currentItems);

            // create final text section
            AssemblyProgram.TextSection newSection = newProg.emitNewTextSection();
            for (AssemblyItem ai : currentItems) {
                if (ai instanceof Instruction instr) {
                    newSection.emit(instr);
                } else if (ai instanceof AssemblyTextItem ati) {
                    newSection.emit(ati);
                } else {
                    throw new RuntimeException("unexpected item type: " + ai.getClass());
                }
            }
        }

        // emit each spill label in data (to avoid .word in text)
        for (Label lbl : spilledLabelMap.values()) {
            newProg.dataSection.emit(lbl);
            newProg.dataSection.emit(new Directive("word 0"));
        }

        return newProg;
    }

    // pick a single spilled register (stable tie-break)
    private Register pickOneSpill(Set<Register> spilled) {
        Register best = null;
        for (Register r : spilled) {
            if (best == null) {
                best = r;
            } else {
                if (r.toString().compareTo(best.toString()) < 0) {
                    best = r;
                }
            }
        }
        return best;
    }

    // rewrite references to toSpill with loads/stores from a unique label
    private List<AssemblyItem> rewriteOneSpilledVR(List<AssemblyItem> items, Register toSpill) {
        Label spillLabel = spilledLabelMap.computeIfAbsent(toSpill, r -> Label.create("spill_" + r));
        List<AssemblyItem> newItems = new ArrayList<>();

        for (AssemblyItem ai : items) {
            if (ai instanceof Instruction instr) {
                List<Register> used = instr.uses();
                Register d = instr.def();
                boolean uses = used.contains(toSpill);
                boolean defs = (d != null && d.equals(toSpill));

                if (!uses && !defs) {
                    newItems.add(ai);
                } else {
                    Register tmp = Register.Virtual.create();
                    Map<Register, Register> regMap = new HashMap<>();
                    if (uses) {
                        newItems.add(new Comment("load spilled " + toSpill + " into " + tmp));
                        newItems.addAll(buildLoadOps(tmp, spillLabel));
                        for (Register ur : used) {
                            if (ur.equals(toSpill)) {
                                regMap.put(ur, tmp);
                            }
                        }
                    }
                    if (defs) {
                        regMap.put(toSpill, tmp);
                    }
                    Instruction rebuilt = instr.rebuild(regMap);
                    newItems.add(rebuilt);

                    if (defs) {
                        newItems.add(new Comment("store spilled " + toSpill + " from " + tmp));
                        newItems.addAll(buildStoreOps(tmp, spillLabel));
                    }
                }
            } else {
                newItems.add(ai);
            }
        }
        return newItems;
    }

    // build a load sequence: la reg, spillLabel; lw reg, 0(reg)
    private List<AssemblyItem> buildLoadOps(Register dst, Label lbl) {
        List<AssemblyItem> ops = new ArrayList<>();
        Register addr = Register.Virtual.create();
        ops.add(new Instruction.LoadAddress(addr, lbl));
        ops.add(new Instruction.Load(OpCode.LW, dst, addr, 0));
        return ops;
    }

    // build a store sequence: la reg, spillLabel; sw src, 0(reg)
    private List<AssemblyItem> buildStoreOps(Register src, Label lbl) {
        List<AssemblyItem> ops = new ArrayList<>();
        Register addr = Register.Virtual.create();
        ops.add(new Instruction.LoadAddress(addr, lbl));
        ops.add(new Instruction.Store(OpCode.SW, src, addr, 0));
        return ops;
    }

    private List<AssemblyItem> patchAll(List<AssemblyItem> items, Map<Register, Register> coloring) {
        List<AssemblyItem> out = new ArrayList<>();
        for (AssemblyItem ai : items) {
            if (ai instanceof Instruction instr) {
                Map<Register, Register> regMap = new HashMap<>();
                for (Register r : instr.registers()) {
                    if (r.isVirtual() && coloring.containsKey(r)) {
                        regMap.put(r, coloring.get(r));
                    }
                }
                Instruction patched = instr.rebuild(regMap);
                out.add(patched);
            } else {
                out.add(ai);
            }
        }
        return out;
    }


    private List<AssemblyItem> expandPushPop(List<AssemblyItem> items) {
        List<AssemblyItem> newItems = new ArrayList<>();
        // first, compute set of physical registers (from ALLOWED_REGS) used in the function
        Set<Register> usedPhysRegs = new HashSet<>();
        for (AssemblyItem ai : items) {
            if (ai instanceof Instruction instr) {
                for (Register r : instr.registers()) {
                    if (!r.isVirtual() && ALLOWED_REGS.contains(r)) {
                        usedPhysRegs.add(r);
                    }
                }
            }
        }
        // determine registers to push in a fixed order (as defined in ALLOWED_REGS)
        List<Register> regsToPush = new ArrayList<>();
        for (Register r : ALLOWED_REGS) {
            if (usedPhysRegs.contains(r)) {
                regsToPush.add(r);
            }
        }
        int n = regsToPush.size(); // number of registers to push/pop

        for (AssemblyItem ai : items) {
            if (ai instanceof Instruction instr) {
                // check if this is a push/pop pseudo instruction
                if (instr instanceof Instruction.Nullary nullaryInstr) {
                    if (nullaryInstr == Instruction.Nullary.pushRegisters) {
                        // expand pushRegisters
                        List<AssemblyItem> expansion = new ArrayList<>();
                        if (n > 0) {
                            // adjust stack pointer: addi $sp, $sp, -n*4
                            expansion.add(new Instruction.ArithmeticWithImmediate(
                                    OpCode.ADDI, Register.Arch.sp, Register.Arch.sp, -n * 4));
                            int offset = 0;
                            for (Register r : regsToPush) {
                                expansion.add(new Instruction.Store(
                                        OpCode.SW, r, Register.Arch.sp, offset));
                                offset += 4;
                            }
                        }
                        newItems.addAll(expansion);
                        continue;
                    } else if (nullaryInstr == Instruction.Nullary.popRegisters) {
                        // expand popRegisters
                        List<AssemblyItem> expansion = new ArrayList<>();
                        if (n > 0) {
                            // pop registers in reverse order
                            List<Register> reverseList = new ArrayList<>(regsToPush);
                            Collections.reverse(reverseList);
                            int offset = 0;
                            for (Register r : reverseList) {
                                expansion.add(new Instruction.Load(
                                        OpCode.LW, r, Register.Arch.sp, offset));
                                offset += 4;
                            }
                            // adjust stack pointer: addi $sp, $sp, n*4
                            expansion.add(new Instruction.ArithmeticWithImmediate(
                                    OpCode.ADDI, Register.Arch.sp, Register.Arch.sp, n * 4));
                        }
                        newItems.addAll(expansion);
                        continue;
                    }
                }
            }
            // for all other items, keep them unchanged
            newItems.add(ai);
        }
        return newItems;
    }
}