package regalloc;

import gen.asm.Register;
import java.util.*;

public class ChaitinAllocator {

    private static final List<Register> PHYSICAL_REGS = Arrays.asList(
            Register.Arch.t0, Register.Arch.t1, Register.Arch.t2, Register.Arch.t3,
            Register.Arch.t4, Register.Arch.t5, Register.Arch.t6, Register.Arch.t7,
            Register.Arch.s0, Register.Arch.s1, Register.Arch.s2, Register.Arch.s3,
            Register.Arch.s4, Register.Arch.s5, Register.Arch.s6, Register.Arch.s7
    );

    public record Result(Map<Register, Register> map, Set<Register> spilled) {}

    public Result color(InterferenceGraph ig) {
        int K = PHYSICAL_REGS.size();
        Deque<Register> stack = new ArrayDeque<>();
        Set<Register> removed = new HashSet<>();
        Set<Register> spill = new HashSet<>();

        while (true) {
            boolean found = true;
            while (found) {
                found = false;
                for (Register v : ig.getAllRegisters()) {
                    long effectiveDegree = ig.getDegree(v) - removed.stream().filter(ig.getNeighbors(v)::contains).count();
                    if (!removed.contains(v) && effectiveDegree < K) {
                        stack.push(v);
                        removed.add(v);
                        found = true;
                        break;
                    }
                }
            }
            List<Register> remaining = new ArrayList<>();
            for (Register v : ig.getAllRegisters()) {
                if (!removed.contains(v)) {
                    remaining.add(v);
                }
            }
            if (remaining.isEmpty()) {
                break;
            }
            // select a register to spill using usage/degree heuristic
            Register toSpill = remaining.stream()
                    .min((a, b) -> {
                        double ratioA = (double) ig.getUsage(a) / ig.getDegree(a);
                        double ratioB = (double) ig.getUsage(b) / ig.getDegree(b);
                        return Double.compare(ratioA, ratioB);
                    })
                    .orElse(remaining.get(0));
            stack.push(toSpill);
            removed.add(toSpill);
            spill.add(toSpill);
        }

        Map<Register, Register> assign = new HashMap<>();
        while (!stack.isEmpty()) {
            Register v = stack.pop();
            if (spill.contains(v)) {
                assign.put(v, null); // Spilled
            } else {
                Set<Register> used = new HashSet<>();
                for (Register w : ig.getNeighbors(v)) {
                    if (assign.containsKey(w) && assign.get(w) != null) {
                        used.add(assign.get(w));
                    }
                }
                Register choice = PHYSICAL_REGS.stream()
                        .filter(r -> !used.contains(r))
                        .findFirst()
                        .orElse(null);
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