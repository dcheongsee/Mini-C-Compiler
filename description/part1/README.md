# Part I : Parsing
The goal of part I is to write a lexical and syntactic analyzer - a parser - for a subset of C; mini-C.
Parsing actually involves three steps:

1. Scanning: the job of the scanner is to read the input file one character at a time.
2. Lexing: the lexer transforms the stream of characters into a stream of tokens. These tokens represent the lexems.
3. Parsing: the parser finally consumes the tokens and determines if the input conforms to the rules of the grammar.

The scanner has already been implemented and there is some partial implementations of the lexer and parser (courtesy of Prof C. Dubach).
We write a recursive descent parser and as such make our grammar LL(k).
Utility functions have been provided in the parser class to allow look ahead.


## 0. C language
Before starting implementing the compiler, a strong familiarisation with C is required.
Since our target language, mini-C, is a subset of C, it is essential to know how to use a C compiler such as gcc.
In case of doubts about the language syntax and semantics when implementing the compiler, a good default is to use the same syntax and semantics as the C language.
This [web tutorial](https://www.tutorialspoint.com/cprogramming/index.htm) is a good starting point to brush up my C skills, and contains most of the information I will need.
Since we are only targeting a subset of C, we do not have to read all of it but only the parts that correspond to our target language (which is described by the EBNF grammar). 


## 1. Lexing
The file `Tokeniser.java` contains a partial implementation of a lexer.
In particular, we have to complete the implementation of the method `nextToken` in the `Tokeniser`-class.
It is strongly recommended that we fill in the missing details, rather than implementing our own `Lexer` from scratch.
The signature of the `nextToken` method is not to be removed or changed.

### Tokens' definition

The tokens that the lexer needs to recognise with the regular expression definition are given in the file `Token.java`.
When implementing the Lexer, I should ensure that you follow *strictly* the given definition of each token category.
Note that we do **not** use the [the Java regular expression matcher](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Matcher.html) in our implementation!

### Comments
Please note that comments are treated as whitespace, and they do not have any associated tokens.

Comments can either be single line:
`// this is a comment`
or multiline
`/* this is a
    multiline comment */`.
Note that `/*` always indicate the start of a comment.

The following code contains the start of a comment (and would be incorrect since the comment never ends):
```
int * p;
2/* p;
```
While the following code does not contain a comment and, therefore, the lexer should recognize it as valid:
```
int * p;
2/ * p;
```

### Include directive

The "#include" directive is tokenised into the INCLUDE token.
However, nothing is to be done with the "include" preprocessor directive (it is completely ignored in the rest of the compiler).

### Numbers
When it comes to number, we only consider integer number and we do not support hexadecimal or octal numbers.

### Escaped characters
The list of characters to escape are given in the Token class.
For instance, `"I am a \"string\""` should return a string token whose data field is `I am a "string"`.
Given this example, the length of the String in the data field should be exactly 15.

If an unknown escaped character appears, this is reported as an error.


### Tips:

* When checking for the presence of a letter or digit, we convert the value of the char to an integer and simply check that the value falls within the expected range.
The integer value corresponding to each letter or digit can be found in the ASCII table (e.g. 'a' has value 97, 'b' has value 98). 
* The data field of most tokens will be empty (e.g. when the category is LPAR, PLUS), except when the token actually carries information (e.g. when the category is INT_LITERAL)

## 2. Grammar transformation to LL(k)

Once we have written the lexer, we are almost ready to move on to the parser stage.
However, we must first transform the [grammar](./grammar/syntax_grammar.txt) given into an equivalent context-free LL(k) grammar.
This is a requirement for implementing a recursive descent parser.

We can keep some parts of the grammar in EBNF form and implement part of the parser in an iterative style where it makes sense to do so (e.g. function call arguments).
However, we ensure that we eliminate left-recursion from the grammar and, if needed, left-factorize it.
This is done by modifying the grammar slightly.



## 3. Parser
After having transformed the grammar into a LL(k)-grammar and implemented the lexer we implement a parser.
The parser determines whether a given source program is syntactically correct or incorrect.
A partial implementation of a recursive-decent parser has been provided.
The provided `Parser`-class has the following interface:

* `void parse()` initiates the parsing of a given source program.

In addition, the `Parser`-class contains various private methods, of which some are utility methods, e.g.

* `void error(Category... expected)` takes a variable number of expected token's categories, and emits an error accordingly.
* `Token lookAhead(int i)` returns the `i`'th token in the token-stream.
* `void nextToken()` advances the token-stream by one, i.e. it consumes one token from the stream.
* `void expect(Category... expected)` takes a variable number of expected token's categories, and consumes them from the token-stream if present, otherwise it generates an error using the `error`-method.
* `boolean accept(Category... expected)` tests whether the next token(s) are identical to the `expected`. However, it *does not* consume any tokens from the token-stream.
* `void parseProgram()` parses a "Program-production" from the LL(k) grammar. Similarly, `void parseIncludes()` parses an "Includes-production". Three additional empty methods have been provided: `parseStructDecls`, `parseVarDecls` and `parseFunDecls` are to be completed by you. Furthermore, you will need to add more parse methods yourself. For each nonterminal you should have a corresponding parse method.

Our parser *should* only determine whether a given source program is syntactically correct or not.
The `Main1`-class relies on the error count provided by the `Parser` superclass.
Therefore, we use the `error`-method in the `Parser`-class to report errors correctly!

## Files
* grammar/syntax_grammar.txt : This file describes the grammar of our mini-C language in EBNF format.
* Scanner : This class implements the scanner which returns character strings.
* Token : This class represent the different tokens from the language.
* Tokeniser : This class converts character strings into tokens.
