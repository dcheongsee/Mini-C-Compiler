package gen.asm;

sealed public abstract class AssemblyTextItem extends AssemblyItem permits Comment, Directive, Label {
}
