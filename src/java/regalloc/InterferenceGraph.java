package regalloc;

import gen.asm.Instruction;
import gen.asm.Register;
import java.util.*;

public class InterferenceGraph {
    private final Map<Register, Set<Register>> adjacency;
    private final Map<Register, Integer> degrees;
    private final List<Register> allNodes;
    private final Set<Register> spilled;
    private final Map<Register, Register> coloring;
    private final int K;
    private final Map<Register, Integer> usageCount; // For improved spill heuristic

    private static final List<Register> PHYSICAL_REGS = Arrays.asList(
            Register.Arch.t0, Register.Arch.t1, Register.Arch.t2, Register.Arch.t3,
            Register.Arch.t4, Register.Arch.t5, Register.Arch.t6, Register.Arch.t7,
            Register.Arch.s0, Register.Arch.s1, Register.Arch.s2, Register.Arch.s3,
            Register.Arch.s4, Register.Arch.s5, Register.Arch.s6, Register.Arch.s7
    );

    public InterferenceGraph(ControlFlowGraph cfg, int K) {
        this.K = K;
        adjacency = new HashMap<>();
        degrees = new HashMap<>();
        spilled = new HashSet<>();
        coloring = new HashMap<>();
        usageCount = new HashMap<>();

        // collect all virtual registers and count usage
        Set<Register> vregs = new HashSet<>();
        for (Instruction instr : cfg.getInstructions()) {
            for (Register r : instr.uses()) {
                if (r.isVirtual()) {
                    vregs.add(r);
                    usageCount.put(r, usageCount.getOrDefault(r, 0) + 1);
                }
            }
            Register d = instr.def();
            if (d != null && d.isVirtual()) {
                vregs.add(d);
                usageCount.put(d, usageCount.getOrDefault(d, 0) + 1);
            }
        }
        for (Register r : vregs) {
            adjacency.put(r, new HashSet<>());
        }

        // use local liveness from each basic block to add interference edges
        for (ControlFlowGraph.BasicBlock block : cfg.getBlocks()) {
            List<Set<Register>> localLiveness = cfg.computeLocalLiveness(block);
            // for each instruction, add interference among registers in the live set
            for (Set<Register> liveSet : localLiveness) {
                List<Register> liveList = new ArrayList<>(liveSet);
                for (int i = 0; i < liveList.size(); i++) {
                    Register r1 = liveList.get(i);
                    for (int j = i + 1; j < liveList.size(); j++) {
                        Register r2 = liveList.get(j);
                        if (!r1.equals(r2)) {
                            adjacency.get(r1).add(r2);
                            adjacency.get(r2).add(r1);
                        }
                    }
                }
            }
            // also, for each instruction defining a register, add interference with its live-out set
            int idx = 0;
            for (Instruction instr : block.instructions) {
                Register d = instr.def();
                if (d != null && d.isVirtual()) {
                    Set<Register> liveAfter = localLiveness.get(idx);
                    for (Register r : liveAfter) {
                        if (!r.equals(d)) {
                            adjacency.get(d).add(r);
                            adjacency.get(r).add(d);
                        }
                    }
                }
                idx++;
            }
        }

        allNodes = new ArrayList<>(adjacency.keySet());
        for (Register r : allNodes) {
            degrees.put(r, adjacency.get(r).size());
        }
    }

    public InterferenceGraph(ControlFlowGraph cfg) {
        this(cfg, 16);
    }

    public void color() {
        Stack<Register> stack = new Stack<>();
        Map<Register, Integer> currentDeg = new HashMap<>(degrees);

        // Chaitin simplification using improved spill heuristic
        while (!currentDeg.isEmpty()) {
            Optional<Register> candidate = currentDeg.keySet().stream()
                    .filter(r -> currentDeg.get(r) < K)
                    .findFirst();

            if (candidate.isPresent()) {
                Register r = candidate.get();
                stack.push(r);
                for (Register neigh : adjacency.get(r)) {
                    if (currentDeg.containsKey(neigh)) {
                        currentDeg.put(neigh, currentDeg.get(neigh) - 1);
                    }
                }
                currentDeg.remove(r);
            } else {
                // pick a spill candidate using the usage/degree ratio
                Register toSpill = pickSpill(currentDeg);
                spilled.add(toSpill);
                for (Register neigh : adjacency.get(toSpill)) {
                    if (currentDeg.containsKey(neigh)) {
                        currentDeg.put(neigh, currentDeg.get(neigh) - 1);
                    }
                }
                currentDeg.remove(toSpill);
            }
        }

        // assign colors
        while (!stack.isEmpty()) {
            Register r = stack.pop();
            Set<Register> usedColors = new HashSet<>();
            for (Register neighbor : adjacency.get(r)) {
                if (coloring.containsKey(neighbor)) {
                    usedColors.add(coloring.get(neighbor));
                }
            }
            Register chosen = null;
            for (Register pr : PHYSICAL_REGS) {
                if (!usedColors.contains(pr)) {
                    chosen = pr;
                    break;
                }
            }
            if (chosen == null) {
                spilled.add(r);
            } else {
                coloring.put(r, chosen);
            }
        }
    }

    private Register pickSpill(Map<Register, Integer> degMap) {
        double bestRatio = Double.MAX_VALUE;
        Register best = null;
        for (Register r : degMap.keySet()) {
            int deg = degMap.get(r);
            int usage = usageCount.getOrDefault(r, 1);
            double ratio = (double) usage / deg;
            if (ratio < bestRatio) {
                bestRatio = ratio;
                best = r;
            } else if (ratio == bestRatio) {
                if (r.toString().compareTo(best.toString()) < 0) {
                    best = r;
                }
            }
        }
        return best;
    }

    public Map<Register, Register> getColoring() {
        return coloring;
    }

    public Set<Register> getSpilled() {
        return spilled;
    }

    // debugging: outputs the interference graph as a dot graph
    public String toDot() {
        StringBuilder sb = new StringBuilder("graph InterferenceGraph {\n");
        Set<String> visited = new HashSet<>();
        for (Register r : adjacency.keySet()) {
            sb.append("  \"").append(r).append("\";\n");
        }
        for (Register r : adjacency.keySet()) {
            for (Register n : adjacency.get(r)) {
                String edge = r + "--" + n;
                String reverseEdge = n + "--" + r;
                if (!visited.contains(edge) && !visited.contains(reverseEdge)) {
                    sb.append("  \"").append(r).append("\" -- \"").append(n).append("\";\n");
                    visited.add(edge);
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
}