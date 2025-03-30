package regalloc;

import gen.asm.*;
import java.util.*;

public class LivenessAnalysis {

    public static void run(ControlFlowGraph cfg) {
        // compute use and def sets for each block
        for (ControlFlowGraph.BasicBlock block : cfg.getBlocks()) {
            for (Instruction instr : block.instructions) {
                for (Register r : instr.uses()) {
                    if (!block.def.contains(r) && r.isVirtual()) {
                        block.use.add(r);
                    }
                }
                Register d = instr.def();
                if (d != null && d.isVirtual()) {
                    block.def.add(d);
                }
            }
        }

        // worklist algorithm for block-level liveness analysis
        Queue<ControlFlowGraph.BasicBlock> worklist = new LinkedList<>(cfg.getBlocks());
        while (!worklist.isEmpty()) {
            ControlFlowGraph.BasicBlock block = worklist.poll();
            Set<Register> oldLiveIn = new HashSet<>(block.liveIn);
            Set<Register> oldLiveOut = new HashSet<>(block.liveOut);

            block.liveOut.clear();
            for (ControlFlowGraph.BasicBlock succ : block.successors) {
                block.liveOut.addAll(succ.liveIn);
            }
            Set<Register> newLiveIn = new HashSet<>(block.use);
            Set<Register> diff = new HashSet<>(block.liveOut);
            diff.removeAll(block.def);
            newLiveIn.addAll(diff);
            block.liveIn = newLiveIn;

            if (!block.liveIn.equals(oldLiveIn) || !block.liveOut.equals(oldLiveOut)) {
                worklist.addAll(block.predecessors);
            }
        }
    }

    // compute local (per-instruction) liveness for a given basic block
    public static List<Set<Register>> computeLocalLiveness(ControlFlowGraph.BasicBlock block) {
        List<Set<Register>> liveAfter = new ArrayList<>();
        int n = block.instructions.size();
        for (int i = 0; i < n; i++) {
            liveAfter.add(new HashSet<>());
        }
        Set<Register> live = new HashSet<>(block.liveOut);
        for (int i = n - 1; i >= 0; i--) {
            Instruction instr = block.instructions.get(i);
            liveAfter.set(i, new HashSet<>(live));
            Register d = instr.def();
            if (d != null && d.isVirtual()) {
                live.remove(d);
            }
            for (Register r : instr.uses()) {
                if (r.isVirtual()) {
                    live.add(r);
                }
            }
        }
        return liveAfter;
    }
}