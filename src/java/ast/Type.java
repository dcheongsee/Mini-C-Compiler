package ast;

public sealed interface Type extends ASTNode
        permits ArrayType, BaseType, PointerType, StructType {
}