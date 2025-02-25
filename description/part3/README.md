# Part III : Code Generation


The goal of part III is to write the code generator, targeting MIPS32 assembly.
For this part, we only use virtual registers (except for special purpose registers such as `$sp, $fp, ...`).
A naive register allocator (`NaiveRegAlloc`) has been provided that assigns each virtual register to a label and uses memory to store the content of the registers.


## Tips


* Writing many small passes to break down the complexity of the task ahead of us. For instance, having one pass to perform variable allocations, one to allocate string literals, one to emit code for the value of expression, ...
* Making use of the `children()` method when writing our passes to reduce the size of our code.
* When unsure about the correct semantics of our code, we go with the C semantics.



## 0. Setup and Learning

Our first task consist of setting up the MARS MIPS simulator.
We first download the simulator [here](./Mars4_5.jar) and follow Part 1 of the [tutorial](https://cs.slu.edu/~fritts/CSCI140_F12/schedule/MARS%20Tutorial.pdf) to learn how to use the simulator.
We also have a look at the documentation provided by MARS which can be found [here](https://dpetersanderson.github.io/Help/MarsHelpIntro.html) as well as the [MIPS 32 Instruction Set Quick Reference](./MD00565-2B-MIPS32-QRC-01.01-1.pdf).
For a more detailed explanation about the MIPS architecture, we have [MIPS Assembly WikiBooks](http://en.wikibooks.org/wiki/MIPS_Assembly).
Another MIPS summary of all the instructions supported : [MIPS Green Card](https://booksite.elsevier.com/9780124077263/downloads/COD_5e_Greencard.pdf)

We can also use this [online simulator](https://ecse324.ece.mcgill.ca/simulator/?sys=mipsr5-spim) for debugging our programs as the comments remain visible when stepping through instructions.

**Important**:
We make sure that all our tests execute correctly with the Mars simulator run from the command line with the following command:
`java -jar MARS_JAR_FILE sm nc me ASM_FILE`


## 1. Generating a simple program and main function

Our first real task consists of producing an empty program (e.g. just an empty main function) and see that we can produce an assembly file.
A well-formed assembly program should always have a `main` function with the following signature: `void main()`.
If the signature of `main` is different, or if `main` is missing, we will consider this as undefined behaviour.
The `main` function will always act as the entry point to our program.
We should ensure that upon starting, the simulator will execute the code of the `main` function.
If we use the command line above, the `sm` option will ensure that execution starts at the instruction with global label `main`.

We ensure that the simulator exits properly when the main function finishes (otherwise random instructions might execute).
We use the following sequence of instruction upon returning from main:
```
li $v0, 10
syscall		# Use syscall 10 to stop simulation
```
To simplify, we will assume that the main function is never called recursively.

Next, we implement the `print_i` function using the corresponding system calls.
To test it, we implement support for integer literals and have a simple call to `print_i` in `main`.

## 2. Binary Operators

Having succeeded in compiling a very simple program, our next task is to add support for all the binary operators, which is mostly done by implementing the `ExprValCodeGen`.
When we need to request a new virtual register to store the results of an operation, we simply instantiate one with `Register.Virtual.create()`.

### Integer operations

All operations on integer types are performed using signed arithmetic.

### Logical operators

Please note that the `||` and `&&` operators are implemented with control flow.
In the following example

```C
if ((1==0) && foo() == 2)
    ...
```

the function foo is never called at runtime since the semantics of `&&` are that if the left side is false, the right side expression should not be executed.
Similar logic applies for `||`. 



## 3. Variable allocations and uses

Next, we implement memory allocation of global and local variables.

The global variables all go in the static storage area (data section of the assembly file).

The local variables (variables inside a function) go onto the stack.
We allocate them at a fix offset from the frame pointer (`$fp`) and store this offset directly in the `VarDecl` AST node as a field (alternatively, we can store them in a symbol table).

We complete and implement the `MemAllocCodeGen` class to deal with the allocation of variables.
Note that we can also separate the allocator into a global one and a local one.
In this case, we can call the global allocator from the `ProgramCodeGen` and then call our local allocator just before processing the function declaration in the `FunCodeGen`.


Next we implement the logic to read and write local or global variables.
We use the `lw` and `sw` instruction to read from or write to a variable respectively.
The tricky part will be to identify the location of the variables; either a label if globally allocated, or an offset from the frame pointer if locally allocated.
We store this allocation information in the `VarDecl` node when allocating variables (or again using a symbol table).

### `sizeof` and data alignment

We adopt the following specification for the size of the different types:
`sizeof(void)==0, sizeof(char)==1`, `sizeof(int)==4`, `sizeof(pointer_type)==4`

Arrays are represented in a compact form.

For a struct, no matter where it appears (e.g. in an array, or in another struct), we are able to access its field with an instruction corresponding to the data alignment of the field (e.g. `lw` for an `int` field, `lb` for a `char` field).
This means:

   * All fields are aligned to respect the data size alignment (e.g. `int` must be aligned to 4 bytes);
   * The size of a struct is a multiple of the largest field alignment constrained (e.g if a struct contains a field of type `int`, then its size must be a multiple of 4 bytes).

So for instance, in the following example:
```C
struct S1 {
  char c1;
  char c2;
  int a;
  char c3;
};
struct S2 {
  char c1;
  char c2;
  char c3;
};
struct S3 {
  char c1;
  struct S1 s;
  char c2; 
}
struct S4 {
  char c1;
  struct S2 s;
  char c2; 
}
```
`S1` has size 12, `S2` has size 3, `S3` has size 20 and `S2` has size 5.

Finally, we ensure that all variable declarations are aligned correctly.


## 4. struct/array accesses and assignments

Next we add support for struct and array accesses.
This can be implemented using the `lw` and `sw` instructions for struct and a combination of `add` instruction with the `lw` and `sw` instructions for array accesses.
The idea is to get the address of an array into a register, then add to it the index, keeping in mind that addresses are expressed in byte.
So, an access to `a[x]` where `a` is an int array means an offset of `x*4` from the base address where the array is stored).

As part of this step, we also implement assignments.


## 5. Branching (if-then-else, loop, logical operators)

We then implement the loop and if-then-else control structures using the branch instructions.


## 6. Function calls

We then move on to implementing function calls, by far the most challenging part.

To keep things simple, we pass all arguments and return values using the stack, rather by register.
This will simplify greatly our code generation logic.

We emit instructions on the caller side before the call occurs (precall) and after the call (postreturn).
On the callee side we emit instructions in the epilogue and prologue.

To facilitate the implementation of function call, two "pseudo" instructions: `pushRegisters` and `popRegisters` have been provided.
These two instructions are responsible for pushing all the registers used by the function onto the stack and restore them.
These two instructions are "expended" during register allocation since this is only at that stage of the compilation that we know exactly how many registers the function uses.
We follow the convention when it comes to function calls.

Beware that structs are passed by value, both when used as argument to the function, and when returned from a function.

### Updating the stack pointer

When manipulating the stack pointer, we use the `addiu` instruction to avoid encountering an exception with an integer overflow.
This can happen if the `$sp` is initialized at 0 by the simulator.

## 7. Standard library functions

Finally, we add support for all the standard library functions found in the file `minic-stdlib.h` provided in the `tests/` folder.
These should all be implemented using [system calls](https://dpetersanderson.github.io/Help/SyscallHelp.html).


## New Files

A new package has been added under `gen/`. This package is used to store our code generator.

 * The `gen.CodeGenerator` is the only class which `Main.java` directly interfaces with.
 * The `gen.*Gen` classes are the main classes that we will use to produce code.
 * The class `gen.asm.Test` shows an example on how to emit instructions.

Another new package has been added under `gen/asm`.
This package defines the components of assembly programs:
 * `gen.asm.Register` represents registers and defines most MIPS32 registers in its `Arch` subclass.
 * `gen.asm.AssemblyProgram` represents assembly programs that consist of several `Section` instances.
   `AssemblyProgram`'s `emit` methods provide a fluent, type-safe instruction generation API.
 * `gen.asm.AssemblyParser` can parse textual assembly programs as `gen.AssemblyProgram` instances.
   `AssemblyParser` restricts itself to the subset of assembly programs that the compiler can output.
 * `gen.asm.AssemblyItem` has subclasses for the items that appear in an assembly program: `Label`, `Instruction`, `Directive` and `Comment`.
 * `gen.asm.Instruction` has subclasses that represent families of instructions with similar behavior.
 * `gen.asm.OpCode` enumerates MIPS opcodes.

**Note:** We do not modify the files under `gen/asm`.
 
 The new package `regalloc/` contains a naive register allocator: `NaiveRegAlloc`.

