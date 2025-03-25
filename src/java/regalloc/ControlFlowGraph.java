package regalloc;

import gen.asm.*;

import java.util.*;

public class ControlFlowGraph {
    private List<Instruction> nodes;
    private Map<Instruction, Set<Instruction>> successors;
    private Map<Instruction, Set<Register>> liveIn;
    private Map<Instruction, Set<Register>> liveOut;
    private Map<String, Instruction> labelToInstruction;

    public ControlFlowGraph(List<AssemblyItem> items) {
        nodes = new ArrayList<>();
        successors = new HashMap<>();
        liveIn = new HashMap<>();
        liveOut = new HashMap<>();
        labelToInstruction = new HashMap<>();

        // collect instructions and map labels
        for (AssemblyItem item : items) {
            if (item instanceof Instruction) {
                nodes.add((Instruction) item);
                successors.put((Instruction) item, new HashSet<>());
            } else if (item instanceof Label) {
                labelToInstruction.put(((Label) item).name, nodes.isEmpty() ? null : nodes.get(nodes.size() - 1));
            }
        }

        // build CFG edges
        for (int i = 0; i < nodes.size(); i++) {
            Instruction instr = nodes.get(i);
            Instruction next = (i + 1 < nodes.size()) ? nodes.get(i + 1) : null;
            if (instr.opcode.kind() == OpCode.Kind.JUMP || instr.opcode == OpCode.JAL) {
                String target = ((Instruction.Jump) instr).label.name;
                Instruction targetInstr = labelToInstruction.get(target);
                if (targetInstr != null) successors.get(instr).add(targetInstr);
            } else if (instr.opcode.kind() == OpCode.Kind.BINARY_BRANCH) {
                String target = ((Instruction.BinaryBranch) instr).label.name;
                Instruction targetInstr = labelToInstruction.get(target);
                if (targetInstr != null) successors.get(instr).add(targetInstr);
                if (next != null) successors.get(instr).add(next);
            } else if (next != null) {
                successors.get(instr).add(next);
            }
        }
    }

    public void computeLiveness() {
        for (Instruction instr : nodes) {
            liveIn.put(instr, new HashSet<>());
            liveOut.put(instr, new HashSet<>());
        }
        boolean changed;
        do {
            changed = false;
            for (int i = nodes.size() - 1; i >= 0; i--) {
                Instruction instr = nodes.get(i);
                Set<Register> oldLiveIn = new HashSet<>(liveIn.get(instr));
                Set<Register> oldLiveOut = new HashSet<>(liveOut.get(instr));
                Set<Register> newLiveOut = new HashSet<>();
                for (Instruction succ : successors.get(instr)) {
                    newLiveOut.addAll(liveIn.get(succ));
                }
                liveOut.put(instr, newLiveOut);
                Set<Register> newLiveIn = new HashSet<>(instr.uses());
                Set<Register> def = new HashSet<>();
                if (instr.def() != null) def.add(instr.def());
                for (Register r : newLiveOut) {
                    if (!def.contains(r)) newLiveIn.add(r);
                }
                liveIn.put(instr, newLiveIn);
                if (!oldLiveIn.equals(newLiveIn) || !oldLiveOut.equals(newLiveOut)) {
                    changed = true;
                }
            }
        } while (changed);
        // handle dead defs, add defined register to liveOut if not present
        for (Instruction instr : nodes) {
            if (instr.def() != null && !liveOut.get(instr).contains(instr.def())) {
                liveOut.get(instr).add(instr.def());
            }
        }
    }

    public List<Instruction> getNodes() { return nodes; }
    public Set<Instruction> getSuccessors(Instruction instr) { return successors.get(instr); }
    public Set<Register> getLiveIn(Instruction instr) { return liveIn.get(instr); }
    public Set<Register> getLiveOut(Instruction instr) { return liveOut.get(instr); }

    // determines if an instruction is dead definition (and safe to remove)
    public boolean isDead(Instruction instr) {
        // only remove arithmetic instructions whose defined register is not live-out
        if (instr.def() != null && !liveOut.get(instr).contains(instr.def())) {
            return (instr instanceof Instruction.TernaryArithmetic ||
                    instr instanceof Instruction.UnaryArithmetic ||
                    instr instanceof Instruction.ArithmeticWithImmediate);
        }
        return false;
    }

    // output the CFG as a dot graph
    public String toDot() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph CFG {\n");
        for (Instruction instr : nodes) {
            String instrLabel = instr.toString().replace("\"", "\\\"");
            sb.append("  \"").append(instrLabel).append("\";\n");
            for (Instruction succ : successors.get(instr)) {
                String succLabel = succ.toString().replace("\"", "\\\"");
                sb.append("  \"").append(instrLabel).append("\" -> \"").append(succLabel).append("\";\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
}
