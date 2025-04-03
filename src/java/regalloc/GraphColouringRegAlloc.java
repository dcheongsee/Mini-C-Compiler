package regalloc;

import gen.asm.*;
import java.util.*;

public class GraphColouringRegAlloc implements AssemblyPass {

    public static final GraphColouringRegAlloc INSTANCE = new GraphColouringRegAlloc();

    // map for tracking spilled virtual registers and their associated spill labels
    private final Map<Register.Virtual, Label> spillLabels = new HashMap<>();

    // added: set to track special virtual registers (used for spilling)
    private final Set<Register.Virtual> specialTemps = new HashSet<>();

    // allowed physical registers for push/pop expansions
    private static final List<Register> ALLOWED_REGS = List.of(
            Register.Arch.t0, Register.Arch.t1, Register.Arch.t2, Register.Arch.t3,
            Register.Arch.t4, Register.Arch.t5, Register.Arch.t6, Register.Arch.t7,
            Register.Arch.t8, Register.Arch.t9,
            Register.Arch.s0, Register.Arch.s1, Register.Arch.s2, Register.Arch.s3,
            Register.Arch.s4, Register.Arch.s5, Register.Arch.s6, Register.Arch.s7
    );

    @Override
    public AssemblyProgram apply(AssemblyProgram program) {
        AssemblyProgram newProg = new AssemblyProgram();
        newProg.dataSection.items.addAll(program.dataSection.items);

        for (var sec : program.textSections) {
            // we'll work in-memory on the instructions until no spills remain
            List<AssemblyItem> work = new ArrayList<>(sec.items);

            while (true) {
                // attempt a color pass
                ColorAttempt attempt = buildAndColor(work);

                if (!attempt.spilled.isEmpty()) {
                    // if there are spills, pick one spilled VR to rewrite
                    Register.Virtual s = attempt.spilled.iterator().next();
                    work = rewriteOneSpilledVR(work, s);
                    // then re-run the loop, building a new CFG and coloring again
                } else {
                    // no spills => we can finalize by patching all VR to physical
                    List<AssemblyItem> patched = patchAll(attempt.section.items, attempt.map);

                    // expand push/pop pseudo-instructions
                    patched = expandPushPop(patched);

                    // Create final text section
                    AssemblyProgram.TextSection newSec = newProg.emitNewTextSection();
                    for (var ai : patched) {
                        if (ai instanceof Instruction instr) {
                            newSec.emit(instr);
                        } else if (ai instanceof AssemblyTextItem ati) {
                            newSec.emit(ati);
                        } else {
                            throw new RuntimeException("Unexpected assembly item: " + ai.getClass());
                        }
                    }
                    break;
                }
            }
        }

        // emit labels for spilled VR memory slots
        for (var entry : spillLabels.entrySet()) {
            Register.Virtual vr = entry.getKey();
            Label lbl = entry.getValue();
            newProg.dataSection.emit(new Comment("Spill slot for " + vr));
            newProg.dataSection.emit(new Directive("align 2"));
            newProg.dataSection.emit(lbl);
            newProg.dataSection.emit(new Directive("space 4"));
        }

        return newProg;
    }

    private static class ColorAttempt {
        AssemblyProgram.TextSection section;
        Map<Register.Virtual, Register> map;
        Set<Register.Virtual> spilled;
    }

    private ColorAttempt buildAndColor(List<AssemblyItem> items) {
        // collect items into a temporary text section
        var sec = new AssemblyProgram.TextSection();
        for (var it : items) {
            if (it instanceof Instruction ins) {
                sec.emit(ins);
            } else if (it instanceof AssemblyTextItem ati) {
                sec.emit(ati);
            }
        }

        // build CFG, run liveness
        var cfg = CFGBuilder.build(sec);
        LivenessAnalysis.run(cfg);

        // build interference graph, color
        var ig = new InterferenceGraph();
        ig.build(cfg);

        // single color pass using all registers; pass the specialTemps set to avoid spilling them
        var alloc = new ChaitinAllocator(false, specialTemps);
        var res = alloc.color(ig);

        ColorAttempt out = new ColorAttempt();
        out.section = sec;
        out.map = res.map();
        out.spilled = res.spilled();
        return out;
    }

    private List<AssemblyItem> rewriteOneSpilledVR(List<AssemblyItem> items, Register.Virtual s) {
        Label spillLbl = spillLabels.computeIfAbsent(s, v -> Label.create("spill_" + v));
        List<AssemblyItem> newItems = new ArrayList<>();

        for (var it : items) {
            if (it instanceof Instruction instr) {
                List<Register> used = instr.uses();
                Register d = instr.def();
                boolean usesS = used.contains(s);
                boolean defsS = (d != null && d.equals(s));

                if (!usesS && !defsS) {
                    // no reference to s => no rewrite needed
                    newItems.add(it);
                } else {
                    // we rewrite references to s entirely in VR
                    Register.Virtual tmp = Register.Virtual.create();
                    specialTemps.add(tmp); // mark tmp as special
                    Map<Register, Register> regMap = new HashMap<>();

                    // if the instruction uses s, load it into a fresh VR 'tmp'
                    if (usesS) {
                        newItems.add(new Comment("load spilled " + s + " into " + tmp));
                        newItems.addAll(buildLoadOps(tmp, spillLbl));
                        for (Register ur : used) {
                            if (ur.equals(s)) {
                                regMap.put(ur, tmp);
                            }
                        }
                    }
                    // if the instruction defines s, we rename the def to 'tmp'
                    if (defsS) {
                        regMap.put(s, tmp);
                    }

                    // rebuild the original instruction with 'tmp' in place of s
                    Instruction rebuilt = instr.rebuild(regMap);
                    newItems.add(rebuilt);

                    // if the instruction defines s, store 'tmp' back to memory
                    if (defsS) {
                        newItems.add(new Comment("store spilled " + s + " from " + tmp));
                        newItems.addAll(buildStoreOps(tmp, spillLbl));
                    }
                }
            } else {
                newItems.add(it);
            }
        }
        return newItems;
    }

    private List<AssemblyItem> buildLoadOps(Register.Virtual tmp, Label lbl) {
        List<AssemblyItem> ops = new ArrayList<>();
        Register.Virtual addr = Register.Virtual.create();
        specialTemps.add(addr); // mark addr as special
        ops.add(new Instruction.LoadAddress(addr, lbl));
        ops.add(new Instruction.Load(OpCode.LW, tmp, addr, 0));
        return ops;
    }

    private List<AssemblyItem> buildStoreOps(Register.Virtual tmp, Label lbl) {
        List<AssemblyItem> ops = new ArrayList<>();
        Register.Virtual addr = Register.Virtual.create();
        specialTemps.add(addr); // mark addr as special
        ops.add(new Instruction.LoadAddress(addr, lbl));
        ops.add(new Instruction.Store(OpCode.SW, tmp, addr, 0));
        return ops;
    }

    private List<AssemblyItem> patchAll(List<AssemblyItem> items, Map<Register.Virtual, Register> coloring) {
        List<AssemblyItem> out = new ArrayList<>();
        for (var it : items) {
            if (it instanceof Instruction instr) {
                Map<Register, Register> regMap = new HashMap<>();
                for (Register r : instr.registers()) {
                    if (r.isVirtual()) {
                        Register.Virtual vr = (Register.Virtual) r;
                        if (coloring.containsKey(vr) && coloring.get(vr) != null) {
                            regMap.put(r, coloring.get(vr));
                        }
                    }
                }
                Instruction patched = instr.rebuild(regMap);
                out.add(patched);
            } else {
                out.add(it);
            }
        }
        return out;
    }

    private List<AssemblyItem> expandPushPop(List<AssemblyItem> items) {
        List<AssemblyItem> newItems = new ArrayList<>();
        Set<Register> usedPhysRegs = new HashSet<>();
        for (var it : items) {
            if (it instanceof Instruction instr) {
                for (Register r : instr.registers()) {
                    if (!r.isVirtual() && ALLOWED_REGS.contains(r)) {
                        usedPhysRegs.add(r);
                    }
                }
            }
        }
        List<Register> regsToPush = new ArrayList<>();
        for (Register r : ALLOWED_REGS) {
            if (usedPhysRegs.contains(r)) {
                regsToPush.add(r);
            }
        }
        int n = regsToPush.size();
        for (var it : items) {
            if (it instanceof Instruction instr) {
                if (instr instanceof Instruction.Nullary nullaryInstr) {
                    if (nullaryInstr == Instruction.Nullary.pushRegisters) {
                        List<AssemblyItem> expansion = new ArrayList<>();
                        if (n > 0) {
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
                        List<AssemblyItem> expansion = new ArrayList<>();
                        if (n > 0) {
                            List<Register> rev = new ArrayList<>(regsToPush);
                            Collections.reverse(rev);
                            int offset = 0;
                            for (Register r : rev) {
                                expansion.add(new Instruction.Load(
                                        OpCode.LW, r, Register.Arch.sp, offset));
                                offset += 4;
                            }
                            expansion.add(new Instruction.ArithmeticWithImmediate(
                                    OpCode.ADDI, Register.Arch.sp, Register.Arch.sp, n * 4));
                        }
                        newItems.addAll(expansion);
                        continue;
                    }
                }
            }
            newItems.add(it);
        }
        return newItems;
    }
}
