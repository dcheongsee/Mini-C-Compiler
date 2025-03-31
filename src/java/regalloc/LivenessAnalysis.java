package regalloc;

import gen.asm.Register;
import java.util.*;

public final class LivenessAnalysis {

    public static void run(CFGBuilder.CFG cfg) {
        // we repeatedly remove dead definitions and rerun the liveness fixpoint
        // so that if removing one instruction causes another to be dead, we catch it
        boolean done = false;
        while (!done) {
            // remove nodes defining unused VR
            boolean removed = removeDeadDefs(cfg);
            // then compute a fixpoint for in/out sets
            boolean changed = livenessFixpoint(cfg);
            // if we neither removed nor updated liveness, we are done
            done = !removed && !changed;
        }
    }

    // standard while not stable pass to compute in/out sets
    private static boolean livenessFixpoint(CFGBuilder.CFG cfg) {
        boolean changedOverall = false;
        boolean stable = false;
        while (!stable) {
            stable = true;
            for (int i = cfg.nodes.size() - 1; i >= 0; i--) {
                var n = cfg.nodes.get(i);
                // snapshot old sets
                var oldIn = new HashSet<>(n.in);
                var oldOut = new HashSet<>(n.out);

                n.out.clear();
                // union of successors' in
                for (var s : n.succ) {
                    n.out.addAll(s.in);
                }
                // remove def from out
                var tmp = new HashSet<>(n.out);
                if (n.def != null) {
                    tmp.remove(n.def);
                }
                // in = uses plus out minus def
                n.in = new HashSet<>(n.uses);
                n.in.addAll(tmp);

                // detect changes
                if (!n.in.equals(oldIn) || !n.out.equals(oldOut)) {
                    stable = false;
                    changedOverall = true;
                }
            }
        }
        return changedOverall;
    }

    private static boolean removeDeadDefs(CFGBuilder.CFG cfg) {
        // count overall usage
        Map<Register.Virtual, Integer> usage = new HashMap<>();
        for (var n : cfg.nodes) {
            for (var u : n.uses) {
                usage.put(u, usage.getOrDefault(u, 0) + 1);
            }
        }
        // find nodes with def never used
        Set<CFGBuilder.CFGNode> dead = new HashSet<>();
        for (var n : cfg.nodes) {
            if (n.def != null && usage.getOrDefault(n.def, 0) == 0) {
                dead.add(n);
            }
        }
        if (dead.isEmpty()) {
            // nothing to remove
            return false;
        }
        // remove them fully
        for (var d : dead) {
            for (var p : d.pred) {
                p.succ.remove(d);
            }
            for (var s : d.succ) {
                s.pred.remove(d);
            }
        }
        cfg.nodes.removeAll(dead);
        return true;
    }
}
