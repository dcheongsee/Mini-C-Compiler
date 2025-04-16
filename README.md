# Mini-C Compiler

> From clean source to optimized MIPS32 — a complete teaching-grade toolchain built in **pure Java** (≈ 14 k LOC).

---

### Why this project matters
Mini-C is a non-trivial subset of ISO C, rich enough to exercise real-world compiler design yet small enough to implement solo. 
This repo shows the **entire** journey — scanner, LL(k) parser, AST, static-semantic passes, MIPS32 codegen, and a Chaitin-style register allocator — implemented, documented, and battle-tested.

---

## Pipeline at a glance

| Stage                                         | Key ideas                                                                                                                                                                | Files / Packages |
|-----------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------|
| **1 · [Parsing](description/part1/)**         | Recursive-descent over an LL(k) grammar; hand-rolled lexer; comments & escape-sequence handling                                                                          | `src/lexer/`, `src/parser/`, `grammar/syntax_grammar.txt` |
| **2 · [AST + Semantics](description/part2/)** | Structural AST (`ast.*`) built on the fly; name analysis with lexical scopes; type checker driven by formal rules in `rules.tex`; l-value & control-flow legality checks | `src/ast/`, `src/sem/` |
| **3 · [Code Gen](description/part3)**         | Emits virtual-register MIPS32; handles structs, arrays, pointer arithmetic, short-circuit Boolean logic; standard library in syscalls                                    | `src/gen/`, `gen/asm/` |
| **4 · [Reg Alloc](description/part4)**        | CFG → liveness → interference graph → Chaitin colouring; deterministic spill heuristic (< 5 % mem-access delta vs. gold standard)                                        | `src/regalloc/GraphColouringRegAlloc.java` |
| **5 · Runtime**                               | Thin libc (`print_i`, `read_c`, `mcmalloc`, …) plus ≈ 60 regression tests                                                                                                | `tests/`, `minic-stdlib.h` |

# Frontend
![Frontend](/figures/frontend.png "Frontend Diagram")

# Backend
![Backend](/figures/backend.png "Backend Diagram")

---

## Quick start

```bash
# 1 · Compile the compiler (Java 17+)
./gradlew build            # or: javac -d bin $(find src -name '*.java')

# 2 · Compile a Mini-C source to assembly
java -cp bin Main4 -gen colour tests/fibonacci.c fib.s

# 3 · Run in MARS
java -jar Mars4_5.jar sm nc me fib.s
```

> **Tip:** `Main1`, `Main2`, `Main3`, `Main4` select the pipeline up to parsing / semantics / code-gen / reg-alloc respectively.

---

## Language snapshot

* **Types** — `int`, `char`, `void`, pointers, fixed-size arrays, user `struct`s
* **Expressions** — full C precedence, unary/binary ops, short-circuit `&& / ||`, casts, `sizeof`
* **Statements** — blocks, `if/else`, `while`, `break/continue`, `return`
* **Functions** — prototypes & definitions, arity/type checked
* **Std-lib** — printing, input, and `mcmalloc` for heap experiments

Formal EBNF and typing rules live in `grammar/` and `typing-rules/`.

---

## Internals worth a peek

* **Hand-tuned lexer** — no regex engine; pure ASCII-table arithmetic
* **Zero-boilerplate passes** — every `ASTNode` exposes `children()` → generic DFS utilities stay one-liners
* **Data-aligned struct layout** — respects 1/4-byte alignment with recursive-pointer rules
* **Graph colouring** — deterministic spills; never needs more than two scratch registers
* **Self-diagnosing tests** — each part ships with focused unit programs; failures print both AST and typed IR for rapid diffing.

---

## Repository map (rough)

```text
.
├── grammar/               # Concrete & abstract grammars
├── typing-rules/          # LaTeX PDF of formal typing system
├── src/
│   ├── lexer/             # Scanner + tokeniser
│   ├── parser/
│   ├── ast/
│   ├── sem/               # Name & type analysers
│   ├── gen/               # Code generator
│   └── regalloc/          # GraphColouringRegAlloc
└── tests/                 # Sample Mini-C programs (See detailed tests in src/testsX)
```

---

## Building blocks & dependencies
* Pure **Java 17** standard library.
* No external frameworks, ANTLR or LLVM backends.
* **MARS 4.5** (bundled JAR) for runtime inspection

---

## Roadmap & ideas

* SSA & local value numbering mid-end.
* Dead-code elimination before codegen.
* Peephole optimiser post register allocation.
* Port back-end to RISC-V RV32I for hardware labs.

---

## License

MIT — use, learn, iterate. Acknowledgements to Prof. **C. Dubach** for skeletons and test suites.

---

*Built incrementally over five milestones — every bug squashed by hand, and every optimisation measured. Enjoy exploring the source!*
