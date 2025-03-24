package regalloc;

import gen.asm.*;
import java.util.*;

public class GraphColouringRegAlloc implements AssemblyPass {

    public static final GraphColouringRegAlloc INSTANCE = new GraphColouringRegAlloc();

    @Override
    public AssemblyProgram apply(AssemblyProgram program) {
        AssemblyProgram np = new AssemblyProgram();
        // copy data section
        for (var d : program.dataSection.items) np.dataSection.emit(d);
        // process each text section
        for (var sec : program.textSections) {
            var newSec = rewriteUntilNoSpill(sec, np);
            np.emitTextSection(newSec);
        }
        return np;
    }

    // iteratively rewrite until no spill remains
    private AssemblyProgram.TextSection rewriteUntilNoSpill(AssemblyProgram.TextSection orig, AssemblyProgram newProg) {
        List<AssemblyItem> work = new ArrayList<>(orig.items);
        while (true) {
            var sec = new AssemblyProgram.TextSection();
            for (var item : work) {
                if (item instanceof Instruction ins) {
                    sec.emit(ins);
                } else if (item instanceof AssemblyTextItem ati) {
                    sec.emit(ati);
                }
            }
            var cfg = CFGBuilder.build(sec);
            LivenessAnalysis.run(cfg);
            var ig = new InterferenceGraph();
            ig.build(cfg);
            var alloc = new ChaitinAllocator();
            var res = alloc.color(ig);
            var map = res.map();
            var spills = res.spilled();
            if (spills.isEmpty()) {
                return finalRewrite(sec, map, newProg);
            } else {
                // pick one vr to rewrite
                Register.Virtual s = spills.iterator().next();
                work = rewriteOneVR(work, s);
            }
        }
    }

    // inserts load/store around spilled vr
    private List<AssemblyItem> rewriteOneVR(List<AssemblyItem> items, Register.Virtual s) {
        Label spillLbl = Label.create("spill_" + s.toString());
        List<AssemblyItem> out = new ArrayList<>();
        boolean declared = false;
        for (var it : items) {
            if (it instanceof Label || it instanceof Directive || it instanceof Comment) {
                out.add(it);
            } else if (it instanceof Instruction ins) {
                if (ins == Instruction.Nullary.pushRegisters || ins == Instruction.Nullary.popRegisters) {
                    out.add(ins);
                    continue;
                }
                // declare label once
                if (!declared) {
                    out.add(new Comment("alloc for " + s));
                    out.add(new Directive("align 2"));
                    out.add(spillLbl);
                    out.add(new Directive("space 4"));
                    declared = true;
                }
                boolean usesS = ins.uses().contains(s);
                boolean defsS = (ins.def() != null && ins.def().equals(s));
                if (!usesS && !defsS) {
                    out.add(ins);
                } else {
                    List<Instruction> pre = new ArrayList<>();
                    List<Instruction> post = new ArrayList<>();
                    Map<Register,Register> vrMap = new HashMap<>(0);
                    // load if used
                    if (usesS) {
                        Register.Virtual tmp = Register.Virtual.create();
                        pre.add(new Instruction.LoadAddress(tmp, spillLbl));
                        pre.add(new Instruction.Load(OpCode.LW, tmp, tmp, 0));
                        vrMap = Collections.singletonMap(s, tmp);
                    }
                    Instruction newI = ins.rebuild(vrMap);
                    // store if defined
                    if (defsS) {
                        Register.Virtual dv;
                        if (vrMap.containsKey(s)) {
                            dv = (Register.Virtual) vrMap.get(s);
                        } else {
                            dv = Register.Virtual.create();
                            newI = newI.rebuild(Collections.singletonMap(s, dv));
                        }
                        // separate vr for address
                        Register.Virtual laReg = Register.Virtual.create();
                        Instruction.LoadAddress laInst = new Instruction.LoadAddress(laReg, spillLbl);
                        post.add(laInst);
                        // store dv into laReg
                        post.add(new Instruction.Store(OpCode.SW, dv, laReg, 0));
                    }
                    for (var p : pre) out.add(p);
                    out.add(newI);
                    for (var p : post) out.add(p);
                }
            }
        }
        return out;
    }

    // applies final vr->arch rewriting
    private AssemblyProgram.TextSection finalRewrite(AssemblyProgram.TextSection sec,
                                                     Map<Register.Virtual,Register> map,
                                                     AssemblyProgram prog) {
        Map<Register.Virtual,Label> spills = new HashMap<>();
        AssemblyProgram.TextSection ns = new AssemblyProgram.TextSection();
        // sort vrs for consistent push/pop
        List<Register.Virtual> vrs = new ArrayList<>(map.keySet());
        vrs.sort(Comparator.comparing(Register.Virtual::toString));
        // gather all virtual registers from the section
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
        // create spill labels for every VR that is unmapped
        for (var v : allVRs) {
            if (map.get(v) == null) {
                Label l = Label.create("spill_final_" + v);
                spills.put(v, l);
                prog.dataSection.emit("spill for " + v);
                prog.dataSection.emit(new Directive("align 2"));
                prog.dataSection.emit(l);
                prog.dataSection.emit(new Directive("space 4"));
            }
        }
        // rewrite instructions
        for (var it : sec.items) {
            switch(it) {
                case Label l -> ns.emit(l);
                case Directive d -> ns.emit(d);
                case Comment c -> ns.emit(c);
                case Instruction ins -> {
                    // handle pushRegisters
                    if (ins == Instruction.Nullary.pushRegisters) {
                        ns.emit("orig pushRegisters");
                        for (var v : vrs) {
                            if (spills.containsKey(v)) {
                                ns.emit(OpCode.LA, Register.Arch.t0, spills.get(v));
                                ns.emit(OpCode.LW, Register.Arch.t0, Register.Arch.t0, 0);
                                ns.emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -4);
                                ns.emit(OpCode.SW, Register.Arch.t0, Register.Arch.sp, 0);
                            } else {
                                ns.emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, -4);
                                ns.emit(OpCode.SW, map.get(v), Register.Arch.sp, 0);
                            }
                        }
                    }
                    // handle popRegisters
                    else if (ins == Instruction.Nullary.popRegisters) {
                        ns.emit("orig popRegisters");
                        List<Register.Virtual> rev = new ArrayList<>(vrs);
                        Collections.reverse(rev);
                        for (var v : rev) {
                            ns.emit(OpCode.LW, Register.Arch.t0, Register.Arch.sp, 0);
                            ns.emit(OpCode.ADDIU, Register.Arch.sp, Register.Arch.sp, 4);
                            if (spills.containsKey(v)) {
                                ns.emit(OpCode.LA, Register.Arch.t1, spills.get(v));
                                ns.emit(OpCode.SW, Register.Arch.t0, Register.Arch.t1, 0);
                            } else {
                                Register realReg = map.get(v);
                                ns.emit(OpCode.ADDU, realReg, Register.Arch.t0, Register.Arch.zero);
                            }
                        }
                    } else {
                        rewriteFinal(ns, ins, map, spills);
                    }
                }
            }
        }
        return ns;
    }

    private void rewriteFinal(AssemblyProgram.TextSection ns, Instruction ins,
                              Map<Register.Virtual,Register> map,
                              Map<Register.Virtual,Label> spills) {
        Map<Register,Register> vm = new HashMap<>();
        // load spilled uses
        for (var u : ins.uses()) {
            if (u instanceof Register.Virtual vv) {
                if (spills.containsKey(vv)) {
                    ns.emit(OpCode.LA, Register.Arch.t0, spills.get(vv));
                    ns.emit(OpCode.LW, Register.Arch.t0, Register.Arch.t0, 0);
                    vm.put(vv, Register.Arch.t0);
                } else {
                    vm.put(vv, map.get(vv));
                }
            }
        }
        Instruction tmp = ins.rebuild(vm);
        // store if def spilled
        var d = ins.def();
        if (d instanceof Register.Virtual dv && spills.containsKey(dv)) {
            Register dd = Register.Arch.t1;
            tmp = tmp.rebuild(Collections.singletonMap(dv, dd));
            ns.emit("orig " + ins);
            ns.emit(tmp);
            ns.emit(OpCode.LA, Register.Arch.t0, spills.get(dv));
            ns.emit(OpCode.SW, dd, Register.Arch.t0, 0);
        } else if (d instanceof Register.Virtual dv2) {
            Register rd = map.get(dv2);
            if (rd == null) { // mapping is null, so treat as spilled
                Register tempDef = Register.Arch.t1;
                tmp = tmp.rebuild(Collections.singletonMap(dv2, tempDef));
                ns.emit("orig " + ins);
                ns.emit(tmp);
                // load the spill address and store the result into it
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