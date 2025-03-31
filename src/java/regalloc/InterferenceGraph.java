package regalloc;

import gen.asm.Register;
import java.util.*;

public final class InterferenceGraph {
    // map each virtual register to the set of virtual registers it interferes with
    private final Map<Register.Virtual, Set<Register.Virtual>> adj = new HashMap<>();
    // for all virtual registers encountered
    private final Set<Register.Virtual> all = new HashSet<>();
    // usage counts for spill heuristics
    private final Map<Register.Virtual, Integer> usage = new HashMap<>();

    public void build(CFGBuilder.CFG cfg) {
        // gather all virtual registers and usage counts
        for (var n : cfg.nodes) {
            for (var u : n.uses) {
                usage.put(u, usage.getOrDefault(u, 0) + 1);
            }
            if (n.def != null) {
                all.add(n.def);
            }
            all.addAll(n.in);
            all.addAll(n.out);
            all.addAll(n.uses);
        }
        for (var v : all) {
            adj.putIfAbsent(v, new HashSet<>());
            usage.putIfAbsent(v, 0);
        }
        // for each node, add interference edges among all registers simultaneously live
        for (var n : cfg.nodes) {
            var liveList = new ArrayList<>(n.out);
            for (int i = 0; i < liveList.size(); i++) {
                for (int j = i + 1; j < liveList.size(); j++) {
                    var a = liveList.get(i);
                    var b = liveList.get(j);
                    adj.get(a).add(b);
                    adj.get(b).add(a);
                }
            }
        }
    }

    public int degree(Register.Virtual v, Set<Register.Virtual> removed) {
        int d = 0;
        for (var x : adj.getOrDefault(v, Set.of())) {
            if (!removed.contains(x)) d++;
        }
        return d;
    }

    public Set<Register.Virtual> neighbors(Register.Virtual v) {
        return adj.getOrDefault(v, Set.of());
    }

    public Set<Register.Virtual> vertices() {
        return all;
    }

    public int usage(Register.Virtual v) {
        return usage.getOrDefault(v, 0);
    }
}
