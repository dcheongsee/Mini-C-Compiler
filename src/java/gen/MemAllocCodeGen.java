package gen;

import ast.*;
import gen.asm.AssemblyProgram;
import gen.asm.Label;
import gen.asm.Directive;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* This allocator should deal with all global and local variable declarations. */

public class MemAllocCodeGen extends CodeGen {

    // mapping of struct names to their decl
    private Map<String, StructTypeDecl> structDecls = new HashMap<>();
    private final Set<String> emittedGlobals = new HashSet<>();

    public MemAllocCodeGen(AssemblyProgram asmProg) {
        this.asmProg = asmProg;
    }

    boolean global = true;
    int fpOffset = 0;

    // aligns value to given alignment
    private int alignTo(int value, int alignment) {
        return alignment == 0 ? value : ((value + alignment - 1) / alignment) * alignment;
    }

    // returns size in bytes for given type
    private int getSize(Type type) {
        if (type instanceof BaseType bt) {
            return switch (bt) {
                case INT -> 4;
                case CHAR -> 1;
                case VOID, UNKNOWN, NONE -> 0;
            };
        } else if (type instanceof PointerType) {
            return 4;
        } else if (type instanceof ArrayType at) {
            return getSize(at.elementType) * at.length;
        } else if (type instanceof StructType st) {
            StructTypeDecl decl = structDecls.get(st.name);
            if (decl == null) throw new RuntimeException("Struct not found: " + st.name);
            return computeStructSize(decl);
        }
        return 0;
    }

    // returns alignment requirement for given type
    private int getAlignment(Type type) {
        if (type instanceof BaseType bt) {
            return switch (bt) {
                case INT -> 4;
                case CHAR -> 1;
                case VOID, UNKNOWN, NONE -> 0;
            };
        } else if (type instanceof PointerType) {
            return 4;
        } else if (type instanceof ArrayType at) {
            return getAlignment(at.elementType);
        } else if (type instanceof StructType st) {
            StructTypeDecl decl = structDecls.get(st.name);
            if (decl == null) throw new RuntimeException("Struct not found: " + st.name);
            return computeStructAlignment(decl);
        }
        return 1;
    }

    // computes total size of a struct from its decl
    private int computeStructSize(StructTypeDecl decl) {
        int offset = 0;
        int maxAlign = 1;
        for (Decl field : decl.getFields()) {
            if (field instanceof VarDecl vd) {
                int size = getSize(vd.type);
                int align = getAlignment(vd.type);
                offset = alignTo(offset, align);
                offset += size;
                if (align > maxAlign) {
                    maxAlign = align;
                }
            }
        }
        // now force struct’s alignment to at least 4
        maxAlign = Math.max(4, maxAlign);
        // then align the final offset too
        offset = alignTo(offset, maxAlign);
        return offset;
    }

    // computes max alignment among struct fields
    private int computeStructAlignment(StructTypeDecl decl) {
        int maxAlign = 1;
        for (Decl field : decl.getFields()) {
            if (field instanceof VarDecl vd) {
                int align = getAlignment(vd.type);
                if (align > maxAlign) {
                    maxAlign = align;
                }
            }
        }
        // force every struct's alignment to be at least 4
        maxAlign = Math.max(4, maxAlign);
        return maxAlign;
    }

    public void visit(ASTNode n) {
        // for the Program node, process its decls only.
        if (n instanceof Program p) {
            for (Decl d : p.decls) {
                if (d instanceof StructTypeDecl) {
                    structDecls.put(((StructTypeDecl) d).getName(), (StructTypeDecl) d);
                } else {
                    visit(d, false);
                }
            }
            return;
        }
        visit(n, false);
    }

    private void visit(ASTNode n, boolean inStruct) {
        if (n == null) return;

        // handle ClassDecl for object layout
        if (n instanceof ClassDecl cd) {
            // compute the object layout for the class
            computeClassLayout(cd);
            // process children (if any) but no further layout is needed here
            for (ASTNode child : n.children()) {
                visit(child, false);
            }
            return;
        }

        if (n instanceof StructTypeDecl std) {
            structDecls.put(std.getName(), std);
            for (ASTNode child : n.children()) {
                visit(child, true);
            }
            return;
        }

        if (n instanceof FunDef fd) {
            boolean oldGlobal = global;
            global = false;
            int paramOffset = 8;
            for (VarDecl p : fd.params) {
                int size = getSize(p.type);
                int align = getAlignment(p.type);
                paramOffset = alignTo(paramOffset, align);
                p.offset = paramOffset;
                p.isParameter = true;
                paramOffset += size;
            }

            int savedFp = fpOffset;
            fpOffset = 8;
            visit(fd.block, false);
            fd.localVarSize = fpOffset;
            fpOffset = savedFp;
            global = oldGlobal;
            return;
        }

        if (n instanceof Block b) {
            for (VarDecl vd : b.vds) {
                int size = getSize(vd.type);
                int align = getAlignment(vd.type);
                fpOffset = alignTo(fpOffset, align);
                fpOffset += size;
                vd.offset = -fpOffset;
                // Debug print: report local variable allocation
                System.out.println("Allocated local variable '" + vd.name + "' of type " + vd.type +
                        " at offset " + vd.offset);
            }
            for (ASTNode child : b.children()) {
                visit(child, false);
            }
            return;
        }

        if (n instanceof VarDecl vd) {
            // if we're inside a struct decl, skip global allocation
            if (inStruct) return;
            if (global) {
                String label = "global_" + vd.name;
                // check if global label was already emitted
                if (emittedGlobals.contains(label))
                    return;
                emittedGlobals.add(label);
                vd.globalLabel = label;
                int size = getSize(vd.type);
                asmProg.dataSection.emit(new Directive("align 2"));
                asmProg.dataSection.emit(Label.get(label));
                asmProg.dataSection.emit(new Directive("space " + size));
            }
            return;
        }

        for (ASTNode child : n.children()) {
            visit(child, inStruct);
        }
    }


    private void computeClassLayout(ClassDecl cd) {
        int baseOffset = 4; // default: no parent then first field starts at offset 4
        if (cd.getParentName() != null) {
            // look up the parent's class declaration in the global AST
            ClassDecl parent = null;
            for (Decl d : sem.SemanticAnalyzer.GlobalAST.decls) {
                if (d instanceof ClassDecl c && c.getName().equals(cd.getParentName())) {
                    parent = c;
                    break;
                }
            }
            if (parent != null) {
                // recursively ensure parent's layout is computed
                computeClassLayout(parent);
                baseOffset = parent.objectSize;
            }
        }
        // for each field in this class, assign an offset (4 bytes per field)
        // note the parser/type analyzer ensures that a subclass does not redeclare a parent's field
        for (int i = 0; i < cd.getFields().size(); i++) {
            VarDecl field = cd.getFields().get(i);
            field.offset = baseOffset + i * 4;
        }
        // the total object size is the baseOffset plus 4 bytes for each field declared in this class
        int totalSize = baseOffset + cd.getFields().size() * 4;
        cd.objectSize = totalSize;
        System.out.println("Computed object layout for class " + cd.getName() +
                ": objectSize = " + totalSize + ", field offsets: " +
                cd.getFields().stream().map(f -> f.name + "=" + f.offset).toList());
    }

}
