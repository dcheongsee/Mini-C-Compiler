package regalloc;

import gen.asm.Register;

import java.util.*;

public final class InterferenceGraph {

    private final Map<Register.Virtual,Set<Register.Virtual>> adj = new HashMap<>();
    private final Set<Register.Virtual> all = new HashSet<>();

    public void build(CFGBuilder.CFG cfg) {
        // gather all vrs
        for (var n : cfg.nodes) {
            all.addAll(n.in);
            all.addAll(n.out);
            if (n.def != null) all.add(n.def);
            all.addAll(n.uses);
        }
        // prepare adjacency sets
        for (var v : all) {
            adj.putIfAbsent(v, new HashSet<>());
        }
        // add edges for live sets
        for (var n : cfg.nodes) {
            var live = new HashSet<>(n.in);
            live.addAll(n.out);
            if (n.def != null) live.add(n.def);
            var list = new ArrayList<>(live);
            // every pair in live interferes
            for (int i = 0; i < list.size(); i++) {
                for (int j = i+1; j < list.size(); j++) {
                    var a = list.get(i);
                    var b = list.get(j);
                    if (!a.equals(b)) {
                        adj.get(a).add(b);
                        adj.get(b).add(a);
                    }
                }
            }
        }
    }

    // measures degree ignoring removed vrs
    public int degree(Register.Virtual v, Set<Register.Virtual> removed) {
        int d = 0;
        for (var x : adj.getOrDefault(v,Set.of())) {
            if (!removed.contains(x)) d++;
        }
        return d;
    }

    // neighbors of v
    public Set<Register.Virtual> neighbors(Register.Virtual v) {
        return adj.getOrDefault(v, Set.of());
    }

    // all vrs in graph
    public Set<Register.Virtual> vertices() {
        return all;
    }
}