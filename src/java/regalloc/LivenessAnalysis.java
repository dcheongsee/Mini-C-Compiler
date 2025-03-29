package regalloc;

import gen.asm.Register;

import java.util.*;

public final class LivenessAnalysis {

    public static void run(CFGBuilder.CFG cfg) {
        boolean changed = true;
        // Repeat until in/out sets stabilize over basic blocks.
        while (changed) {
            changed = false;
            // Process blocks in reverse order.
            for (int i = cfg.blocks.size() - 1; i >= 0; i--) {
                var block = cfg.blocks.get(i);
                var oldIn = new HashSet<>(block.in);
                var oldOut = new HashSet<>(block.out);
                block.out.clear();
                // Union of successors' in sets.
                for (var succ : block.succ) {
                    block.out.addAll(succ.in);
                }
                // in = use ∪ (out - def)
                Set<Register.Virtual> newIn = new HashSet<>(block.use);
                Set<Register.Virtual> outMinusDef = new HashSet<>(block.out);
                outMinusDef.removeAll(block.def);
                newIn.addAll(outMinusDef);
                block.in = newIn;
                if (!block.in.equals(oldIn) || !block.out.equals(oldOut)) {
                    changed = true;
                }
            }
        }
    }
}
