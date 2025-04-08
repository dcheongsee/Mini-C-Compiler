package gen;

import ast.*;
import gen.asm.AssemblyProgram;
import gen.asm.Directive;
import gen.asm.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This visitor should produce a program.
 */
public class ProgramCodeGen extends CodeGen {

    public ProgramCodeGen(AssemblyProgram asmProg) {
        this.asmProg = asmProg;
    }

    // map of class names to their ClassDecl for quick lookup
    private Map<String, ClassDecl> classDecls = new HashMap<>();

    void generate(Program p) {
        // allocate all variables
        MemAllocCodeGen allocator = new MemAllocCodeGen(asmProg);
        allocator.visit(p);

        // collect all class declarations for vtable generation
        for (Decl d : p.decls) {
            if (d instanceof ClassDecl cd) {
                classDecls.put(cd.getName(), cd);
            }
        }

        // emit virtual tables for each class
        for (ClassDecl cd : classDecls.values()) {
            List<Label> vtableEntries = computeVTable(cd);
            emitVTable(cd, vtableEntries);
        }

        // generate the code for each function
        p.decls.forEach((d) -> {
            switch(d) {
                case FunDef fd -> {
                    FunCodeGen fcg = new FunCodeGen(asmProg);
                    fcg.visit(fd);
                }
                default -> {}// nothing to do
            }
        });
    }


    private List<Label> computeVTable(ClassDecl cd) {
        // use a LinkedHashMap to maintain method order: method name -> label
        LinkedHashMap<String, Label> vtable = new LinkedHashMap<>();

        // inherit parent's vtable if present
        if (cd.getParentName() != null) {
            ClassDecl parent = classDecls.get(cd.getParentName());
            if (parent != null) {
                // recursively get parent's vtable mapping
                vtable.putAll(getVTableMapping(parent));
            }
        }

        // add or override methods declared in this class
        for (FunDef method : cd.getMethods()) {
            Label methodLabel = Label.get(method.name);
            vtable.put(method.name, methodLabel);
        }

        return new ArrayList<>(vtable.values());
    }


    private LinkedHashMap<String, Label> getVTableMapping(ClassDecl cd) {
        LinkedHashMap<String, Label> map = new LinkedHashMap<>();
        if (cd.getParentName() != null) {
            ClassDecl parent = classDecls.get(cd.getParentName());
            if (parent != null) {
                map.putAll(getVTableMapping(parent));
            }
        }
        for (FunDef method : cd.getMethods()) {
            map.put(method.name, Label.get(method.name));
        }
        return map;
    }


    private void emitVTable(ClassDecl cd, List<Label> vtableEntries) {
        String vtLabelName = "vt_" + cd.getName();
        Label vtLabel = Label.get(vtLabelName);
        asmProg.dataSection.emit(new Directive("align 2"));
        asmProg.dataSection.emit(vtLabel);
        for (Label methodLabel : vtableEntries) {
            asmProg.dataSection.emit(new Directive("word " + methodLabel));
        }
    }
}
