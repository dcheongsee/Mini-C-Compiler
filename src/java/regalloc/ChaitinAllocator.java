package regalloc;

import gen.asm.Register;
import java.util.*;

public final class ChaitinAllocator {

    // available physical registers
    private static final List<Register> MIPS_REGS = Arrays.asList(
            Register.Arch.t0, Register.Arch.t1, Register.Arch.t2, Register.Arch.t3,
            Register.Arch.t4, Register.Arch.t5, Register.Arch.t6, Register.Arch.t7,
            Register.Arch.t8, Register.Arch.t9, Register.Arch.s0, Register.Arch.s1,
            Register.Arch.s2, Register.Arch.s3, Register.Arch.s4, Register.Arch.s5,
            Register.Arch.s6, Register.Arch.s7
    );

    public record Result(Map<Register.Virtual,Register> map, Set<Register.Virtual> spilled) {}

    public Result color(InterferenceGraph ig) {
        // stack for removed vertices
        Deque<Register.Virtual> stack = new ArrayDeque<>();
        // set of vrs not in graph anymore
        Set<Register.Virtual> removed = new HashSet<>();
        // set of forced spills
        Set<Register.Virtual> spill = new HashSet<>();
        int k = MIPS_REGS.size();

        // simplify or spill
        while (true) {
            boolean found = true;
            while (found) {
                found = false;
                // look for node with degree < k
                for (var v : ig.vertices()) {
                    if (removed.contains(v)) continue;
                    if (ig.degree(v, removed) < k) {
                        stack.push(v);
                        removed.add(v);
                        found = true;
                        break;
                    }
                }
            }
            // remaining unremoved nodes
            var remain = new ArrayList<Register.Virtual>();
            for (var v : ig.vertices()) {
                if (!removed.contains(v)) remain.add(v);
            }
            if (remain.isEmpty()) break;
            // pick highest degree for spilling
            Register.Virtual best = null;
            int bestDeg = -1;
            for (var v : remain) {
                int deg = ig.degree(v, removed);
                if (deg > bestDeg) {
                    bestDeg = deg;
                    best = v;
                }
            }
            // push and mark spilled
            stack.push(best);
            removed.add(best);
            spill.add(best);
        }

        // assign registers on pop
        Map<Register.Virtual,Register> assign = new HashMap<>();
        while (!stack.isEmpty()) {
            var v = stack.pop();
            if (spill.contains(v)) {
                assign.put(v, null);
            } else {
                // gather used colors
                Set<Register> used = new HashSet<>();
                for (var w : ig.neighbors(v)) {
                    if (assign.containsKey(w) && assign.get(w) != null) {
                        used.add(assign.get(w));
                    }
                }
                // pick any free color
                Register choice = null;
                for (var r : MIPS_REGS) {
                    if (!used.contains(r)) {
                        choice = r;
                        break;
                    }
                }
                if (choice == null) {
                    assign.put(v, null);
                    spill.add(v);
                } else {
                    assign.put(v, choice);
                }
            }
        }
        // returns final map + spills
        return new Result(assign, spill);
    }
}