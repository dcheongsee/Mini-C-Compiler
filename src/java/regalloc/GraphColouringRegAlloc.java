package regalloc;

import gen.asm.*;
import java.util.*;

public class GraphColouringRegAlloc implements AssemblyPass {

    public static final GraphColouringRegAlloc INSTANCE = new GraphColouringRegAlloc();

    private final Map<Register, Label> spillLabels = new HashMap<>();

    @Override
    public AssemblyProgram apply(AssemblyProgram program) {
        AssemblyProgram np = new AssemblyProgram();
        // copy data section
        for (var d : program.dataSection.items) {
            np.dataSection.emit(d);
        }
        // process each text section
        for (var sec : program.textSections) {
            var newSec = rewriteUntilNoSpill(sec.items, np);
            np.emitTextSection(newSec);
        }
        // emit spill labels in data section
        for (var entry : spillLabels.entrySet()) {
            Register v = entry.getKey();
            Label lbl = entry.getValue();
            np.dataSection.emit(new Comment("Spill slot for " + v));
            np.dataSection.emit(new Directive("align 2"));
            np.dataSection.emit(lbl);
            np.dataSection.emit(new Directive("space 4"));
        }
        return np;
    }

    private AssemblyProgram.TextSection rewriteUntilNoSpill(List<AssemblyItem> items, AssemblyProgram newProg) {
        List<AssemblyItem> work = new ArrayList<>(items);
        while (true) {
            var attempt = buildAndColor(work);
            if (attempt.spilled.isEmpty()) {
                return finalRewrite(attempt.section, attempt.map, newProg);
            }
            // rewrite one spilled variable at a time
            Register toSpill = pickOneSpill(attempt.spilled);
            work = rewriteOneVR(work, toSpill);
        }
    }

    private static class ColorAttempt {
        AssemblyProgram.TextSection section;
        Map<Register, Register> map;
        Set<Register> spilled;
    }

    private ColorAttempt buildAndColor(List<AssemblyItem> items) {
        var sec = new AssemblyProgram.TextSection();
        for (var it : items) {
            if (it instanceof Instruction ins) {
                sec.emit(ins);
            } else if (it instanceof AssemblyTextItem ati) {
                sec.emit(ati);
            }
        }
        var cfg = new ControlFlowGraph(sec.items);
        LivenessAnalysis.run(cfg);
        var ig = new InterferenceGraph();
        ig.build(cfg);
        var alloc = new ChaitinAllocator();
        var res = alloc.color(ig);
        ColorAttempt out = new ColorAttempt();
        out.section = sec;
        out.map = res.map();
        out.spilled = res.spilled();
        return out;
    }

    private Register pickOneSpill(Set<Register> spilled) {
        return spilled.stream().min(Comparator.comparing(Register::toString)).orElse(null);
    }

    private List<AssemblyItem> rewriteOneVR(List<AssemblyItem> items, Register s) {
        Label spillLbl = spillLabels.computeIfAbsent(s, v -> Label.create("spill_" + v));
        List<AssemblyItem> out = new ArrayList<>();
        for (var it : items) {
            if (it instanceof Label || it instanceof Directive || it instanceof Comment) {
                out.add(it);
            } else if (it instanceof Instruction ins) {
                if (ins == Instruction.Nullary.pushRegisters || ins == Instruction.Nullary.popRegisters) {
                    out.add(ins);
                    continue;
                }
                boolean usesS = ins.uses().contains(s);
                boolean defsS = (ins.def() != null && ins.def().equals(s));
                if (!usesS && !defsS) {
                    out.add(ins);
                } else {
                    List<Instruction> pre = new ArrayList<>();
                    List<Instruction> post = new ArrayList<>();
                    Map<Register, Register> vrMap = new HashMap<>();
                    if (usesS) {
                        Register.Virtual tmp = Register.Virtual.create();
                        pre.add(new Instruction.LoadAddress(tmp, spillLbl));
                        pre.add(new Instruction.Load(OpCode.LW, tmp, tmp, 0));
                        vrMap.put(s, tmp);
                    }
                    Instruction newI = ins.rebuild(vrMap);
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

    private AssemblyProgram.TextSection finalRewrite(AssemblyProgram.TextSection sec, Map<Register, Register> map, AssemblyProgram newProg) {
        var newSec = new AssemblyProgram.TextSection();
        for (var item : sec.items) {
            if (item instanceof Instruction ins) {
                Map<Register, Register> vrMap = new HashMap<>();
                for (Register r : ins.uses()) {
                    if (r.isVirtual() && map.containsKey(r) && map.get(r) != null) {
                        vrMap.put(r, map.get(r));
                    }
                }
                Register d = ins.def();
                if (d != null && d.isVirtual() && map.containsKey(d) && map.get(d) != null) {
                    vrMap.put(d, map.get(d));
                }
                Instruction newIns = ins.rebuild(vrMap);
                newSec.emit(newIns);
            } else if (item instanceof AssemblyTextItem ati) {
                newSec.emit(ati);
            }
        }
        return newSec;
    }
}