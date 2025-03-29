package regalloc;

import gen.asm.Register;
import java.util.*;

public final class InterferenceGraph {

    private final Map<Register.Virtual, Set<Register.Virtual>> adj = new HashMap<>();
    private final Set<Register.Virtual> all = new HashSet<>();

    // track usage for each VR
    private final Map<Register.Virtual, Integer> usage = new HashMap<>();

    public void build(CFGBuilder.CFG cfg) {
        // Gather registers and usage counts from basic blocks.
        for (var block : cfg.blocks) {
            for (var u : block.use) {
                usage.put(u, usage.getOrDefault(u, 0) + 1);
            }
            all.addAll(block.in);
            all.addAll(block.out);
            all.addAll(block.use);
            all.addAll(block.def);
        }
        for (var v : all) {
            adj.putIfAbsent(v, new HashSet<>());
            usage.putIfAbsent(v, 0);
        }
        // Add interference edges: for each block, each register defined interferes with every register live out.
        for (var block : cfg.blocks) {
            for (var r : block.def) {
                for (var s : block.out) {
                    if (!r.equals(s)) {
                        adj.get(r).add(s);
                        adj.get(s).add(r);
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
