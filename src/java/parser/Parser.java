package parser;

import ast.*;
import lexer.Token;
import lexer.Token.Category;
import lexer.Tokeniser;
import util.CompilerPass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * @author cdubach
 */
public class Parser extends CompilerPass {

    private Token token;

    private Queue<Token> buffer = new LinkedList<>();

    private final Tokeniser tokeniser;



    public Parser(Tokeniser tokeniser) {
        this.tokeniser = tokeniser;
    }

    public Program parse() {
        // get the first token
        nextToken();

        return parseProgram();
    }



    //private int error = 0;
    private Token lastErrorToken;

    private void error(Category... expected) {

        if (lastErrorToken == token) {
            // skip this error, same token causing trouble
            return;
        }

        StringBuilder sb = new StringBuilder();
        String sep = "";
        for (Category e : expected) {
            sb.append(sep);
            sb.append(e);
            sep = "|";
        }
        String msg = "Parsing error: expected ("+sb+") found ("+token+") at "+token.position;
        System.out.println(msg);

        incError();
        lastErrorToken = token;
    }

    /*
     * Look ahead the i^th element from the stream of token.
     * i should be >= 1
     */
    private Token lookAhead(int i) {
        // ensures the buffer has the element we want to look ahead
        while (buffer.size() < i)
            buffer.add(tokeniser.nextToken());

        int cnt=1;
        for (Token t : buffer) {
            if (cnt == i)
                return t;
            cnt++;
        }

        assert false; // should never reach this
        return tokeniser.nextToken();
    }


    /*
     * Consumes the next token from the tokeniser or the buffer if not empty.
     */
    private void nextToken() {
        if (!buffer.isEmpty())
            token = buffer.remove();
        else
            token = tokeniser.nextToken();
    }

    /*
     * If the current token is equals to the expected one, then skip it, otherwise report an error.
     */
    private Token expect(Category... expected) {
        for (Category e : expected) {
            if (token.category == e) {
                Token ret = token;
                nextToken();
                return ret;
            }
        }
        error(expected);
        return token;
    }

    /*
     * Returns true if the current token is equals to any of the expected ones.
     */
    private boolean accept(Category... expected) {
        for (Category e : expected) {
            if (e == token.category)
                return true;
        }
        return false;
    }


    private Program parseProgram() {
        parseIncludes();
        List<Decl> decls = new ArrayList<>();

        // updated to accept CLASS as well as the original categories
        while (accept(Category.STRUCT, Category.INT, Category.CHAR, Category.VOID, Category.CLASS)) {
            if (token.category == Category.STRUCT &&
                    lookAhead(1).category == Category.IDENTIFIER &&
                    lookAhead(2).category == Category.LBRA) {
                // parse struct declaration
                decls.add(parseStructDecl());
            }
            else if (token.category == Category.CLASS) {
                // parse class declaration
                decls.add(parseClassDecl());
            }
            else {
                // parse either a function definition/declaration or a global variable declaration
                Type type = parseType();
                Token ident = expect(Category.IDENTIFIER);
                if (accept(Category.LPAR))
                    decls.add(parseFunction(type, ident));
                else
                    decls.add(parseVarDeclSuffix(type, ident));
            }
        }
        expect(Category.EOF);
        return new Program(decls);
    }

    // includes are ignored, so does not need to return an AST node
    private void parseIncludes() {
        if (accept(Category.INCLUDE)) {
            nextToken();
            expect(Category.STRING_LITERAL);
            parseIncludes();
        }
    }

    private StructTypeDecl parseStructDecl() {
        expect(Category.STRUCT);
        Token id = expect(Category.IDENTIFIER);
        expect(Category.LBRA);
        List<Decl> fields = new ArrayList<>();

        do {
            fields.add(parseVariableDecl());
        } while (accept(Category.INT, Category.CHAR, Category.VOID, Category.STRUCT, Category.CLASS));
        expect(Category.RBRA);
        expect(Category.SC);

        return new StructTypeDecl(id.data, fields);
    }


     // parse a class declaration
    private ClassDecl parseClassDecl() {
        // we already know token.category == CLASS at this point
        expect(Category.CLASS);
        Token className = expect(Category.IDENTIFIER);

        // optional "extends" <IDENT>
        String parentName = null;
        if (accept(Category.EXTENDS)) {
            expect(Category.EXTENDS);
            Token parentIdent = expect(Category.IDENTIFIER);
            parentName = parentIdent.data;
        }

        expect(Category.LBRA);

        // parse zero or more variable declarations
        List<VarDecl> fields = new ArrayList<>();
        while (accept(Category.INT, Category.CHAR, Category.VOID, Category.STRUCT, Category.CLASS)) {
            if (lookAhead(2).category == Category.LPAR) {
                break;
            }
            fields.add(parseVariableDecl());
        }

        // parse zero or more function definitions
        List<FunDef> methods = new ArrayList<>();
        while (accept(Category.INT, Category.CHAR, Category.VOID, Category.STRUCT, Category.CLASS)) {
            Type type = parseType();
            Token ident = expect(Category.IDENTIFIER);
            expect(Category.LPAR);
            List<VarDecl> params = parseParameters();
            expect(Category.RPAR);

            Block block = parseBlock();
            FunDef method = new FunDef(type, ident.data, params, block);
            method.isInstanceMethod = true; // mark as instance method
            methods.add(method);
        }

        expect(Category.RBRA);

        // no semicolon after '}' for classes in our grammar

        return new ClassDecl(className.data, parentName, fields, methods);
    }

    private Decl parseFunction(Type type, Token ident) {
        expect(Category.LPAR);
        List<VarDecl> params = parseParameters();
        expect(Category.RPAR);
        if (accept(Category.LBRA)) {
            Block block = parseBlock();
            return new FunDef(type, ident.data, params, block);
        } else {
            expect(Category.SC);
            return new FunDecl(type, ident.data, params);
        }
    }

    // *new*,  returns a declaration AST node for a var
    private VarDecl parseVariableDecl() {
        Type baseType = parseType();
        Token ident = expect(Category.IDENTIFIER);
        Type finalType = parseArrayDimensions(baseType);
        expect(Category.SC);
        return new VarDecl(finalType, ident.data);
    }

    // those that appear after type and identifier
    private Decl parseVarDeclSuffix(Type type, Token ident) {
        Type finalType = parseArrayDimensions(type);
        expect(Category.SC);
        return new VarDecl(finalType, ident.data);
    }

    //  array dimensions in square brackets
    Type parseArrayDimensions(Type base) {
        List<Integer> dims = new ArrayList<>();
        while (accept(Category.LSBR)) {
            expect(Category.LSBR);
            Token intToken = expect(Category.INT_LITERAL);
            dims.add(Integer.parseInt(intToken.data));
            expect(Category.RSBR);
        }
        for (int i = dims.size() - 1; i >= 0; i--) {
            base = new ArrayType(base, dims.get(i));
        }
        return base;
    }


    // allows for 0 or more comma separated parameters
    private List<VarDecl> parseParameters() {
        List<VarDecl> params = new ArrayList<>();
        if (!accept(Category.RPAR)) {
            params.add(parseParam());
            while (accept(Category.COMMA)) {
                expect(Category.COMMA);
                params.add(parseParam());
            }
        }
        return params;
    }

    // single param in func decl
    private VarDecl parseParam() {
        Type baseType = parseType();
        Token ident = expect(Category.IDENTIFIER);
        // parse any array dimensions
        Type arrayType = parseArrayDimensions(baseType);
        // if param type is array, convert to pointer type and mark as an array parameter
        Type finalType;
        boolean isArrayParam = false;
        if (arrayType instanceof ArrayType) {
            finalType = new PointerType(((ArrayType) arrayType).elementType);
            isArrayParam = true;
        } else {
            finalType = arrayType;
        }
        VarDecl vd = new VarDecl(finalType, ident.data);
        vd.isArrayParam = isArrayParam;  // set the new flag
        return vd;
    }


    private Type parseType() {
        Type type;
        if (accept(Category.STRUCT)) {
            expect(Category.STRUCT);
            Token id = expect(Category.IDENTIFIER);

            type = new StructType(id.data);
        }
        // new branch: handle 'class' in a type
        else if (accept(Category.CLASS)) {
            expect(Category.CLASS);
            Token id = expect(Category.IDENTIFIER);
            type = new ClassType(id.data);
        }
        else {
            // expect one of int, char, void
            Token t = expect(Category.INT, Category.CHAR, Category.VOID);
            switch (t.category) {
                case INT:
                    type = BaseType.INT;
                    break;
                case CHAR:
                    type = BaseType.CHAR;
                    break;
                case VOID:
                    type = BaseType.VOID;
                    break;
                default:
                    type = BaseType.UNKNOWN;
                    break;
            }
        }

        // handle any pointer stars
        while (accept(Category.ASTERISK)) {
            expect(Category.ASTERISK);
            type = new PointerType(type);
        }
        return type;
    }

    private Block parseBlock() {
        Block block = new Block(); // vds and stmts initialized in the constructor
        expect(Category.LBRA);
        // parse variable declarations
        while (accept(Category.INT, Category.CHAR, Category.VOID, Category.STRUCT, Category.CLASS)) {
            // each var decl returns a VarDecl node which we add to block.vds
            block.vds.add(parseVariableDecl());
        }
        // parse statements
        while (isStmtStart()) {
            block.stmts.add(parseStmt());
        }
        expect(Category.RBRA);
        return block;
    }

    private Stmt parseStmt() {
        if (accept(Category.LBRA)) {
            return parseBlock();
        } else if (accept(Category.WHILE)) {
            return parseWhile();
        } else if (accept(Category.IF)) {
            return parseIf();
        } else if (accept(Category.RETURN)) {
            return parseReturn();
        } else if (accept(Category.CONTINUE)) {
            expect(Category.CONTINUE);
            expect(Category.SC);
            return new Continue();
        } else if (accept(Category.BREAK)) {
            expect(Category.BREAK);
            expect(Category.SC);
            return new Break();
        } else if (isExpStart()) {
            Expr e = parseExp();
            expect(Category.SC);
            return new ExprStmt(e);
        }
        error(Category.LBRA, Category.WHILE, Category.IF, Category.RETURN,
                Category.CONTINUE, Category.BREAK, Category.IDENTIFIER, Category.INT_LITERAL, Category.CHAR_LITERAL,
                Category.STRING_LITERAL, Category.LPAR);
        return null;  // should not reach this point
    }

    private Stmt parseWhile() {
        expect(Category.WHILE);
        expect(Category.LPAR);
        Expr condition = parseExp();
        expect(Category.RPAR);
        Stmt body = parseStmt();
        return new While(condition, body);
    }

    private Stmt parseIf() {
        expect(Category.IF);
        expect(Category.LPAR);
        Expr condition = parseExp();
        expect(Category.RPAR);
        Stmt thenStmt = parseStmt();
        Stmt elseStmt = null;
        if (accept(Category.ELSE)) {
            expect(Category.ELSE);
            elseStmt = parseStmt();
        }
        return new If(condition, thenStmt, elseStmt);
    }

    private Stmt parseReturn() {
        expect(Category.RETURN);
        Expr retExpr = null;
        if (isExpStart()) {
            retExpr = parseExp();
        }
        expect(Category.SC);
        return new Return(retExpr);
    }

    private boolean isExpStart() {
        // 'new' can also start an expression (instantiating an object)
        return accept(Category.IDENTIFIER, Category.INT_LITERAL, Category.CHAR_LITERAL,
                Category.STRING_LITERAL, Category.LPAR, Category.PLUS, Category.MINUS,
                Category.ASTERISK, Category.AND, Category.SIZEOF, Category.NEW);
    }

    private boolean isStmtStart() {
        return accept(Category.LBRA, Category.WHILE, Category.IF, Category.RETURN, Category.CONTINUE, Category.BREAK)
                || isExpStart();
    }

    private Expr parseExp() {
        return parseAssignment();
    }

    private Expr parseAssignment() {
        Expr left = parseLogicalOr();
        if (accept(Category.ASSIGN)) {
            expect(Category.ASSIGN);
            Expr right = parseAssignment();
            return new Assign(left, right);
        }
        return left;
    }

    private Expr parseLogicalOr() {
        Expr left = parseLogicalAnd();
        while (accept(Category.LOGOR)) {
            Token op = expect(Category.LOGOR);
            Expr right = parseLogicalAnd();
            left = new BinOp(left, tokenToOp(op), right);
        }
        return left;
    }

    private Expr parseLogicalAnd() {
        Expr left = parseEquality();
        while (accept(Category.LOGAND)) {
            Token op = expect(Category.LOGAND);
            Expr right = parseEquality();
            left = new BinOp(left, tokenToOp(op), right);
        }
        return left;
    }

    private Expr parseEquality() {
        Expr left = parseRelational();
        while (accept(Category.EQ, Category.NE)) {
            Token op = expect(Category.EQ, Category.NE);
            Expr right = parseRelational();
            left = new BinOp(left, tokenToOp(op), right);
        }
        return left;
    }

    private Expr parseRelational() {
        Expr left = parseAdditive();
        while (accept(Category.LT, Category.GT, Category.LE, Category.GE)) {
            Token op = expect(Category.LT, Category.GT, Category.LE, Category.GE);
            Expr right = parseAdditive();
            left = new BinOp(left, tokenToOp(op), right);
        }
        return left;
    }

    private Expr parseAdditive() {
        Expr left = parseMultiplicative();
        while (accept(Category.PLUS, Category.MINUS)) {
            Token op = expect(Category.PLUS, Category.MINUS);
            Expr right = parseMultiplicative();
            left = new BinOp(left, tokenToOp(op), right);
        }
        return left;
    }

    private Expr parseMultiplicative() {
        Expr left = parseUnary();
        while (accept(Category.ASTERISK, Category.DIV, Category.REM)) {
            Token op = expect(Category.ASTERISK, Category.DIV, Category.REM);
            Expr right = parseUnary();
            left = new BinOp(left, tokenToOp(op), right);
        }
        return left;
    }

    private Expr parseUnary() {
        // handle 'new' at the same precedence level as unary +, -
        if (accept(Category.NEW)) {
            return parseNewInstance();
        }
        if (accept(Category.SIZEOF)) {
            return parseSizeOf();
        } else if (accept(Category.MINUS, Category.PLUS, Category.ASTERISK, Category.AND)) {
            Token op = expect(Category.MINUS, Category.PLUS, Category.ASTERISK, Category.AND);
            Expr expr = parseUnary();
            // for unary plus/minus, represent them as BinOp with 0 as 1st arg
            switch (op.category) {
                case MINUS:
                    return new BinOp(new IntLiteral(0), Op.SUB, expr);
                case PLUS:
                    return new BinOp(new IntLiteral(0), Op.ADD, expr);
                case ASTERISK:
                    return new ValueAtExpr(expr);
                case AND:
                    return new AddressOfExpr(expr);
                default:
                    return expr;
            }
        } else if (accept(Category.LPAR) && isType(lookAhead(1).category)) {
            // typecast
            return parseTypecast();
        } else {
            return parsePrimary();
        }
    }


     // parse an object instantiation "new classtype()"
    private Expr parseNewInstance() {
        expect(Category.NEW); // we know it's 'new'
        // parse class type
        expect(Category.CLASS);
        Token id = expect(Category.IDENTIFIER);
        ClassType ct = new ClassType(id.data);

        expect(Category.LPAR);
        expect(Category.RPAR);

        return new NewInstance(ct);
    }

    private Expr parseTypecast() {
        expect(Category.LPAR);
        Type type = parseType();
        expect(Category.RPAR);
        Expr expr = parseUnary();
        return new TypecastExpr(type, expr);
    }

    // is token category a valid type?
    private boolean isType(Category category) {
        return category == Category.INT
                || category == Category.CHAR
                || category == Category.VOID
                || category == Category.STRUCT
                || category == Category.CLASS; // Part V: class type
    }

    private Expr parsePrimary() {
        Expr result = null;
        if (accept(Category.LPAR)) {
            expect(Category.LPAR);
            result = parseExp();
            expect(Category.RPAR);
        } else if (accept(Category.IDENTIFIER)) {
            Token ident = expect(Category.IDENTIFIER);
            if (accept(Category.LPAR))
                result = parseFuncall(ident);
            else
                result = new VarExpr(ident.data);
        } else if (accept(Category.INT_LITERAL, Category.CHAR_LITERAL, Category.STRING_LITERAL)) {
            Token lit = expect(Category.INT_LITERAL, Category.CHAR_LITERAL, Category.STRING_LITERAL);
            switch (lit.category) {
                case INT_LITERAL:
                    result = new IntLiteral(Integer.parseInt(lit.data));
                    break;
                case CHAR_LITERAL:
                    result = new ChrLiteral(decodeCharLiteral(lit.data));
                    break;
                case STRING_LITERAL:
                    result = new StrLiteral(lit.data);
                    break;
                default:
                    error(Category.INT_LITERAL, Category.CHAR_LITERAL, Category.STRING_LITERAL);
            }
        } else {
            error(Category.IDENTIFIER, Category.INT_LITERAL, Category.CHAR_LITERAL, Category.STRING_LITERAL, Category.LPAR);
        }

        // after parsing any primary, check for repeated . or [
        while (accept(Category.DOT) || accept(Category.LSBR)) {
            if (accept(Category.DOT)) {
                result = parseFieldOrInstanceCall(result);
            } else if (accept(Category.LSBR)) {
                result = parseArrayAccess(result);
            }
        }

        return result;
    }

    private char decodeCharLiteral(String s) {
        // s is the contents of the char lit
        if (s == null || s.isEmpty()) {
            return '\0';
        }
        if (s.charAt(0) != '\\') {
            return s.charAt(0);
        }
        if (s.length() < 2) {
            return '\\';
        }
        char esc = s.charAt(1);
        switch (esc) {
            case 'a': return '\u0007';  // bell
            case 'b': return '\b';      // backspace
            case 'n': return '\n';      // newline
            case 'r': return '\r';      // carriage return
            case 't': return '\t';      // horizontal tab
            case 'v': return '\u000B';  // vertical tab
            case 'f': return '\f';      // form feed
            case '\\': return '\\';     // backslash
            case '\'': return '\'';     // single quote
            case '"':  return '"';      // double quote
            case '0':  return '\0';     // null
            default:
                return esc;
        }
    }

    private Expr parseFuncall(Token ident) {
        expect(Category.LPAR);
        List<Expr> args = new ArrayList<>();
        if (!accept(Category.RPAR)) {
            args.add(parseExp());
            while (accept(Category.COMMA)) {
                expect(Category.COMMA);
                args.add(parseExp());
            }
        }
        expect(Category.RPAR);
        return new FunCallExpr(ident.data, args);
    }

    private Expr parseFieldOrInstanceCall(Expr expr) {
        // we already know there's a DOT
        expect(Category.DOT);
        Token fieldToken = expect(Category.IDENTIFIER);

        // if next token is ( then this is an instance function call
        if (accept(Category.LPAR)) {
            expect(Category.LPAR);
            List<Expr> args = new ArrayList<>();
            if (!accept(Category.RPAR)) {
                args.add(parseExp());
                while (accept(Category.COMMA)) {
                    expect(Category.COMMA);
                    args.add(parseExp());
                }
            }
            expect(Category.RPAR);
            Expr instanceCall = new InstanceFunCallExpr(expr, fieldToken.data, args);

            // parse chain of . or [ after instance call if needed
            while (accept(Category.DOT) || accept(Category.LSBR)) {
                if (accept(Category.DOT)) {
                    expect(Category.DOT);
                    Token nextField = expect(Category.IDENTIFIER);
                    if (accept(Category.LPAR)) {
                        // subsequent instance call
                        expect(Category.LPAR);
                        List<Expr> nextArgs = new ArrayList<>();
                        if (!accept(Category.RPAR)) {
                            nextArgs.add(parseExp());
                            while (accept(Category.COMMA)) {
                                expect(Category.COMMA);
                                nextArgs.add(parseExp());
                            }
                        }
                        expect(Category.RPAR);
                        instanceCall = new InstanceFunCallExpr(instanceCall, nextField.data, nextArgs);
                    } else {
                        instanceCall = new FieldAccessExpr(instanceCall, nextField.data);
                    }
                } else if (accept(Category.LSBR)) {
                    instanceCall = parseArrayAccess(instanceCall);
                }
            }
            return instanceCall;
        }
        else {
            // regular field access
            Expr fieldExpr = new FieldAccessExpr(expr, fieldToken.data);

            // parse chain of . or [ after field access
            while (accept(Category.DOT) || accept(Category.LSBR)) {
                if (accept(Category.DOT)) {
                    expect(Category.DOT);
                    Token nextField = expect(Category.IDENTIFIER);
                    if (accept(Category.LPAR)) {
                        // subsequent instance call
                        expect(Category.LPAR);
                        List<Expr> nextArgs = new ArrayList<>();
                        if (!accept(Category.RPAR)) {
                            nextArgs.add(parseExp());
                            while (accept(Category.COMMA)) {
                                expect(Category.COMMA);
                                nextArgs.add(parseExp());
                            }
                        }
                        expect(Category.RPAR);
                        fieldExpr = new InstanceFunCallExpr(fieldExpr, nextField.data, nextArgs);
                    } else {
                        fieldExpr = new FieldAccessExpr(fieldExpr, nextField.data);
                    }
                } else if (accept(Category.LSBR)) {
                    fieldExpr = parseArrayAccess(fieldExpr);
                }
            }
            return fieldExpr;
        }
    }

    private Expr parseArrayAccess(Expr expr) {
        expect(Category.LSBR);
        Expr index = parseExp();
        Expr result = new ArrayAccessExpr(expr, index);
        expect(Category.RSBR);
        while (accept(Category.LSBR)) {
            expect(Category.LSBR);
            Expr nextIndex = parseExp();
            expect(Category.RSBR);
            result = new ArrayAccessExpr(result, nextIndex);
        }
        return result;
    }

    private Expr parseSizeOf() {
        expect(Category.SIZEOF);
        expect(Category.LPAR);
        Type type = parseType();     // parse the type inside sizeof()
        expect(Category.RPAR);
        return new SizeOfExpr(type);
    }

    // helper, converts a token into the corresponding operator
    private Op tokenToOp(Token token) {
        switch(token.category) {
            case NE:        return Op.NE;
            case LOGAND:    return Op.AND;
            case MINUS:     return Op.SUB;
            case LE:        return Op.LE;
            case PLUS:      return Op.ADD;
            case LT:        return Op.LT;
            case DIV:       return Op.DIV;
            case REM:       return Op.MOD;
            case LOGOR:     return Op.OR;
            case GT:        return Op.GT;
            case EQ:        return Op.EQ;
            case GE:        return Op.GE;
            case ASTERISK:  return Op.MUL;

            default:
                throw new RuntimeException("Unexpected operator token: " + token);
        }
    }

}