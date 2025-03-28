package regalloc;

import gen.asm.Register;
import java.util.*;

public final class InterferenceGraph {

    private final Map<Register.Virtual, Set<Register.Virtual>> adj = new HashMap<>();
    private final Set<Register.Virtual> all = new HashSet<>();

    // track usage for each VR
    private final Map<Register.Virtual, Integer> usage = new HashMap<>();


    public void build(CFGBuilder.CFG cfg) {
        // gather all vrs, also build usage counts
        for (var n : cfg.nodes) {
            // for each VR used by this node, increment usage
            for (var u : n.uses) {
                usage.put(u, usage.getOrDefault(u, 0) + 1);
            }
            // also add 'def' to the set of all VRs
            if (n.def != null) {

            }

            // add them to the global set
            all.addAll(n.in);
            all.addAll(n.out);
            if (n.def != null) {
                all.add(n.def);
            }
            all.addAll(n.uses);
        }

        // prepare adjacency sets for each VR
        for (var v : all) {
            adj.putIfAbsent(v, new HashSet<>());
            usage.putIfAbsent(v, 0); // ensure every VR has an entry in usage, even if 0
        }

        // add edges for live sets
        for (var n : cfg.nodes) {
            var live = new HashSet<>(n.in);
            live.addAll(n.out);
            if (n.def != null) live.add(n.def);
            live.addAll(n.uses);

            // every pair in 'live' interferes
            var list = new ArrayList<>(live);
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
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