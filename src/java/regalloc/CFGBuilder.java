package regalloc;

import gen.asm.*;

import java.util.*;

public final class CFGBuilder {

    public static final class BasicBlock {
        public final int index;
        public final List<Instruction> instrs = new ArrayList<>();
        public final Set<Register.Virtual> use = new HashSet<>();
        public final Set<Register.Virtual> def = new HashSet<>();
        public Set<Register.Virtual> in = new HashSet<>();
        public Set<Register.Virtual> out = new HashSet<>();
        public final List<BasicBlock> succ = new ArrayList<>();
        public final List<BasicBlock> pred = new ArrayList<>();

        public BasicBlock(int index) {
            this.index = index;
        }
    }

    public static final class CFG {
        public final List<BasicBlock> blocks;
        public final Map<Label,Integer> lblMap; // maps a label to the block index that begins at that label
        public CFG(List<BasicBlock> blocks, Map<Label,Integer> lblMap) {
            this.blocks = blocks;
            this.lblMap = lblMap;
        }
    }

    public static CFG build(AssemblyProgram.TextSection sec) {
        // Collect instructions and associate labels with instruction indices.
        List<Instruction> instructions = new ArrayList<>();
        Map<Integer, List<Label>> instrLabels = new HashMap<>();
        Map<Label, Integer> labelToInstr = new HashMap<>();
        int instrIndex = 0;
        for (var item : sec.items) {
            if (item instanceof Label l) {
                instrLabels.computeIfAbsent(instrIndex, k -> new ArrayList<>()).add(l);
                labelToInstr.put(l, instrIndex);
            } else if (item instanceof Instruction ins) {
                instructions.add(ins);
                instrIndex++;
            }
        }
        // Identify leaders.
        Set<Integer> leaders = new HashSet<>();
        if (!instructions.isEmpty()) {
            leaders.add(0);
        }
        for (int i = 0; i < instructions.size(); i++) {
            // If an instruction has a label, mark it as a leader.
            if (instrLabels.containsKey(i)) {
                leaders.add(i);
            }
            // If the previous instruction is a jump or branch, mark this as a leader.
            if (i > 0) {
                Instruction prev = instructions.get(i - 1);
                if (prev instanceof Instruction.Jump || prev instanceof Instruction.BinaryBranch || prev instanceof Instruction.UnaryBranch) {
                    leaders.add(i);
                }
            }
        }
        List<Integer> leaderList = new ArrayList<>(leaders);
        Collections.sort(leaderList);
        // Partition instructions into basic blocks.
        List<BasicBlock> blocks = new ArrayList<>();
        // Map each instruction index to its block index.
        int[] blockForInstruction = new int[instructions.size()];
        for (int i = 0; i < leaderList.size(); i++) {
            int start = leaderList.get(i);
            int end = (i + 1 < leaderList.size()) ? leaderList.get(i + 1) : instructions.size();
            BasicBlock block = new BasicBlock(blocks.size());
            for (int j = start; j < end; j++) {
                block.instrs.add(instructions.get(j));
                blockForInstruction[j] = block.index;
            }
            blocks.add(block);
        }
        // Compute use and def for each basic block.
        for (BasicBlock block : blocks) {
            for (Instruction ins : block.instrs) {
                // For each use: if not already defined in the block, add to use.
                for (var u : ins.uses()) {
                    if (u instanceof Register.Virtual vr) {
                        if (!block.def.contains(vr)) {
                            block.use.add(vr);
                        }
                    }
                }
                // For each def: add to def.
                if (ins.def() instanceof Register.Virtual vr) {
                    block.def.add(vr);
                }
            }
        }
        // Build control flow: determine successors for each block.
        Map<Label, Integer> lblMap = new HashMap<>(); // maps label to block index
        // Populate lblMap using labelToInstr and blockForInstruction.
        for (var entry : labelToInstr.entrySet()) {
            Label l = entry.getKey();
            int instrIdx = entry.getValue();
            int blockIdx = blockForInstruction[instrIdx];
            lblMap.put(l, blockIdx);
        }
        for (int i = 0; i < blocks.size(); i++) {
            BasicBlock block = blocks.get(i);
            if (block.instrs.isEmpty()) continue;
            Instruction last = block.instrs.get(block.instrs.size() - 1);
            if (last instanceof Instruction.Jump j) {
                Integer targetBlock = lblMap.get(j.label);
                if (targetBlock != null) {
                    BasicBlock target = blocks.get(targetBlock);
                    block.succ.add(target);
                    target.pred.add(block);
                }
            } else if (last instanceof Instruction.BinaryBranch bb) {
                Integer targetBlock = lblMap.get(bb.label);
                if (targetBlock != null) {
                    BasicBlock target = blocks.get(targetBlock);
                    block.succ.add(target);
                    target.pred.add(block);
                }
                // Fall-through successor.
                if (i + 1 < blocks.size()) {
                    BasicBlock fallthrough = blocks.get(i + 1);
                    block.succ.add(fallthrough);
                    fallthrough.pred.add(block);
                }
            } else if (last instanceof Instruction.UnaryBranch ub) {
                Integer targetBlock = lblMap.get(ub.label);
                if (targetBlock != null) {
                    BasicBlock target = blocks.get(targetBlock);
                    block.succ.add(target);
                    target.pred.add(block);
                }
                // Fall-through successor.
                if (i + 1 < blocks.size()) {
                    BasicBlock fallthrough = blocks.get(i + 1);
                    block.succ.add(fallthrough);
                    fallthrough.pred.add(block);
                }
            } else {
                // Default fall-through.
                if (i + 1 < blocks.size()) {
                    BasicBlock fallthrough = blocks.get(i + 1);
                    block.succ.add(fallthrough);
                    fallthrough.pred.add(block);
                }
            }
        }
        return new CFG(blocks, lblMap);
    }
}
