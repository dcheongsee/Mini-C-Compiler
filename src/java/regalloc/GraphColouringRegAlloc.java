package regalloc;

import gen.asm.*;
import java.util.*;

public class GraphColouringRegAlloc implements AssemblyPass {

    public static final GraphColouringRegAlloc INSTANCE = new GraphColouringRegAlloc();


    private final Map<Register.Virtual, Label> spillLabels = new HashMap<>();

    @Override
    public AssemblyProgram apply(AssemblyProgram program) {
        AssemblyProgram np = new AssemblyProgram();
        // copy data section
        for (var d : program.dataSection.items) {
            np.dataSection.emit(d);
        }
        // process each text section
        for (var sec : program.textSections) {
            var newSec = rewriteUntilNoSpill(sec, np);
            np.emitTextSection(newSec);
        }
        // now emit all spill labels in the data section
        for (var entry : spillLabels.entrySet()) {
            Register.Virtual v = entry.getKey();
            Label lbl = entry.getValue();
            np.dataSection.emit(new Comment("Spill slot for " + v));
            np.dataSection.emit(new Directive("align 2"));
            np.dataSection.emit(lbl);
            np.dataSection.emit(new Directive("space 4"));
        }
        return np;
    }


    private AssemblyProgram.TextSection rewriteUntilNoSpill(AssemblyProgram.TextSection orig, AssemblyProgram newProg) {
        List<AssemblyItem> work = new ArrayList<>(orig.items);

        while (true) {
            // try an allocation with NO registers reserved
            var attempt = buildAndColor(work, /*reserveTwo=*/false);
            if (attempt.spilled.isEmpty()) {
                // success => finalize
                return finalRewrite(attempt.section, attempt.map, newProg);
            }

            // if that fails, try again with 2 regs reserved
            var attempt2 = buildAndColor(work, /*reserveTwo=*/true);
            if (attempt2.spilled.isEmpty()) {
                // success => finalize
                return finalRewrite(attempt2.section, attempt2.map, newProg);
            }

            // if we STILL get spills, pick one VR to rewrite
            // from the second attempt's spilled set
            var s = attempt2.spilled.iterator().next();
            work = rewriteOneVR(work, s);
            // then loop again from step (1)
        }
    }


    private static class ColorAttempt {
        AssemblyProgram.TextSection section;
        Map<Register.Virtual,Register> map;
        Set<Register.Virtual> spilled;
    }

    private ColorAttempt buildAndColor(List<AssemblyItem> items, boolean reserveTwo) {
        // build a temporary TextSection
        var sec = new AssemblyProgram.TextSection();
        for (var it : items) {
            if (it instanceof Instruction ins) {
                sec.emit(ins);
            } else if (it instanceof AssemblyTextItem ati) {
                sec.emit(ati);
            }
        }
        // CFG + Liveness + IG
        var cfg = CFGBuilder.build(sec);
        LivenessAnalysis.run(cfg);
        var ig = new InterferenceGraph();
        ig.build(cfg);
        // allocate
        var alloc = new ChaitinAllocator(reserveTwo);
        var res = alloc.color(ig);
        // return
        ColorAttempt out = new ColorAttempt();
        out.section = sec;
        out.map = res.map();
        out.spilled = res.spilled();
        return out;
    }


    private List<AssemblyItem> rewriteOneVR(List<AssemblyItem> items, Register.Virtual s) {
        Label spillLbl = spillLabels.computeIfAbsent(s, v -> Label.create("spill_" + v));

        List<AssemblyItem> out = new ArrayList<>();
        for (var it : items) {
            if (it instanceof Label || it instanceof Directive || it instanceof Comment) {
                out.add(it);
            } else if (it instanceof Instruction ins) {
                // skip push/pop expansions
                if (ins == Instruction.Nullary.pushRegisters || ins == Instruction.Nullary.popRegisters) {
                    out.add(ins);
                    continue;
                }
                boolean usesS = ins.uses().contains(s);
                boolean defsS = (ins.def() != null && ins.def().equals(s));
                if (!usesS && !defsS) {
                    out.add(ins);
                } else {
                    // insert load if used
                    List<Instruction> pre = new ArrayList<>();
                    List<Instruction> post = new ArrayList<>();
                    Map<Register,Register> vrMap = new HashMap<>();
                    if (usesS) {
                        Register.Virtual tmp = Register.Virtual.create();
                        pre.add(new Instruction.LoadAddress(tmp, spillLbl));
                        pre.add(new Instruction.Load(OpCode.LW, tmp, tmp, 0));
                        vrMap.put(s, tmp);
                    }
                    Instruction newI = ins.rebuild(vrMap);
                    // insert store if defined
                    if (defsS) {
                        Register.Virtual dv = (Register.Virtual) vrMap.get(s);
                        if (dv == null) {
                            dv = Register.Virtual.create();
                            newI = newI.rebuild(Collections.singletonMap(s, dv));
                        }
                        Register.Virtual laReg = Register.Virtual.create();
                        post.add(new Instruction.LoadAddress(laReg, spillLbl));
                        post.add(new Instruction.Store(OpCode.SW, dv, laReg, 0));
                    }
                    out.addAll(pre);
                    out.add(newI);
                    out.addAll(post);
                }
            }
        }
        return out;
    }


    private AssemblyProgram.TextSection finalRewrite(AssemblyProgram.TextSection sec,
                                                     Map<Register.Virtual,Register> map,
                                                     AssemblyProgram prog) {
        Map<Register.Virtual,Label> localSpills = new HashMap<>();
        AssemblyProgram.TextSection ns = new AssemblyProgram.TextSection();

        // gather all VR used
        Set<Register.Virtual> allVRs = new HashSet<>();
        for (var item : sec.items) {
            if (item instanceof Instruction ins) {
                for (var r : ins.registers()) {
                    if (r instanceof Register.Virtual v) {
                        allVRs.add(v);
                    }
                }
            }
        }
        // check which VRs are mapped to null => spilled
        for (var v : allVRs) {
            if (map.get(v) == null) {
                localSpills.put(v, spillLabels.computeIfAbsent(v, vv -> Label.create("spill_final_" + vv)));
            }
        }

        for (var it : sec.items) {
            switch(it) {
                case Label l -> ns.emit(l);
                case Directive d -> ns.emit(d);
                case Comment c -> ns.emit(c);
                case Instruction ins -> {
                    if (ins == Instruction.Nullary.pushRegisters) {
                        ns.emit("orig pushRegisters");
                        rewritePush(ns, map, localSpills);
                    }
                    else if (ins == Instruction.Nullary.popRegisters) {
                        ns.emit("orig popRegisters");
                        rewritePop(ns, map, localSpills);
                    }
                    else {
                        rewriteInstr(ns, ins, map, localSpills);
                    }
                }
            }
        }
        return ns;
    }

    private void rewritePush(AssemblyProgram.TextSection ns,
                             Map<Register.Virtual,Register> map,
                             Map<Register.Virtual,Label> spills) {
        // gather VR in sorted order
        List<Register.Virtual> vrs = new ArrayList<>(map.keySet());
        vrs.sort(Comparator.comparing(Register.Virtual::toString));
        for (var v : vrs) {
            // Only push if we actually got an architectural register (not spilled).
            if (!spills.containsKey(v)) {
                ns.emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -4);
                ns.emit(OpCode.SW, map.get(v), Register.Arch.sp, 0);
            }
        }
    }

    private void rewritePop(AssemblyProgram.TextSection ns,
                            Map<Register.Virtual,Register> map,
                            Map<Register.Virtual,Label> spills) {
        List<Register.Virtual> vrs = new ArrayList<>(map.keySet());
        vrs.sort(Comparator.comparing(Register.Virtual::toString));
        Collections.reverse(vrs);
        for (var v : vrs) {
            // only pop if we actually got an architectural register (not spilled)
            if (!spills.containsKey(v)) {
                ns.emit(OpCode.LW, map.get(v), Register.Arch.sp, 0);
                ns.emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, 4);
            }
        }
    }

    private void rewriteInstr(AssemblyProgram.TextSection ns, Instruction ins,
                              Map<Register.Virtual,Register> map,
                              Map<Register.Virtual,Label> spills) {
        Map<Register,Register> vm = new HashMap<>();
        // check if def is spilled
        Register.Virtual defSpilled = null;
        if (ins.def() instanceof Register.Virtual dv && spills.containsKey(dv)) {
            defSpilled = dv;
        }

        // compute spilled registers used that are not also defined
        Set<Register.Virtual> spilledUses = new HashSet<>();
        for (var u : ins.uses()) {
            if (u instanceof Register.Virtual vv && spills.containsKey(vv) && (defSpilled == null || !vv.equals(defSpilled))) {
                spilledUses.add(vv);
            }
        }

        // for each spilled register used, load it only once into a unique temporary
        Register[] tempRegs = new Register[]{ Register.Arch.t0, Register.Arch.t2, Register.Arch.t3, Register.Arch.t4, Register.Arch.t5, Register.Arch.t6, Register.Arch.t7, Register.Arch.t8, Register.Arch.t9 };
        int tempIndex = 0;
        for (Register.Virtual vv : spilledUses) {
            Register temp;
            if (tempIndex < tempRegs.length) {
                temp = tempRegs[tempIndex++];
            } else {
                temp = Register.Virtual.create();
            }
            ns.emit(OpCode.LA, temp, spills.get(vv));
            ns.emit(OpCode.LW, temp, temp, 0);
            vm.put(vv, temp);
        }

        // for non-spilled uses
        for (var u : ins.uses()) {
            if (u instanceof Register.Virtual vv && !spills.containsKey(vv)) {
                vm.put(vv, map.get(vv));
            }
        }

        Instruction tmp = ins.rebuild(vm);

        // handle def if spilled.
        if (defSpilled != null) {
            // allocate a new temporary for the def
            Register dd = Register.Arch.t1;
            tmp = tmp.rebuild(Collections.singletonMap(defSpilled, dd));
            ns.emit("orig " + ins);
            ns.emit(tmp);
            ns.emit(OpCode.LA, Register.Arch.t0, spills.get(defSpilled));
            ns.emit(OpCode.SW, dd, Register.Arch.t0, 0);
        } else if (ins.def() instanceof Register.Virtual dv2) {
            Register rd = map.get(dv2);
            if (rd == null) {
                // treat as spilled
                Register tempDef = Register.Arch.t1;
                tmp = tmp.rebuild(Collections.singletonMap(dv2, tempDef));
                ns.emit("orig " + ins);
                ns.emit(tmp);
                ns.emit(OpCode.LA, Register.Arch.t0, spills.get(dv2));
                ns.emit(OpCode.SW, tempDef, Register.Arch.t0, 0);
            } else {
                tmp = tmp.rebuild(Collections.singletonMap(dv2, rd));
                ns.emit("orig " + ins);
                ns.emit(tmp);
            }
        } else {
            ns.emit("orig " + ins);
            ns.emit(tmp);
        }
    }
}