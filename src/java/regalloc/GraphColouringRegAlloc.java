package regalloc;

import gen.asm.*;
import java.util.*;

public class GraphColouringRegAlloc implements AssemblyPass {

    public static final GraphColouringRegAlloc INSTANCE = new GraphColouringRegAlloc();

    // map for tracking spilled virtual registers and their associated spill labels
    private final Map<Register.Virtual, Label> spillLabels = new HashMap<>();

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
                    List<AssemblyItem> patched = patchAll(attempt.section.items, attempt.map, attempt.spilled);

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

        // single color pass using all registers
        var alloc = new ChaitinAllocator(false); // (the boolean is irrelevant now)
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
                    Register tmp = Register.Virtual.create();
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

    // patchAll method to handle spilled registers by rewriting instructions accordingly
    private List<AssemblyItem> patchAll(List<AssemblyItem> items, Map<Register.Virtual, Register> coloring, Set<Register.Virtual> spilled) {
        List<AssemblyItem> out = new ArrayList<>();
        for (var it : items) {
            if (it instanceof Instruction instr) {
                boolean hasSpilled = false;
                for (Register r : instr.registers()) {
                    if (r.isVirtual()) {
                        Register.Virtual vr = (Register.Virtual) r;
                        if (spilled.contains(vr)) {
                            hasSpilled = true;
                            break;
                        }
                    }
                }
                if (hasSpilled) {
                    out.addAll(rewriteInstr(instr, coloring, spilled));
                } else {
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
                }
            } else {
                out.add(it);
            }
        }
        return out;
    }

    // helper to rewrite instructions that involve spilled registers
    private List<AssemblyItem> rewriteInstr(Instruction ins, Map<Register.Virtual, Register> coloring, Set<Register.Virtual> spilled) {
        List<AssemblyItem> out = new ArrayList<>();
        Map<Register, Register> vm = new HashMap<>();
        Register.Virtual defSpilled = null;
        if (ins.def() instanceof Register.Virtual dv && spilled.contains(dv)) {
            defSpilled = dv;
        }
        Set<Register.Virtual> spilledUses = new HashSet<>();
        for (Register r : ins.uses()) {
            if (r instanceof Register.Virtual vv && spilled.contains(vv)) {
                if (defSpilled == null || !vv.equals(defSpilled)) {
                    spilledUses.add(vv);
                }
            }
        }
        Register[] tempRegs = new Register[]{ Register.Arch.t0, Register.Arch.t2, Register.Arch.t3, Register.Arch.t4, Register.Arch.t5, Register.Arch.t6, Register.Arch.t7, Register.Arch.t8, Register.Arch.t9 };
        int tempIndex = 0;
        for (Register.Virtual vv : spilledUses) {
            Register temp;
            if (tempIndex < tempRegs.length) {
                temp = tempRegs[tempIndex++];
            } else {
                temp = Register.Virtual.create();
            }
            Label spillLabel = spillLabels.get(vv);
            if (spillLabel == null) {
                spillLabel = Label.create("spill_" + vv);
                spillLabels.put(vv, spillLabel);
            }
            out.add(new Instruction.LoadAddress(temp, spillLabel));
            out.add(new Instruction.Load(OpCode.LW, temp, temp, 0));
            vm.put(vv, temp);
        }
        for (Register r : ins.uses()) {
            if (r instanceof Register.Virtual vv && !spilled.contains(vv)) {
                vm.put(vv, coloring.get(vv));
            }
        }
        Instruction rebuilt = ins.rebuild(vm);
        if (defSpilled != null) {
            Register tempDef = Register.Arch.t1;
            rebuilt = rebuilt.rebuild(Collections.singletonMap(defSpilled, tempDef));
            out.add(new Comment("orig " + ins));
            out.add(rebuilt);
            Label spillLabel = spillLabels.get(defSpilled);
            if (spillLabel == null) {
                spillLabel = Label.create("spill_" + defSpilled);
                spillLabels.put(defSpilled, spillLabel);
            }
            out.add(new Instruction.LoadAddress(Register.Arch.t0, spillLabel));
            out.add(new Instruction.Store(OpCode.SW, tempDef, Register.Arch.t0, 0));
        } else if (ins.def() instanceof Register.Virtual dv) {
            Register rd = coloring.get(dv);
            if (rd == null) {
                Register tempDef = Register.Arch.t1;
                rebuilt = rebuilt.rebuild(Collections.singletonMap(dv, tempDef));
                out.add(new Comment("orig " + ins));
                out.add(rebuilt);
                Label spillLabel = spillLabels.get(dv);
                if (spillLabel == null) {
                    spillLabel = Label.create("spill_" + dv);
                    spillLabels.put(dv, spillLabel);
                }
                out.add(new Instruction.LoadAddress(Register.Arch.t0, spillLabel));
                out.add(new Instruction.Store(OpCode.SW, tempDef, Register.Arch.t0, 0));
            } else {
                rebuilt = rebuilt.rebuild(Collections.singletonMap(dv, rd));
                out.add(new Comment("orig " + ins));
                out.add(rebuilt);
            }
        } else {
            out.add(new Comment("orig " + ins));
            out.add(rebuilt);
        }
        return out;
    }

    private List<AssemblyItem> expandPushPop(List<AssemblyItem> items) {
        List<AssemblyItem> newItems = new ArrayList<>();

        // we'll treat each push/pop pair as a scope tracking which registers get used
        // between them. Nested scopes are allowed and each pop will expand the matching push
        Deque<Scope> scopeStack = new ArrayDeque<>();

        for (AssemblyItem it : items) {
            if (!(it instanceof Instruction instr)) {
                newItems.add(it);
                continue;
            }

            // check if it's a push or pop instruction
            if (instr instanceof Instruction.Nullary nullaryInstr) {
                if (nullaryInstr == Instruction.Nullary.pushRegisters) {
                    int placeholderIndex = newItems.size();
                    newItems.add(new Comment("<< PUSH PLACEHOLDER >>"));
                    scopeStack.push(new Scope(placeholderIndex));
                    continue;
                } else if (nullaryInstr == Instruction.Nullary.popRegisters) {
                    if (scopeStack.isEmpty()) {
                        throw new RuntimeException("popRegisters with no matching pushRegisters!");
                    }
                    Scope scope = scopeStack.pop();

                    // determine which registers were used in this scope (both physical and spilled)
                    List<Register> regsToSave = new ArrayList<>(scope.used);
                    regsToSave.sort(Comparator.comparing(Register::toString));

                    // expand the push placeholder
                    if (!regsToSave.isEmpty()) {
                        newItems.set(scope.placeholderIndex,
                                new Instruction.ArithmeticWithImmediate(
                                        OpCode.ADDI, Register.Arch.sp, Register.Arch.sp, -4 * regsToSave.size()
                                )
                        );
                        int insertPos = scope.placeholderIndex + 1;
                        for (int i = 0; i < regsToSave.size(); i++) {
                            Register r = regsToSave.get(i);
                            if (r.isVirtual() && spillLabels.containsKey((Register.Virtual) r)) {
                                Label lbl = spillLabels.get((Register.Virtual) r);
                                Register tmp = Register.Virtual.create();
                                newItems.add(insertPos + i, new Comment("push spilled " + r));
                                newItems.add(insertPos + i + 1, new Instruction.LoadAddress(tmp, lbl));
                                newItems.add(insertPos + i + 2, new Instruction.Load(OpCode.LW, tmp, tmp, 0));
                                newItems.add(insertPos + i + 3, new Instruction.Store(OpCode.SW, tmp, Register.Arch.sp, 4 * i));
                                i += 3;
                            } else {
                                newItems.add(insertPos + i,
                                        new Instruction.Store(OpCode.SW, r, Register.Arch.sp, 4 * i)
                                );
                            }
                        }
                    } else {
                        newItems.set(scope.placeholderIndex,
                                new Comment("pushRegisters: no registers used in this scope")
                        );
                    }

                    // now expand the pop
                    if (!regsToSave.isEmpty()) {
                        for (int i = 0; i < regsToSave.size(); i++) {
                            Register r = regsToSave.get(regsToSave.size() - 1 - i);
                            if (r.isVirtual() && spillLabels.containsKey((Register.Virtual) r)) {
                                Label lbl = spillLabels.get((Register.Virtual) r);
                                Register tmp = Register.Virtual.create();
                                newItems.add(new Instruction.Load(OpCode.LW, tmp, Register.Arch.sp, 4 * i));
                                Register addr = Register.Virtual.create();
                                newItems.add(new Instruction.LoadAddress(addr, lbl));
                                newItems.add(new Instruction.Store(OpCode.SW, tmp, addr, 0));
                            } else {
                                newItems.add(new Instruction.Load(OpCode.LW, r, Register.Arch.sp, 4 * i));
                            }
                        }
                        newItems.add(new Instruction.ArithmeticWithImmediate(
                                OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, 4 * regsToSave.size()
                        ));
                    } else {
                        newItems.add(new Comment("popRegisters: no registers used in this scope"));
                    }
                    continue;
                }
            }

            newItems.add(it);
            if (!scopeStack.isEmpty()) {
                Scope currentScope = scopeStack.peek();
                for (Register r : instr.registers()) {
                    if ((!r.isVirtual() && ALLOWED_REGS.contains(r)) ||
                            (r.isVirtual() && spillLabels.containsKey((Register.Virtual) r))) {
                        currentScope.used.add(r);
                    }
                }
            }
        }

        if (!scopeStack.isEmpty()) {
            throw new RuntimeException("pushRegisters without a matching popRegisters!");
        }
        return newItems;
    }

    private static final class Scope {
        final int placeholderIndex;
        final Set<Register> used = new HashSet<>();

        Scope(int placeholderIndex) {
            this.placeholderIndex = placeholderIndex;
        }
    }
}
