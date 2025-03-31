package regalloc;

import gen.asm.*;
import java.util.*;

public final class CFGBuilder {

    // each node represents one instruction
    public static final class CFGNode {
        public final int index;
        public final Instruction instr;
        public final List<CFGNode> succ = new ArrayList<>();
        public final List<CFGNode> pred = new ArrayList<>();
        public final Set<Register.Virtual> uses = new HashSet<>();
        public Register.Virtual def;
        public Set<Register.Virtual> in = new HashSet<>();
        public Set<Register.Virtual> out = new HashSet<>();

        public CFGNode(int i, Instruction ins) {
            index = i;
            instr = ins;
        }
    }

    // CFG consists of nodes and a mapping from labels to node indices
    public static final class CFG {
        public final List<CFGNode> nodes;
        public final Map<Label, Integer> lblMap;
        public CFG(List<CFGNode> nds, Map<Label, Integer> map) {
            nodes = nds;
            lblMap = map;
        }
    }

    public static CFG build(AssemblyProgram.TextSection sec) {
        List<CFGNode> nodes = new ArrayList<>();
        Map<Label, Integer> lblMap = new HashMap<>();
        int idx = 0;
        // process items in the text section
        for (var item : sec.items) {
            if (item instanceof Label l) {
                lblMap.put(l, idx);
            } else if (item instanceof Instruction ins) {
                CFGNode node = new CFGNode(idx, ins);
                nodes.add(node);
                idx++;
            }
        }
        // link each node to its successors
        for (int i = 0; i < nodes.size(); i++) {
            CFGNode n = nodes.get(i);
            if (i < nodes.size() - 1) {
                // fallthrough to next instruction
                n.succ.add(nodes.get(i + 1));
                nodes.get(i + 1).pred.add(n);
            }
            // handle jump instructions
            if (n.instr instanceof Instruction.Jump j) {
                n.succ.clear();
                var targetIdx = lblMap.get(j.label);
                if (targetIdx != null) {
                    n.succ.add(nodes.get(targetIdx));
                    nodes.get(targetIdx).pred.add(n);
                }
            } else if (n.instr instanceof Instruction.JumpRegister) {
                n.succ.clear();
            } else if (n.instr instanceof Instruction.BinaryBranch bb) {
                var targetIdx = lblMap.get(bb.label);
                if (targetIdx != null) {
                    n.succ.add(nodes.get(targetIdx));
                    nodes.get(targetIdx).pred.add(n);
                }
            } else if (n.instr instanceof Instruction.UnaryBranch ub) {
                var targetIdx = lblMap.get(ub.label);
                if (targetIdx != null) {
                    n.succ.add(nodes.get(targetIdx));
                    nodes.get(targetIdx).pred.add(n);
                }
            }
        }
        // record uses and definitions
        for (var n : nodes) {
            var d = n.instr.def();
            if (d instanceof Register.Virtual dv) {
                n.def = dv;
            }
            for (var u : n.instr.uses()) {
                if (u instanceof Register.Virtual uv) {
                    n.uses.add(uv);
                }
            }
        }
        return new CFG(nodes, lblMap);
    }
}
