package regalloc;

import gen.asm.*;
import java.util.*;

public class InterferenceGraph {

    private final Map<Register, Set<Register>> adjacency = new HashMap<>();
    private final Map<Register, Integer> usageCount = new HashMap<>();

    public void build(ControlFlowGraph cfg) {
        // collect all virtual registers and count usage
        Set<Register> vregs = new HashSet<>();
        for (Instruction instr : cfg.getBlocks().stream().flatMap(b -> b.instructions.stream()).toList()) {
            for (Register r : instr.uses()) {
                if (r.isVirtual()) {
                    vregs.add(r);
                    usageCount.put(r, usageCount.getOrDefault(r, 0) + 1);
                }
            }
            Register d = instr.def();
            if (d != null && d.isVirtual()) {
                vregs.add(d);
                usageCount.put(d, usageCount.getOrDefault(d, 0) + 1);
            }
        }
        for (Register r : vregs) {
            adjacency.put(r, new HashSet<>());
        }

        // add interference edges using local liveness
        for (ControlFlowGraph.BasicBlock block : cfg.getBlocks()) {
            List<Set<Register>> localLiveness = LivenessAnalysis.computeLocalLiveness(block);
            for (Set<Register> liveSet : localLiveness) {
                List<Register> liveList = new ArrayList<>(liveSet);
                for (int i = 0; i < liveList.size(); i++) {
                    for (int j = i + 1; j < liveList.size(); j++) {
                        Register r1 = liveList.get(i);
                        Register r2 = liveList.get(j);
                        if (!r1.equals(r2)) {
                            adjacency.get(r1).add(r2);
                            adjacency.get(r2).add(r1);
                        }
                    }
                }
            }
            // add edges between defined registers and live-out registers
            int idx = 0;
            for (Instruction instr : block.instructions) {
                Register d = instr.def();
                if (d != null && d.isVirtual()) {
                    Set<Register> liveAfter = localLiveness.get(idx);
                    for (Register r : liveAfter) {
                        if (!r.equals(d)) {
                            adjacency.get(d).add(r);
                            adjacency.get(r).add(d);
                        }
                    }
                }
                idx++;
            }
        }
    }

    public Set<Register> getNeighbors(Register r) {
        return adjacency.getOrDefault(r, Collections.emptySet());
    }

    public int getDegree(Register r) {
        return adjacency.getOrDefault(r, Collections.emptySet()).size();
    }

    public int getUsage(Register r) {
        return usageCount.getOrDefault(r, 0);
    }

    public Set<Register> getAllRegisters() {
        return adjacency.keySet();
    }
}