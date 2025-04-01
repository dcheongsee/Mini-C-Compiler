package regalloc;

import gen.asm.Register;
import java.util.*;

public final class LivenessAnalysis {

    public static void run(CFGBuilder.CFG cfg) {
        // remove dead definitions: if a node defines a virtual register never used anywhere
        removeDeadDefs(cfg);
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
    }

    private static void removeDeadDefs(CFGBuilder.CFG cfg) {
        Map<Register.Virtual, Integer> usage = new HashMap<>();
        for (var n : cfg.nodes) {
            for (var u : n.uses) {
                usage.put(u, usage.getOrDefault(u, 0) + 1);
            }
        }
        Set<CFGBuilder.CFGNode> dead = new HashSet<>();
        for (var n : cfg.nodes) {
            if (n.def != null && usage.getOrDefault(n.def, 0) == 0) {
                dead.add(n);
            }
        }
        for (var d : dead) {
            for (var p : d.pred) {
                p.succ.remove(d);
            }
            for (var s : d.succ) {
                s.pred.remove(d);
            }
        }
        cfg.nodes.removeAll(dead);
    }
}
