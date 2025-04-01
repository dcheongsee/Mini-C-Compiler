package regalloc;

import gen.asm.*;
import java.util.*;

public class GraphColouringRegAlloc implements AssemblyPass {

    public static final GraphColouringRegAlloc INSTANCE = new GraphColouringRegAlloc();

    // map for tracking spilled virtual registers and their associated spill labels
    private final Map<Register.Virtual, Label> spillLabels = new HashMap<>();

    // allowed physical registers for push/pop
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
            List<AssemblyItem> work = new ArrayList<>(sec.items);
            // multi–pass rewriting loop: allocate until no spills remain and no virtual registers remain
            while (true) {
                ColorAttempt attempt = buildAndColor(work, /*reserveTwo=*/false);
                if (attempt.spilled.isEmpty()) {
                    List<AssemblyItem> patched = patchAll(attempt.section.items, attempt.map);
                    if (containsVirtualRegisters(patched)) {
                        // if some virtual registers remain (i.e. spilled ones that weren't rewritten),
                        // collect them and rewrite them
                        Set<Register.Virtual> stillVirtual = collectVirtualRegisters(patched);
                        for (Register.Virtual vr : stillVirtual) {
                            work = rewriteOneSpilledVR(work, vr);
                        }
                        // continue the loop to reallocate
                        continue;
                    } else {
                        work = patched;
                        break;
                    }
                }
                ColorAttempt attempt2 = buildAndColor(work, /*reserveTwo=*/true);
                if (attempt2.spilled.isEmpty()) {
                    List<AssemblyItem> patched = patchAll(attempt2.section.items, attempt2.map);
                    if (containsVirtualRegisters(patched)) {
                        Set<Register.Virtual> stillVirtual = collectVirtualRegisters(patched);
                        for (Register.Virtual vr : stillVirtual) {
                            work = rewriteOneSpilledVR(work, vr);
                        }
                        continue;
                    } else {
                        work = patched;
                        break;
                    }
                }
                // if spills remain, pick one virtual register to rewrite
                var s = attempt2.spilled.iterator().next();
                work = rewriteOneSpilledVR(work, s);
            }
            work = expandPushPop(work);
            AssemblyProgram.TextSection newSec = newProg.emitNewTextSection();
            for (var ai : work) {
                if (ai instanceof Instruction instr) {
                    newSec.emit(instr);
                } else if (ai instanceof AssemblyTextItem ati) {
                    newSec.emit(ati);
                } else {
                    throw new RuntimeException("Unexpected assembly item: " + ai.getClass());
                }
            }
        }

        for (var entry : spillLabels.entrySet()) {
            Label lbl = entry.getValue();
            newProg.dataSection.emit(new Comment("Spill slot for " + entry.getKey()));
            newProg.dataSection.emit(new Directive("align 2"));
            newProg.dataSection.emit(lbl);
            newProg.dataSection.emit(new Directive("space 4"));
        }

        return newProg;
    }

    // helper structure for a color attempt
    private static class ColorAttempt {
        AssemblyProgram.TextSection section;
        Map<Register.Virtual, Register> map;
        Set<Register.Virtual> spilled;
    }

    private ColorAttempt buildAndColor(List<AssemblyItem> items, boolean reserveTwo) {
        var sec = new AssemblyProgram.TextSection();
        for (var it : items) {
            if (it instanceof Instruction ins) {
                sec.emit(ins);
            } else if (it instanceof AssemblyTextItem ati) {
                sec.emit(ati);
            }
        }
        var cfg = CFGBuilder.build(sec);
        LivenessAnalysis.run(cfg);
        var ig = new InterferenceGraph();
        ig.build(cfg);
        var alloc = new ChaitinAllocator(reserveTwo);
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
                    newItems.add(it);
                } else {
                    Register tmp = Register.Virtual.create();
                    Map<Register, Register> regMap = new HashMap<>();
                    if (usesS) {
                        newItems.add(new Comment("load spilled " + s + " into " + tmp));
                        newItems.addAll(buildLoadOps(tmp, spillLbl));
                        for (Register ur : used) {
                            if (ur.equals(s)) {
                                regMap.put(ur, tmp);
                            }
                        }
                    }
                    if (defsS) {
                        regMap.put(s, tmp);
                    }
                    Instruction rebuilt = instr.rebuild(regMap);
                    newItems.add(rebuilt);
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

    private List<AssemblyItem> buildLoadOps(Register tmp, Label lbl) {
        List<AssemblyItem> ops = new ArrayList<>();
        Register addr = Register.Virtual.create();
        ops.add(new Instruction.LoadAddress(addr, lbl));
        ops.add(new Instruction.Load(OpCode.LW, tmp, addr, 0));
        return ops;
    }

    private List<AssemblyItem> buildStoreOps(Register tmp, Label lbl) {
        List<AssemblyItem> ops = new ArrayList<>();
        Register addr = Register.Virtual.create();
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
                    if (r.isVirtual() && coloring.containsKey(r)) {
                        regMap.put(r, coloring.get(r));
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

    // helper: check if any instruction still contains a virtual register
    private boolean containsVirtualRegisters(List<AssemblyItem> items) {
        for (AssemblyItem it : items) {
            if (it instanceof Instruction instr) {
                for (Register r : instr.registers()) {
                    if (r.isVirtual()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // helper: collect all virtual registers appearing in instructions
    private Set<Register.Virtual> collectVirtualRegisters(List<AssemblyItem> items) {
        Set<Register.Virtual> result = new HashSet<>();
        for (AssemblyItem it : items) {
            if (it instanceof Instruction instr) {
                for (Register r : instr.registers()) {
                    if (r.isVirtual()) {
                        result.add((Register.Virtual) r);
                    }
                }
            }
        }
        return result;
    }
}
