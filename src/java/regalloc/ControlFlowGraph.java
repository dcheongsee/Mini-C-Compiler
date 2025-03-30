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
        // determine leader indices: first instruction, post-jump/branch, and label targets
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
            if (sequence.get(i) instanceof Label lbl) {
                block.label = lbl.name;
                i++;
            }
            while (i < sequence.size() && (!(leaderIndices.contains(i)) || block.instructions.isEmpty())) {
                Object obj = sequence.get(i);
                if (obj instanceof Label lbl) {
                    if (block.label == null) {
                        block.label = lbl.name;
                    }
                } else if (obj instanceof Instruction instr) {
                    block.instructions.add(instr);
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
                case JUMP:
                    if (lastInstr instanceof Instruction.Jump jumpInstr) {
                        BasicBlock target = labelToBlock.get(jumpInstr.label.name);
                        if (target != null) {
                            block.successors.add(target);
                        }
                    }
                    break;
                case BINARY_BRANCH:
                    if (lastInstr instanceof Instruction.BinaryBranch bb) {
                        BasicBlock target = labelToBlock.get(bb.label.name);
                        if (target != null) {
                            block.successors.add(target);
                        }
                    }
                    if (j + 1 < blocks.size()) {
                        block.successors.add(blocks.get(j + 1));
                    }
                    break;
                case JUMP_REGISTER:
                    // Terminal block, no successors
                    break;
                default:
                    if (j + 1 < blocks.size()) {
                        block.successors.add(blocks.get(j + 1));
                    }
            }
        }
        // build predecessors
        for (BasicBlock block : blocks) {
            for (BasicBlock succ : block.successors) {
                succ.predecessors.add(block);
            }
        }
    }

    public List<BasicBlock> getBlocks() {
        return blocks;
    }
}