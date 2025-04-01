package regalloc;

import gen.asm.Register;
import java.util.*;

public final class LivenessAnalysis {

    public static void run(CFGBuilder.CFG cfg) {
        // instead of removing dead definitions, we now keep them
        // and add the defined register to the liveOut set at the end
        boolean changed = true;
        // compute a fixed point
        while (changed) {
            changed = false;
            // process nodes in reverse order
            for (int i = cfg.nodes.size() - 1; i >= 0; i--) {
                var n = cfg.nodes.get(i);
                var oldIn = new HashSet<>(n.in);
                var oldOut = new HashSet<>(n.out);
                n.out.clear();
                // union of successors' in sets
                for (var s : n.succ) {
                    n.out.addAll(s.in);
                }
                // remove definition from out
                var tmp = new HashSet<>(n.out);
                if (n.def != null) {
                    tmp.remove(n.def);
                }
                // in = uses ∪ (out − def)
                n.in = new HashSet<>(n.uses);
                n.in.addAll(tmp);
                if (!n.in.equals(oldIn) || !n.out.equals(oldOut)) {
                    changed = true;
                }
            }
        }
        // for instructions that define a register that is never used,
        // add that register to the liveOut set (if not already present), just once
        for (var n : cfg.nodes) {
            if (n.def != null && !n.out.contains(n.def)) {
                n.out.add(n.def);
            }
        }
    }

}
