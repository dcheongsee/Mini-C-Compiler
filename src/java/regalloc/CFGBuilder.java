package regalloc;

import gen.asm.*;

import java.util.*;

public final class CFGBuilder {

    // node stores one instruction
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

    // cfg has nodes and label map
    public static final class CFG {
        public final List<CFGNode> nodes;
        public final Map<Label,Integer> lblMap;
        public CFG(List<CFGNode> nds, Map<Label,Integer> map) {
            nodes = nds;
            lblMap = map;
        }
    }

    public static CFG build(AssemblyProgram.TextSection sec) {
        // collects instructions and labels
        List<CFGNode> nodes = new ArrayList<>();
        Map<Label,Integer> lblMap = new HashMap<>();
        int idx = 0;
        // scans items in text section
        for (var item : sec.items) {
            // label marks next node index
            if (item instanceof Label l) {
                lblMap.put(l, idx);
            } else if (item instanceof Instruction ins) {
                // create a node for each instruction
                CFGNode node = new CFGNode(idx, ins);
                nodes.add(node);
                idx++;
            }
        }
        // link each node to successors
        for (int i = 0; i < nodes.size(); i++) {
            CFGNode n = nodes.get(i);
            if (i < nodes.size() - 1) {
                // fallthrough to next node
                n.succ.add(nodes.get(i+1));
                nodes.get(i+1).pred.add(n);
            }
            // handle unconditional jumps
            if (n.instr instanceof Instruction.Jump j) {
                n.succ.clear();
                var t = lblMap.get(j.label);
                if (t != null) {
                    n.succ.add(nodes.get(t));
                    nodes.get(t).pred.add(n);
                }
            }
            // jump register ends control flow
            else if (n.instr instanceof Instruction.JumpRegister) {
                n.succ.clear();
            }
            // handle binary branch
            else if (n.instr instanceof Instruction.BinaryBranch bb) {
                var t = lblMap.get(bb.label);
                if (t != null) {
                    n.succ.add(nodes.get(t));
                    nodes.get(t).pred.add(n);
                }
            }
            // handle unary branch
            else if (n.instr instanceof Instruction.UnaryBranch ub) {
                var t = lblMap.get(ub.label);
                if (t != null) {
                    n.succ.add(nodes.get(t));
                    nodes.get(t).pred.add(n);
                }
            }
        }
        // record uses and def
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
        // returns built cfg
        return new CFG(nodes, lblMap);
    }
}