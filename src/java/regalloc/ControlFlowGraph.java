package regalloc;

import gen.asm.*;

import java.util.*;

public class ControlFlowGraph {

    public static class BasicBlock {
        public List<Instruction> instructions = new ArrayList<>();
        public Set<Register> use = new HashSet<>();
        public Set<Register> def = new HashSet<>();
        public Set<Register> liveIn = new HashSet<>();
        public Set<Register> liveOut = new HashSet<>();
        public Set<BasicBlock> successors = new HashSet<>();
        public Set<BasicBlock> predecessors = new HashSet<>();
        public String label;
    }

    private final List<BasicBlock> blocks;

    public ControlFlowGraph(List<AssemblyItem> items) {
        blocks = new ArrayList<>();
        // build a linear sequence from items (keeping only labels and instructions)
        List<Object> sequence = new ArrayList<>();
        for (AssemblyItem item : items) {
            if (item instanceof Instruction || item instanceof Label) {
                sequence.add(item);
            }
        }
        // determine leader indices: first instruction, any instruction following a jump/branch, and any target of a label
        Set<Integer> leaderIndices = new HashSet<>();
        for (int i = 0; i < sequence.size(); i++) {
            Object obj = sequence.get(i);
            if (obj instanceof Instruction) {
                leaderIndices.add(i);
                break;
            }
        }
        for (int i = 0; i < sequence.size(); i++) {
            Object obj = sequence.get(i);
            if (obj instanceof Instruction instr) {
                if (instr.opcode.kind() == OpCode.Kind.JUMP ||
                        instr.opcode.kind() == OpCode.Kind.BINARY_BRANCH ||
                        instr.opcode.kind() == OpCode.Kind.JUMP_REGISTER) {
                    if (i + 1 < sequence.size() && sequence.get(i + 1) instanceof Instruction) {
                        leaderIndices.add(i + 1);
                    }
                }
            }
        }
        for (int i = 0; i < sequence.size(); i++) {
            if (sequence.get(i) instanceof Label) {
                if (i + 1 < sequence.size() && sequence.get(i + 1) instanceof Instruction) {
                    leaderIndices.add(i + 1);
                }
            }
        }

        // form basic blocks starting at leader indices
        int i = 0;
        while (i < sequence.size()) {
            if (!leaderIndices.contains(i)) {
                i++;
                continue;
            }
            BasicBlock block = new BasicBlock();
            // if the leader is a Label, record it and advance
            if (sequence.get(i) instanceof Label lbl) {
                block.label = lbl.name;
                i++;
            }
            // add instructions until the next leader is encountered (unless the block is empty)
            while (i < sequence.size() && (!(leaderIndices.contains(i)) || block.instructions.isEmpty())) {
                Object obj = sequence.get(i);
                if (obj instanceof Label lbl) {
                    if (block.label == null) {
                        block.label = lbl.name;
                    }
                } else if (obj instanceof Instruction instr) {
                    block.instructions.add(instr);
                    // end the block immediately if a jump/branch instruction is encountered
                    if (instr.opcode.kind() == OpCode.Kind.JUMP ||
                            instr.opcode.kind() == OpCode.Kind.BINARY_BRANCH ||
                            instr.opcode.kind() == OpCode.Kind.JUMP_REGISTER) {
                        i++;
                        break;
                    }
                }
                i++;
            }
            blocks.add(block);
        }

        // map block labels to blocks for branch targets
        Map<String, BasicBlock> labelToBlock = new HashMap<>();
        for (BasicBlock block : blocks) {
            if (block.label != null) {
                labelToBlock.put(block.label, block);
            }
        }

        // compute successors for each basic block
        for (int j = 0; j < blocks.size(); j++) {
            BasicBlock block = blocks.get(j);
            if (block.instructions.isEmpty()) continue;
            Instruction lastInstr = block.instructions.get(block.instructions.size() - 1);
            switch (lastInstr.opcode.kind()) {
                case JUMP -> {
                    if (lastInstr instanceof Instruction.Jump jumpInstr) {
                        BasicBlock target = labelToBlock.get(jumpInstr.label.name);
                        if (target != null) {
                            block.successors.add(target);
                        }
                    }
                }
                case BINARY_BRANCH -> {
                    if (lastInstr instanceof Instruction.BinaryBranch bb) {
                        BasicBlock target = labelToBlock.get(bb.label.name);
                        if (target != null) {
                            block.successors.add(target);
                        }
                    }
                    if (j + 1 < blocks.size()) {
                        block.successors.add(blocks.get(j + 1));
                    }
                }
                case JUMP_REGISTER -> {
                    // terminal block, no successors
                }
                default -> {
                    if (j + 1 < blocks.size()) {
                        block.successors.add(blocks.get(j + 1));
                    }
                }
            }
        }
        // build predecessors
        for (BasicBlock block : blocks) {
            for (BasicBlock succ : block.successors) {
                succ.predecessors.add(block);
            }
        }

        // compute use and def sets for each block
        for (BasicBlock block : blocks) {
            for (Instruction instr : block.instructions) {
                for (Register r : instr.uses()) {
                    if (!block.def.contains(r) && r.isVirtual()) {
                        block.use.add(r);
                    }
                }
                Register d = instr.def();
                if (d != null && d.isVirtual()) {
                    block.def.add(d);
                }
            }
        }

        // worklist algorithm for block-level liveness analysis
        Queue<BasicBlock> worklist = new LinkedList<>(blocks);
        while (!worklist.isEmpty()) {
            BasicBlock block = worklist.poll();
            Set<Register> oldLiveIn = new HashSet<>(block.liveIn);
            Set<Register> oldLiveOut = new HashSet<>(block.liveOut);

            block.liveOut.clear();
            for (BasicBlock succ : block.successors) {
                block.liveOut.addAll(succ.liveIn);
            }
            Set<Register> newLiveIn = new HashSet<>(block.use);
            Set<Register> diff = new HashSet<>(block.liveOut);
            diff.removeAll(block.def);
            newLiveIn.addAll(diff);
            block.liveIn = newLiveIn;

            if (!block.liveIn.equals(oldLiveIn) || !block.liveOut.equals(oldLiveOut)) {
                worklist.addAll(block.predecessors);
            }
        }
        // (local, per-instruction liveness is computed on demand via computeLocalLiveness)
    }

    public List<BasicBlock> getBlocks() {
        return blocks;
    }

    // returns all instructions from all basic blocks
    public List<Instruction> getInstructions() {
        List<Instruction> instrs = new ArrayList<>();
        for (BasicBlock block : blocks) {
            instrs.addAll(block.instructions);
        }
        return instrs;
    }

    // compute live range lengths for virtual registers across the function
    // returns a mapping from a virtual register to its live range length (in instruction count)
    public Map<Register, Integer> computeLiveRangeLengths() {
        Map<Register, Integer> liveRanges = new HashMap<>();
        Map<Register, Integer> firstOccurrence = new HashMap<>();
        Map<Register, Integer> lastOccurrence = new HashMap<>();
        int index = 0;
        for (BasicBlock block : blocks) {
            for (Instruction instr : block.instructions) {
                for (Register r : instr.registers()) {
                    if (r.isVirtual()) {
                        if (!firstOccurrence.containsKey(r)) {
                            firstOccurrence.put(r, index);
                        }
                        lastOccurrence.put(r, index);
                    }
                }
                index++;
            }
        }
        for (Register r : firstOccurrence.keySet()) {
            int length = lastOccurrence.get(r) - firstOccurrence.get(r) + 1;
            liveRanges.put(r, length);
        }
        return liveRanges;
    }

    // compute local (per-instruction) liveness for a given basic block
    // returns a list where index i corresponds to the live set immediately after instruction i
    public List<Set<Register>> computeLocalLiveness(BasicBlock block) {
        List<Set<Register>> liveAfter = new ArrayList<>();
        int n = block.instructions.size();
        for (int i = 0; i < n; i++) {
            liveAfter.add(new HashSet<>());
        }
        Set<Register> live = new HashSet<>(block.liveOut);
        for (int i = n - 1; i >= 0; i--) {
            Instruction instr = block.instructions.get(i);
            liveAfter.set(i, new HashSet<>(live));
            // live = (live - def) ∪ uses
            Register d = instr.def();
            if (d != null && d.isVirtual()) {
                live.remove(d);
            }
            for (Register r : instr.uses()) {
                if (r.isVirtual()) {
                    live.add(r);
                }
            }
        }
        return liveAfter;
    }

    // debugging: outputs the cfg as a dot graph (by basic block)
    public String toDot() {
        StringBuilder sb = new StringBuilder("digraph CFG {\n");
        for (BasicBlock block : blocks) {
            StringBuilder label = new StringBuilder();
            if (block.label != null) {
                label.append(block.label).append("\\l");
            }
            for (Instruction instr : block.instructions) {
                label.append(instr.toString().replace("\"", "\\\"")).append("\\l");
            }
            sb.append("  \"").append(label.toString()).append("\";\n");
            for (BasicBlock succ : block.successors) {
                StringBuilder succLabel = new StringBuilder();
                if (succ.label != null) {
                    succLabel.append(succ.label).append("\\l");
                }
                for (Instruction instr : succ.instructions) {
                    succLabel.append(instr.toString().replace("\"", "\\\"")).append("\\l");
                }
                sb.append("  \"").append(label.toString()).append("\" -> \"")
                        .append(succLabel.toString()).append("\";\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
}