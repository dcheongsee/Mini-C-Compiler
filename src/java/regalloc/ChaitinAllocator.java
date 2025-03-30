package regalloc;

import gen.asm.Register;
import java.util.*;

public final class ChaitinAllocator {

    // full set of allowed physical registers ($t0-$t9 and $s0-$s7)
    private static final List<Register> ALL_MIPS_REGS = Arrays.asList(
            Register.Arch.t0, Register.Arch.t1, Register.Arch.t2, Register.Arch.t3,
            Register.Arch.t4, Register.Arch.t5, Register.Arch.t6, Register.Arch.t7,
            Register.Arch.t8, Register.Arch.t9, Register.Arch.s0, Register.Arch.s1,
            Register.Arch.s2, Register.Arch.s3, Register.Arch.s4, Register.Arch.s5,
            Register.Arch.s6, Register.Arch.s7
    );

    public record Result(Map<Register.Virtual, Register> map, Set<Register.Virtual> spilled) {}

    private final boolean reserveTwo;

    public ChaitinAllocator(boolean reserveTwo) {
        this.reserveTwo = reserveTwo;
    }

    public Result color(InterferenceGraph ig) {
        // k is the number of available colors
        int k = ALL_MIPS_REGS.size();
        if (reserveTwo && k > 2) {
            k -= 2;
        }

        Deque<Register.Virtual> stack = new ArrayDeque<>();
        Set<Register.Virtual> removed = new HashSet<>();
        Set<Register.Virtual> spill = new HashSet<>();

        while (true) {
            boolean foundAny = true;
            while (foundAny) {
                foundAny = false;
                for (var v : ig.vertices()) {
                    if (removed.contains(v)) continue;
                    int deg = ig.degree(v, removed);
                    if (deg < k) {
                        stack.push(v);
                        removed.add(v);
                        foundAny = true;
                        break;
                    }
                }
            }

            List<Register.Virtual> remain = new ArrayList<>();
            for (var v : ig.vertices()) {
                if (!removed.contains(v)) {
                    remain.add(v);
                }
            }
            if (remain.isEmpty()) {
                break;
            }
            // select a victim to spill
            remain.sort((a, b) -> {
                int degA = ig.degree(a, removed);
                int degB = ig.degree(b, removed);
                if (degA != degB) {
                    return Integer.compare(degB, degA);
                }
                int useA = ig.usage(a);
                int useB = ig.usage(b);
                if (useA != useB) {
                    return Integer.compare(useB, useA);
                }
                return a.toString().compareTo(b.toString());
            });
            Register.Virtual victim = remain.get(0);
            stack.push(victim);
            removed.add(victim);
            spill.add(victim);
        }

        Map<Register.Virtual, Register> assign = new HashMap<>();
        while (!stack.isEmpty()) {
            Register.Virtual v = stack.pop();
            if (spill.contains(v)) {
                assign.put(v, null);
            } else {
                Set<Register> used = new HashSet<>();
                for (var w : ig.neighbors(v)) {
                    if (assign.containsKey(w) && assign.get(w) != null) {
                        used.add(assign.get(w));
                    }
                }
                List<Register> colorSet = reserveTwo ? ALL_MIPS_REGS.subList(0, k) : ALL_MIPS_REGS;
                Register choice = null;
                for (Register r : colorSet) {
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
        return new Result(assign, spill);
    }
}
