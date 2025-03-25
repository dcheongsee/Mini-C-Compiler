package regalloc;

import gen.asm.Instruction;
import gen.asm.Register;

import java.util.*;

public class InterferenceGraph {
    private Map<Register, Set<Register>> adjList;
    private Map<Register, Register> coloring;
    private Set<Register> spilled;
    private final int K; // number of available registers for coloring
    private static final List<Register> PHYSICAL_REGISTERS = Arrays.asList(
            Register.Arch.t0, Register.Arch.t1, Register.Arch.t2, Register.Arch.t3,
            Register.Arch.t4, Register.Arch.t5, Register.Arch.t6, Register.Arch.t7,
            Register.Arch.s0, Register.Arch.s1, Register.Arch.s2, Register.Arch.s3,
            Register.Arch.s4, Register.Arch.s5, Register.Arch.s6, Register.Arch.s7
    );

    // default, use 16 registers
    public InterferenceGraph(ControlFlowGraph cfg) {
        this(cfg, 16);
    }

    // constructor with parameterized K e.g. 14 if reserving spill registers
    public InterferenceGraph(ControlFlowGraph cfg, int K) {
        this.K = K;
        adjList = new HashMap<>();
        coloring = new HashMap<>();
        spilled = new HashSet<>();

        // collect all virtual registers
        Set<Register> registers = new HashSet<>();
        for (Instruction instr : cfg.getNodes()) {
            registers.addAll(instr.uses());
            if (instr.def() != null) registers.add(instr.def());
        }
        for (Register r : registers) {
            if (r.isVirtual()) adjList.put(r, new HashSet<>());
        }

        // build interference edges
        for (Instruction instr : cfg.getNodes()) {
            Set<Register> live = new HashSet<>(cfg.getLiveOut(instr));
            for (Register r1 : live) {
                if (!r1.isVirtual()) continue;
                for (Register r2 : live) {
                    if (!r2.isVirtual() || r1 == r2) continue;
                    adjList.get(r1).add(r2);
                    adjList.get(r2).add(r1);
                }
            }
        }
    }

    public void color() {
        Stack<Register> stack = new Stack<>();
        Map<Register, Integer> degrees = new HashMap<>();

        // initialize degrees
        for (Register r : adjList.keySet()) {
            degrees.put(r, adjList.get(r).size());
        }

        // simplify, remove nodes with degree < K
        while (!degrees.isEmpty()) {
            Optional<Map.Entry<Register, Integer>> toRemove = degrees.entrySet().stream()
                    .filter(e -> e.getValue() < K).findFirst();

            if (toRemove.isPresent()) {
                Register r = toRemove.get().getKey();
                stack.push(r);
                for (Register neighbor : adjList.get(r)) {
                    if (degrees.containsKey(neighbor)) {
                        degrees.put(neighbor, degrees.get(neighbor) - 1);
                    }
                }
                degrees.remove(r);
            } else {
                // spill, remove node with highest degree
                Register r = degrees.entrySet().stream()
                        .max(Comparator.comparingInt(Map.Entry::getValue))
                        .get().getKey();
                spilled.add(r);
                for (Register neighbor : adjList.get(r)) {
                    if (degrees.containsKey(neighbor)) {
                        degrees.put(neighbor, degrees.get(neighbor) - 1);
                    }
                }
                degrees.remove(r);
            }
        }

        // select, assign colors
        while (!stack.isEmpty()) {
            Register r = stack.pop();
            Set<Register> usedColors = new HashSet<>();
            for (Register neighbor : adjList.get(r)) {
                if (coloring.containsKey(neighbor)) {
                    usedColors.add(coloring.get(neighbor));
                }
            }
            boolean colored = false;
            for (Register color : PHYSICAL_REGISTERS) {
                if (!usedColors.contains(color)) {
                    coloring.put(r, color);
                    colored = true;
                    break;
                }
            }
            if (!colored) {
                spilled.add(r);
            }
        }
    }

    public Map<Register, Register> getColoring() { return coloring; }
    public Set<Register> getSpilled() { return spilled; }

    // for debug, output interference graph as dot graph
    public String toDot() {
        StringBuilder sb = new StringBuilder();
        sb.append("graph InterferenceGraph {\n");
        for (Register r : adjList.keySet()) {
            sb.append("  \"").append(r.toString()).append("\";\n");
        }
        Set<String> seen = new HashSet<>();
        for (Register r : adjList.keySet()) {
            for (Register neighbor : adjList.get(r)) {
                String edge = r.toString() + "--" + neighbor.toString();
                String reverseEdge = neighbor.toString() + "--" + r.toString();
                if (!seen.contains(edge) && !seen.contains(reverseEdge)) {
                    sb.append("  \"").append(r.toString()).append("\" -- \"").append(neighbor.toString()).append("\";\n");
                    seen.add(edge);
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
}
