# Part IV : Register Allocation


The goal of part IV is to replace the naive register allocator by a proper register allocator based on graph colouring. 
As seen in the lecture, you will have to:
  1. build a control-flow graph;
  2. implement liveness analysis; and
  3. implement Chaitin's graph colouring algorithm.

At the high-level, you should implement the class `GraphColouringRegAlloc`, taking inspiration from the naive register allocator class.

Once implemented, you can run your compiler with the new register allocator using the `-gen colour` option.

> **Marking methodology:** the marking will be done using a series of automated tests.
> The automated tests will check that your register allocator:
> * allocates the expected number of architectural registers to virtual registers, and
> * respects program semantics.
>
> We will verify these properties via automated tests.
> Your mark will be a function of the number of tests your compiler compiles correctly, with a number of memory accesses no larger than our gold standard (+5%).
>
> Specifically, we will feed assembly programs with virtual registers and `pushRegisters`/`popRegisters`
> pseudo-instructions to your compiler.
> We will check that this assembly program behaves as expected and contains the expected number of memory accesses (within 5% of our gold standard).
> Memory accesses allow us to measure whether your register allocator is spilling excessively or not.

## 1. Building the Control-Flow Graph (CFG)

Your first task consists of taking the assembly program produced by your code generator (with virtual registers) and transforming each function into a control flow graph.
You should assume that the instructions corresponding to each function are contained in a single text section (i.e. the text sections delimit the functions).

Although a production compiler would use the concept of basic blocks, the use of basic blocks is not necessary as seen in class.
We suggest that you keep a simple approach where each node of the CFG contains a single instruction and do not use basic blocks.

For this task, you should write your own directed graph data structure.
In order to verify that you have built the CFG correctly, you may want to write a pass that outputs the CFG as a [DOT](https://graphviz.org/doc/info/lang.html) graph.

> **Reminder:** You're not allowed to use external libraries.
> All code you submit should be written by you and you alone.
> Do not copy/paste from the web.

## 2. Liveness Analysis

Once you have correctly built the CFG, your next task is to implement liveness analysis.
As seen during the lectures, you should implement this as a simple fixed-point algorithm.
For this project, it is okay to process the nodes in any order (no need to sort them).
However, if you want to ensure a faster convergence time, you should compute the LiveOut set before the LiveIn set as seen in the lecture.

The output of the liveness analysis should be a liveIn and liveOut set for each node in the CFG.

Please note that there is special corner case that you need to handle when an instruction defines a register that is never used.
In such case, you must either:
    * remove this "dead" instruction;
    * or you must add the register defined in the liveOut set of the corresponding node (but just once at the end of calculating liveness).

Without doing one of these, your allocator will produce broken code. 

## 3. Interference Graph

Once you have performed liveness analysis, your next step should consist of building the interference graph as seen in the lecture.
The interference graph is an undirected graph.
You should implement your own data structure to represent it.
A node of the interference graph represents a single virtual register.
Two nodes are connected if there exist a liveIn or LiveOut set where both corresponding virtual registers are present (there is a point in the program execution where both virtual registers are alive at the same time).


## 4. Graph colouring with Chaitin algorithm

Your next job is to implement Chaitin's algorithm for graph colouring as shown in the lecture.
For this task, your register allocator can only use the following set of MIPS registers when mapping virtual registers: `$t0-9` and `$s0-s7`.
Your allocator should not make any distinctions between the `$s` and `$t` registers (we are deviating from the MIPS convention here and treat all these registers in the same way).
Note that your code generator is of course free to use the other classical MIPS registers such as `$fp` or `$sp`, but the virtual registers should only be mapped to the registers specified above.

> **Hint:** Contrary to what the lecture slides might suggest, you may not want to actually remove nodes from the graph when adding them to the stack as this would complicate your job.
Instead, we suggest that you simply add them to the stack. 
When you request the list of neighbours from a node, you can simply discard the nodes from that list that appear on the stack.
 

### Spilling

Your allocator should support the ability to spill registers when it is not possible to find a node in the graph with fewer than k neighbours.
As seen in the lectures, there are several approaches you can take.
We suggest that you start by implementing the simple approach which reserves a fixed set of registers to be used for spilling.
You should never need more than two registers for spilling since this is the maximum number of operands a MIPS instruction can have.
When you need to spill a node, simply remove it from the graph and add it to a set of nodes to be spilled.

Once the naive approach is implemented, a next improvement could consist of first trying to allocate the registers optimistically using all allowed registers and assuming no spilling will be necessary.
Next, if spilling is required, you simply restart the process with the two registers set aside for spilling.

Finally, a more advanced scheme would not reserve any architectural registers, but instead use two virtual registers to implement spilling.
So the advanced approach would try to allocate first.
Then, if spilling is required, one variable would be selected to be spilled and the assembly program would be rewritten using virtual registers to spill that one variable.
The resulting rewritten assembly would still only contain virtual registers.
The process is then repeated until there is no need for spilling.
At this point the actual register allocation would take place.

 

## 5. Output produced

Your implementation of the algorithm should produce two results: a set of virtual registers to be spilled (may be empty) and a mapping of virtual registers to architecture registers (`$t0-9`, `$s0-s7`).
Using these two outputs, you should then go back to the list of instructions and patch it up to replace each virtual registers with either an architectural register, or with a load/store instruction if the virtual register is spilled.
You should also at this point expand the two pseudo-instructions `pushRegisters` and `popRegisters`.

You will find it useful to reuse some of the existing logic from the `NaiveRegAlloc` when performing this step.
As already mentioned, you should never need more than two registers when spilling with your register allocator.


### Spilled registers
Important: you should not put any logic related to spilling in the `pushRegisters` and `popRegisters` pseudo-instructions.
There is no guarantee that these pseudo-instructions will be present in a function at all (e.g. in the case where the function does not perform any call, there is no need to push/pop the used registers).
Furthermore, the calling convention used by the tests might be different from the one in your compiler, and the stack pointer might move during the program's execution.
While a real compiler would spill registers on the stack, in the context of this coursework, you must use the naive approach for spilled registers and allocate them statically instead of allocating them on the stack.
Note that the `la` and `li` instructions do not count as a memory accesses when testing.






## Debugging Tip: Promote variables to registers

In production compilers, challenging situations for the register allocator typically arise from the use of local variables.
Most local variables are assigned to virtual registers rather than memory by real-life compilers.
Keeping local variables in registers helps reduce memory accesses, but increases register pressure in intricate ways that differ from the register use patterns emerging from temporary values in expressions.

The compiler we used to produce test assembly programs also keeps local variables in registers.
The register use patterns resulting from this variable-to-register promotion are something your register allocator must be able to handle.

You do not need to alter your register allocator for this purpose.
The algorithms from the lectures are sufficiently general.
However, some variable-to-register promotion may trigger bugs in your register allocator that would not have otherwise occurred.

To find these bugs, you **may** want to update your compiler to store eligible local variables in virtual registers rather than on the stack.
You should do this for every **local** variable, except when:

* the variable is of type struct or array;
* the variable is a function parameter; or
* the variable is used with an addressOf operator.

In all those cases, the allocation should be done on the stack as usual.

We suggest that you implement a pass that runs before code generation starts to identify all the local variables that should be stored in registers.
Then, simply modify your code generator to allocate each identified local variables in a virtual register.

Example:
```c
struct pt_t {
  int x;
  int y;
}

int global_i;    // statically allocated

int foo(int a     /* stack allocated */) {
  int i;          // register allocated
  int j;          // stack allocated (used in addressOf operator)
  int arr[10];    // stack allocated (it is an array)
  struct pt_t pt; // stack allocated (it is a struct)
  int * ptr;      // register allocated
  ...
  ptr = &j;
  ...
}
``` 

> **Important:** Variable-to-register promotion is *optional.* We will not evaluate this part of the assignment. We simply recommend it as a way to battle-test your register allocator.



## New Files

* `gen.regalloc.GraphColouringRegAlloc.java` is the new class you should implement.
* `Main4.java` has been updated from Main 3 to provide a new commandline argument to call the graph colouring register allocator.
