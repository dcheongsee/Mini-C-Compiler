# Part II : AST builder + Semantic Analyser

The goal of part II is to implement the rest of the front-end all the way to semantic analysis.
This will involve modifying your parser so that it can build the Abstract Syntax Tree (AST) corresponding to your the input program and then perform semantic analysis.

In order to achieve this goal, you will have to perform several tasks.
First, you will have to follow the abstract grammar specification and design the Java classes that represent the AST as seen during the course.
Then, you should write an AST printer in order to output the AST into a file.
Thirdly, you will have to modify your parser so that it builds the AST as your are parsing the tokens.
Finally, you will be able to perform semantic analysis.

Note that we highly recommend following an iterative approach where you add AST nodes one by one, extend the printer and modify your parser as you go.
We also encourage you to write small test programs that test every AST node as you build them rather than trying to implement everything at once.
If you encounter any problem, have any questions or find a bug with the newly provided files, please post a note on the online forum.


## Tips

 * Don't try to support the whole language at once, start small with a subset of the grammar.
  For instance, starts with a subset of the grammar that could just handle a main function returning 0, massage the grammar, add the AST nodes and update your parser.
  Then grow from there by supporting step by step a larger subset of the grammar.
 * Each analysis pass you write should have only a single purpose.
   It is much easier to write many small passes, each performing a simple task, than one big one trying to do everything at once (for instance write one pass just for checking assignment).
   Do not worry about efficiency, even if your compiler requires the application of 100 passes to reach its goal, this is absolutely fine and will make your task of writing the compiler easier.
 * Make use of the debugger from your IDE to chase bugs.
   If you have never use a debugger before, now is the time to learn: [IntelliJ debugger tutorial](https://www.jetbrains.com/help/idea/debugging-your-first-java-application.html)




## 0. Setup

You will have to pull the AST class nodes and abstract grammar from the main repository.

:warning:
If you wish to start Part 2 before the deadline of Part 1, execute the following commands in a separate branch!
Otherwise, if you merge with your master branch and by accident push your changes to your remote repository, the auto-testing for Part 1 will fail.
You must first create a new branch in your repository and switch to that branch (instructions are given below).
:warning:

We advise you to first read this [tutorial](https://www.atlassian.com/git/tutorials/using-branches) about managing branches in git.
First, open a terminal and navigate to the root of your local repository.

Note that a new main file, `Main2.java` is provided.
You should use this class as the main entry point to the compiler from now on.

### 0.1 Creating separate branch (if starting before deadline for part 1)
If you are starting part 2 after the deadline for part 1 you can skip to 0.2.

If you are starting part 2 before the deadline for part 1, we will first create a new local branch.
Type:
```
$ git branch part2
```

Then, let's checkout the new local branch:
```
$ git checkout part2
```

And finally, let's push the branch to your git remote repository:
```
$ git push -u origin test
```

From now on, whenever you commit or push, this will happen in your newly created part2 branch.

### 0.2 Bringing in new updated skeleton compiler code

We are now ready to bring in the new skeleton code from the **instructor** (cdubach) repository.

Type:
```
$ git pull git@gitlab.cs.mcgill.ca:cdubach/comp520-coursework-w20XX.git
```
where XX is this year (e.g. 22 for 2022).
This will cause some merge conflict(s) due to the change of the return type of some of the parse functions to return an AST node instead of void.
For instance:
```
From gitlab.cs.mcgill.ca:cdubach/comp520-coursework-w20XX
 * branch            HEAD       -> FETCH_HEAD
Auto-merging src/parser/Parser.java
CONFLICT (content): Merge conflict in src/parser/Parser.java
```
where XX is this year (e.g. 22 for 2022).
Here, the file Parser.java is causing a merge conflict.
In order to resolve it, you should open the file to fix the conflict.
For the parser, you'd possibly want to remove everything between the equals symbols and the greater than symbols, e.g.
```
    =======
    public Program parse() {
    >>>>>>> 92a7665c3dde600e1bd2d5681b2fc8fb43e1d37b
```
Thereafter you can continue to extend your solution.
You can safely commit and push your changes as these will be pushed on the branch part2.

### 0.3 Merging your changes into your master branch (if started before deadline for part 1)

If you have created a new local branch for part 2, and **once the deadline for part 2 has passed**, it is time to bring back these changes into your master branch.
This will ensure that the automarker will be able to test your part 2.
Make sure to have committed all your changes from your local branch before executing the following.

To come back to the master branch, type:
```
git checkout master
```

Then, you can merge your changes from your local branch part2 into master as follows:
```
git merge part2
```

From that point on, you are back in working onto master.
When you will push any of your changes from master to your remote repository, these will be picked up by the automarker.

If you encouter any issues with this, please make sure to post on the discussion forum or attend the TA office hours.



## 1. Massaging the grammar (Operator precedence and associativity)

As seen in the lecture, when building the AST it is important to ensure that the correct operator precedence and associativity rules are followed.
To this end, you should start again from the initial concrete syntax grammar and update it.

You should make sure the resulting grammar is non-ambiguous, eliminate left recursion and ensure that the usual C precedence and associativity rules for operators are respected based on the table below.

As see in the lecture, left-associative binary operators should be handled using an iterative approach in the parser (rather than recursion).
We suggest that you express these in the grammar using a Kleene closure, which will directly translate to a loop in your parser code.


| Precedence |Operator       | Description       |Associativity  |
|:-----------| :------------ | :-----------      | :-----------  |
| 1          | ()            | Function call     | Left-to-right |
| 1          | \[\]          | Array subscripting | Left-to-right |
| 1          | .             | Structure member access | Left-to-right |
| 2          | +             | Unary plus | Right-to-left |
| 2          | -             | Unary minus | Right-to-left |
| 2          | (type)        | Type cast | Right-to-left |
| 2          | *             | Indirection | Right-to-left |
| 2          | &             | Address of | Right-to-left |
| 3          | * / %         | Multiplication, division, remainder | Left-to-right |
| 4          | + -           | Addition, subtraction | Left-to-right |
| 5          | < <= > >=     | Relational operators | Left-to-right |
| 6          | == \!=        | Relational operators | Left-to-right |
| 7          | &&            | Logical AND | Left-to-right |
| 8          | ⎮⎮            | Logical OR | Left-to-right |
| 9          | =             | Assignment        | Right-to-left |

Here is how to "interpret" the following piece of C code based on precedence and associativity:
 
```C
array[1][2]       // (array[1])[2]
mystruct.field[1] // (mystruct.field)[1]
2*3+4             // (2*3)+4
2+3*4             // 2+(3*4)
&*ptr             // &(*ptr)
&p[1]             // &(p[1])
a+b+c             // (a+b)+c
a=b=c             // a=(b=c)
a=b.c=d           // a=((b.c)=d)
```

Note that associativity for unary operators seems at first a bit of an ill-defined concept.
However, it is still a useful concept that basically specifies whether the operator is used as *prefix* or *postfix*.
For instance, left-to-right associativity for function call tells you that if the following input were somehow valid syntactically (it is not in your language)
```C
foo()bar
```
then the call would be taking place on `foo` and not on `bar`.
However, for type casting, if the following were syntactically correct (it is not the case in our language):
```C
x(int)y
```
then the casting operator would be applied on `y` and not on `x`.


## 2. AST Nodes

As seen in the lecture, the AST is generally defined using an abstract grammar.
You can find the abstract grammar for our language [here](grammar/abstract_grammar.txt).
It is important to ensure that the design of your classes follows the abstract grammar;
the automated marking system will rely exclusively on the name of the class to determine the type of AST node and will expect the subtrees to appear in the same order as defined in the grammar file.

Note that a few AST node classes are already given as a starting point.
You should not have to modify these (unless otherwise stated), but you are free to do so if you wish.

### ASTNode class

Observe how the `ASTNode` class has an abstract `children()` method.
When implementing a new ASTNode, you should take care that `children()` returns all the nodes that are included in the current node (i.e. its children).
This will allow you to implement very simple analysis passes with only a few lines of code.

For instance, if you wanted to implement a function that prints the name of all variables every time they appear in the abstract syntax tree, you could simply do:

```java
public void printAllVariableUses(ASTNode node) {
    switch (node) {
        case null -> {
            throw new IllegalStateException("Unexpected null value");
        }

        case VarExpr ve -> {
            System.out.println(ve.name);
        }

        case ASTNode n -> {
            for (ASTNode child: n.children()) {
                printAllVariableUses(child);
            }
        }
    }
}            
```
This will become handy when, for instance, you will have to check that variable are declared before usage.

#### Tips
 Note that in this example, we do not want to visit the children of `VarExpr`. 
 However, there might be cases where you may want to do so, and you should not forget to do it when needed!

## 3. Parser modifications

Your next task major consists in updating your parser so that it creates the AST nodes as it parses your input program.
For most of your parseXYZ methods, you will have to modify the return type to the type of the node the parsing method should produce as seen during the lecture and implement the functionality that builds the AST nodes.
You may have to modify slightly the design of your parser in order to accommodate the creation of the AST nodes.


## 4. AST Printer

Your next job consists in extending the AST printer class provided to handle your newly added AST node classes.
As seen during the course, the AST printer will use pattern matching.

It is important to respect the following format when printing the AST to ensure that your output can be validated by our automatic marking system.
Using EBNF syntax, the output should be of the form: `AST_NODE_CLASS_NAME '(' [SUB_TREE (',' SUB_TREE)*] ')'`
Except for `Op` and `BaseType`, which should be Java enums, all AST nodes printed must be followed by an opening and corresponding closing parenthesis (even if there is no children).

### Examples:

* `y = 3*x;` should result in the following output: `ExprStmt(Assign(VarExpr(y),BinOp(IntLiteral(3), MUL, VarExpr(x))))`.
* `void foo() { return; }` should result in: `FunDef(VOID, foo, Block(Return()))`.
* `+x` should result in just `BinOp(IntLiteral(0),ADD,VarExpr(x))`
* `-x` should result in: `BinOp(IntLiteral(0),SUB,VarExpr(x))`.
* `-x*3` should result in: `BinOp(BinOp(IntLiteral(0),SUB,VarExpr(x)),MUL,IntLiteral(3))`.
* `-1` should result in `BinOp(IntLiteral(0),SUB,IntLiteral(1))`.
* `2+3+4` should result in `BinOp(BinOp(IntLiteral(2), ADD, IntLiteral(3)), ADD, IntLiteral(4))`  (all binary operators are left associative in our language)
* `2+3*4` should result in `BinOp(IntLiteral(2), ADD, BinOp(IntLiteral(3), MUL, IntLiteral(4)))`  (multiplication has precedence over addition, see precedence table)
* `struct node_t { int field1; char field2; };` should result in `StructTypeDecl(StructType(node_t),VarDecl(INT,field1),VarDecl(CHAR,field2))`
* `struct node_t n;` should result in `VarDecl(StructType(node_t), n)`

Note that you are free to add white spaces in your output format; spaces, newlines and tabulations will be ignore by our comparison tool.

See the file [fibonacci.c-ast-dump](./fibonacci.c-ast-dump) for an example output of `java -cp bin Main2 -ast tests/fibonacci.c fibonacci.c-ast-dump`.

Note that we represent the `-` and `+` unary operators using a `BinOp` add/sub AST node with `0` as first argument.

## 4'. Dot Printer (Optional)

As seen during the lectures, it might be a good idea to also implement a Dot printer in order to easily visualise your AST.
This task is completely optional and will not be marked, but it might help you find problems more easily. 



## 5. Name Analysis
 
The goal of  name analysis is to ensure that the scoping and visibility rules of the language are respected.
This means for instance ensuring identifiers are only declared once or that any use of an identifier is preceded by a declaration in the current or enclosing scope.

Please note that an identifier can either be a variable or a function.

### Global and local scopes

As seen during the lectures, our language only have two scopes: global and local.

The global scope corresponds to the global variables declared outside any procedure and for the procedure declarations. Identifiers declared in the global scope can be accessed anywhere in the program (as long as their declaration preceed their use).

The block scope (or local scope) is a set of statements enclosed within left and right braces ({ and } respectively).
Blocks may be nested (a block may contain other blocks inside it).
A variable declared in a block is accessible in the block and all inner blocks of that block, but not accessible outside the block.
Function parameter identifiers have block scope, as if they had been declared inside the block forming the body of the procedure.

In both cases (global or local), it is illegal to declare twice the same identifiers in the same current block (note that this means it is illegal to declare a variable with the same name as a procedure at the global level).

Special care must be taken in any struct definition since it is not allowed to declare twice the same field.
For instance the following is invalid:
```C
struct foo_t {
  int bar;
  int bar;
}
```

### Shadowing

Shadowing occurs when an identifier declared within a given scope has the same name as an identifier declared in an outer scope.
The outer identifier is said to be shadowed and any use of the identifier will refer to the one from the inner scope.

### Function declarations and definitions

A function name can only be used once for a function definition, and once for a function declaration (if any).
Declaring two function definition, or two function declaration, with the same name, is not legal.
Every function declaration must have a corresponding function definition (i.e. it is illegal to only have a function declaration).

Before a function is allowed to be call, a function declaration or definition must have been defined first.

If both a function definition and function declaration with the same name exists, there must have identical types (return type and arguments' type).


### Built-in functions

As you may have noticed in the previous part, our language supports a set of built-in functions which are defined as parts of our standard library: 

```C
void print_s(char* s);
void print_i(int i);
void print_c(char c);
char read_c();
int read_i();
void* mcmalloc(int size);
```

In order to recognise any call to these functions as valid, we suggest that you simply add dummy declaration for each of these (with an empty body) to the list of defined functions into the Program AST node.
Please note that it is important to do this just before name analysis but after having printed the AST so that our automatic tests do not fail (we are not expecting to see these built-ins function in the AST when checking for the AST correctness). 



### Actual Task

Your task is to implement a pass that traverses the AST and identifies when the above rules are violated.
In addition, you should add (and fill in), for the FunCallExpr and VarExpr AST nodes, a field referencing the declaration or definition (either a FunDefinition or VarDecl).
This field should be updated to point to the actual declaration/definition of the identifier when traversing the AST with the name analysis pass.
This establishes the link between the use of a variable or function and its declaration/definition as seen during the lectures.


## 6. Type analysis

The goal of type analysis is to verify that the input program is well-typed and assign a type for each expression encountered.
As seen during the course, the typing rule of our miniC language are defined using a formal notation.
You can find all the typing rules [here](./typing-rules/rules.pdf).
As usual, if you notice an error or if something is not clear, please post your question on the online forum.

Your task consists of extending the `sem.TypeAnalyzer` class and implement the type checking mechanism following the typing rules.

### Arrays

The elements of an array can be of any type, except `void`.
For instance, the following two declarations are illegal:
```C
void array[10];
void array[2][3];
```

When checking for type equivalence for arrays, it is important to ensure that their lengths match.
However, an access to an array that is out-of-bound, does not constitute a typing error.
The following code is perfectly valid as far as type analysis is concerned (although it is likely to crash at runtime or produce an unexpected result):
```C
int foo(int a[3]) {
   return a[99]; // this is fine
}
int bar() {
    int b[3];
    return foo(b);
}
```
While the following example is invalid:
```C
int foo(int a[3]) {
   return a[1]; 
}
int bar() {
    int b[6];
    return foo(b); // foo expects an array of size 3, not 6
}
```

### Structures

Structure declaration are represented in the AST as StructTypeDecl.
The type analysis pass must ensure that each structure declaration has a unique name.
You can enforce this by creating a simple pass which checks for this before running the type checker for instance.

Similarly to the function call and variable use, your type analyser needs to check that if any variable is declared with a struct type, the struct type exists.
For instance if you encounter a variable declaration such as `struct node_t var;`, you must ensure that the corresponding `node_t` structure has been declared earlier.

Note that structure can only be defined recursively when using a pointer.
So the following example is valid:
```C
struct x_s {
    int a;
    struct x_s * s; // recursive reference via a pointer is fine
}
```
while the following example is invalid:
```C
struct x_s {
    int a;
    struct x_s s; // recursive reference without a pointer is invalid
}
```
For the last example, the issue is that it is impossible to tell how large this data structure is due to the recursive definition.
Hence, this should be rejected by the compiler during semantic analysis.

Since the fields of a structure are variable declaration, they cannot be of type `void` as seen in the typing rules.

Finally, when accessing a structure, you must also check that the field exist in the structure type declaration.


### String literal

String literals are represented in our language as null terminated char arrays.
The string literal `"Hello"` should therefore be of type `char[6]` holding characters `'H'`,`'e'`,`'l'`,`'l'`,`'o'` and `'\0'` where `\0` represents the null character.

### Strong typing

Our language is strongly typed.
This means that there are no implicit casts between expressions and the cast must be explicit.
For instance the following code is invalid in our language:
```C
int i;
char c;
i=c;
```
To make this valid, an explicit cast operation must be performed.
The following is valid:
```C
int i;
char c;
i=(int)c;
```

## 7. Checking lvalues

Finally, your last task will consist in checking that *lvalues* (left-values) are the only expressions that can appear on the left-hand side of an assignment (`=`) or as an argument to the *address-of* operator (`&`). 
Intuitively, an *lvalue* represents some identifiable memory location that has a corresponding address. 

In our language, the expression that can produce *lvalues* are:

   * VarExpr;
   * FieldAccessExpr;
   * ArrayAccessExpr;
   * ValueAtExpr.
 
Note that to be an *lvalue*, a FieldAccessExpr, ArrayAccessExpr and ValueAtExpr must have an *lvalue* as their first expression.
I.e. the struct, array or pointer accessed must be an *lvalue*.

Example of legal code:
```C
int i;
int* p;

i  = 0;
*p = i;
p  = &i;
```
whereas the following code shows examples of invalid cases:
```C
int i;
i+2=3; // invalid: i+2 is not an lvalue
&3;    // invalid: 3 is not an lvalue
...
foo().a=i; // invalid: foo() is not an lvalue, therefore foo().a is not an lvalue
```

## 8. continue/break

The `continue` and `break` statements can only be used within a loop.


## New Files
* desc/part2/grammar/abstract_grammar.txt : This file describes the abstract grammar that defines our AST.
* src/java/Main2 : Entry point to the compiler for part 2
* src/java/ast/ASTPrinter.java : This the AST printer pass.
* src/java/ast/\*.java : These files implement some of the AST nodes.
* src/java/sem/\*.java : These files providing you with a starting point to implement semantic analysis.
* desc/part2/typing-rules/rules.pdf : This file specify the typing rules for our language.
* desct/part2/fibonacci.c-ast-dump: This file shows you what the AST output should look like for the fibonacci test program.

A new package has been added under `src/java/sem/`. This package contains template classes to get you started on implementing the semantic analysis.

 * The `sem.SemanticAnalyzer` is the only class which `Main.java` directly interfaces with for semantic analysis. Inside this class you should run all of your semantic passes.
 * The `sem.NameAnalyzer` is a template for the name analysis.
 * The `sem.TypeAnalyzer` is a template for typechecker.
 * The `sem.Symbol` is an abstract parent class for other concrete symbols (e.g. variables and procedures).
 * The `sem.Scope` is a partial implementation of the `Scope`-class discussed in the lectures.
 * The `typing-rules.pdf` contains all the typing rules for our language


## Updated Files
* src/parser/Parser.java : The parseXXX methods' signatures have been updated.
