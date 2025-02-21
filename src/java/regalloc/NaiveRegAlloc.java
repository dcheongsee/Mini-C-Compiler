// Authors: Christophe Dubach, Jonathan Van der Cruysse

package regalloc;

import gen.asm.*;

import java.util.*;

/**
 * A very naive register allocator which allocates each virtual registers in the data section with a label.
 * The allocator assumes that each function has a single corresponding text section.
 */
public final class NaiveRegAlloc implements AssemblyPass {

    private NaiveRegAlloc() { }

    private static Map<Register.Virtual, Label>  collectVirtualRegisters(AssemblyProgram.TextSection section) {
        final Map<Register.Virtual, Label> vrMap = new HashMap<>();

        section.items.forEach((item) -> {
            switch (item) {
                case Instruction insn -> insn.registers().forEach(reg -> {
                    if (reg instanceof Register.Virtual) {
                        Register.Virtual vr = (Register.Virtual) reg;
                        Label l = Label.create(vr.toString());
                        vrMap.put(vr, l);
                    }
                });
                default -> {} // nothing to do
            }
        });

        return vrMap;
    }

    private static void emitInstructionWithoutVirtualRegister(Instruction insn, Map<Register.Virtual, Label> vrMap, AssemblyProgram.TextSection section) {

        section.emit("Original instruction: "+insn);

        final Map<Register, Register> vrToAr = new HashMap<>();
        Register[] tempRegs = {Register.Arch.t0, Register.Arch.t1, Register.Arch.t2, Register.Arch.t3, Register.Arch.t4, Register.Arch.t5}; // 6 temporaries should be more than enough
        final Stack<Register> freeTempRegs = new Stack<>();
        freeTempRegs.addAll(Arrays.asList(tempRegs));

        // creates a map from virtual register to temporary architecture register for all registers appearing in the instructions
        insn.registers().forEach(reg -> {
            if (reg.isVirtual()) {
                Register tmp = freeTempRegs.pop();
                Label label = vrMap.get(reg);
                vrToAr.put(reg, tmp);
            }
        });

        // load the values of any virtual registers used by the instruction from memory into a temporary architectural register
        insn.uses().forEach(reg -> {
            if (reg.isVirtual()) {
                Register tmp = vrToAr.get(reg);
                Label label = vrMap.get(reg);
                section.emit(OpCode.LA, tmp, label);
                section.emit(OpCode.LW, tmp, tmp, 0);
            }
        });

        // emit new instructions where all virtual register have been replaced by architectural ones
        section.emit(insn.rebuild(vrToAr));

        if (insn.def() != null) {
            if (insn.def().isVirtual()) {
                Register tmpVal = vrToAr.get(insn.def());
                Register tmpAddr = freeTempRegs.remove(0);
                Label label = vrMap.get(insn.def());

                section.emit(OpCode.LA, tmpAddr, label);
                section.emit(OpCode.SW, tmpVal, tmpAddr, 0);
            }
        }
    }

    private static AssemblyProgram run(AssemblyProgram prog) {

        AssemblyProgram newProg = new AssemblyProgram();

        // copy all items from the data section
        prog.dataSection.items.forEach( item -> {
            newProg.dataSection.emit(item);
        });

        // each function has a single corresponding text section
        prog.textSections.forEach(section -> {
            {

                // map from virtual register to corresponding uniquely created label
                final Map<Register.Virtual, Label> vrMap = collectVirtualRegisters(section);

                // allocate one label for each virtual register in a new data section
                //AssemblyProgram.DataSection dataSec = newProg.newSection(AssemblyProgram.Section.Type.DATA);
                newProg.dataSection.emit("Allocated labels for virtual registers");
                vrMap.forEach((vr, lbl) -> {
                    newProg.dataSection.emit(lbl);
                    newProg.dataSection.emit(new Directive("space " + 4));
                });

                // emit new instructions that don't use any virtual registers and transform push/pop registers instructions into real sequence of instructions
                // When dealign with push/pop registers, we assume that if a virtual register is used in the section, then it must be written into.
                final AssemblyProgram.TextSection newSection = newProg.emitNewTextSection();
                List<Label> vrLabels = new LinkedList<>(vrMap.values());
                List<Label> reverseVrLabels = new LinkedList<>(vrLabels);
                Collections.reverse(reverseVrLabels);

                section.items.forEach((item) -> {
                    switch (item) {
                        case AssemblyTextItem ati -> newSection.emit(ati);
                        case Instruction insn -> {
                            if (insn == Instruction.Nullary.pushRegisters) {
                                newSection.emit("Original instruction: pushRegisters");
                                for (Label l : vrLabels) {
                                    // load content of memory at label into $t0
                                    newSection.emit(OpCode.LA, Register.Arch.t0, l);
                                    newSection.emit(OpCode.LW, Register.Arch.t0, Register.Arch.t0, 0);

                                    // push $t0 onto stack
                                    newSection.emit(OpCode.ADDI, Register.Arch.sp, Register.Arch.sp, -4);
                                    newSection.emit(OpCode.SW, Register.Arch.t0, Register.Arch.sp, 0);
                                }
                            } else if (insn == Instruction.Nullary.popRegisters) {
                                newSection.emit("Original instruction: popRegisters");
                                for (Label l : reverseVrLabels) {
                                    // pop from stack into $t0
                                    newSection.emit(OpCode.LW, Register.Arch.t0, Register.Arch.sp, 0);
                                    newSection.emit(OpCode.ADDI, Register.Arch.sp, Register.Arch.sp, 4);

                                    // store content of $t0 in memory at label
                                    newSection.emit(OpCode.LA, Register.Arch.t1, l);
                                    newSection.emit(OpCode.SW, Register.Arch.t0, Register.Arch.t1, 0);
                                }
                            } else
                                emitInstructionWithoutVirtualRegister(insn, vrMap, newSection);
                        }
                    }
                });
            }
        });


        return newProg;
    }

    /**
     * The singleton instance of {@link NaiveRegAlloc}.
     */
    public static final NaiveRegAlloc INSTANCE = new NaiveRegAlloc();

    @Override
    public AssemblyProgram apply(AssemblyProgram program) {
        return run(program);
    }
}
