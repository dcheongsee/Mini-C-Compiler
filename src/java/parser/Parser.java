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

    private final Queue<Token> buffer = new LinkedList<>();

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

        while (accept(Category.STRUCT, Category.INT, Category.CHAR, Category.VOID)) {
            if (token.category == Category.STRUCT &&
                    lookAhead(1).category == Category.IDENTIFIER &&
                    lookAhead(2).category == Category.LBRA) {
                decls.add(parseStructDecl());     // struct type decl
            }
            else {
                // parse either decl or def
                Type t = parseType();
                Token ident = expect(Category.IDENTIFIER);

                if (accept(Category.LPAR)) {
                    decls.add(parseFunction(ident.data, t));
                } else {
                    decls.add(parseVarDeclSuffix(t, ident));     // it's a var decl
                }
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
        } while (accept(Category.INT, Category.CHAR, Category.VOID, Category.STRUCT));
        expect(Category.RBRA);
        expect(Category.SC);

        return new StructTypeDecl(id.data, fields);
    }

    private Decl parseFunction(String funName, Type returnType) {
        expect(Category.LPAR);
        List<VarDecl> params = parseParameters();
        expect(Category.RPAR);
        if (accept(Category.LBRA)) {
            // it's a FunDef
            Block body = parseBlock();
            return new FunDef(returnType, funName, params, body);
        } else {
            // it's a FunDecl
            expect(Category.SC);
            return new FunDecl(returnType, funName, params);
        }
    }

    // returns a declaration AST node for a var
    private Decl parseVariableDecl() {
        Type type = parseType();
        Token ident = expect(Category.IDENTIFIER);
        parseArrayDimensions();
        expect(Category.SC);
        return new VarDecl(type, ident.data);
    }

    // those that appear after type and identifier
    private VarDecl parseVarDeclSuffix(Type type, Token ident) {
        parseArrayDimensions();
        expect(Category.SC);
        return new VarDecl(type, ident.data);
    }

    //  array dimensions in square brackets
    private void parseArrayDimensions() {
        while (accept(Category.LSBR)) {
            expect(Category.LSBR);
            expect(Category.INT_LITERAL);
            expect(Category.RSBR);
        }
    }

    // allows for 0 or more comma separated args
    private List<VarDecl> parseParameters() {
        List<VarDecl> parameters = new ArrayList<>();
        if (!accept(Category.RPAR)) {
            // we have at least one param
            parameters.add(parseParam());
            while (accept(Category.COMMA)) {
                expect(Category.COMMA);
                parameters.add(parseParam());
            }
        }
        return parameters;
    }

    // single param in func decl
    private VarDecl parseParam() {
        Type paramType = parseType();
        Token ident = expect(Category.IDENTIFIER);
        parseArrayDimensions();
        return new VarDecl(paramType, ident.data);
    }

    private Type parseType() {
        Type type;
        if (accept(Category.STRUCT)) {
            expect(Category.STRUCT);
            Token id = expect(Category.IDENTIFIER);

            type = new StructType(id.data);
        } else {
            // expect one of int, char, void
            Token t = expect(Category.INT, Category.CHAR, Category.VOID);
            switch (t.category) {
                case INT: type = BaseType.INT; break;
                case CHAR: type = BaseType.CHAR; break;
                case VOID: type = BaseType.VOID; break;
                default: type = BaseType.UNKNOWN; break;
            }
        }
        // handle pointer stars
        while (accept(Category.ASTERISK)) {
            expect(Category.ASTERISK);
            type = new PointerType(type);
        }
        return type;
    }



    private Block parseBlock() {
        expect(Category.LBRA);
        List<VarDecl> vds = new ArrayList<>();
        List<Stmt> stmts = new ArrayList<>();

        while (accept(Category.INT, Category.CHAR, Category.VOID, Category.STRUCT)) {
            vds.add((VarDecl) parseVariableDecl());
        }
        while (isStmtStart()) {
            Stmt s = parseStmt();
            if (s != null) stmts.add(s);
        }
        expect(Category.RBRA);

        return new Block(vds, stmts);
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
            nextToken();
            expect(Category.SC);
            return new Continue();
        } else if (accept(Category.BREAK)) {
            nextToken();
            expect(Category.SC);
            return new Break();
        } else if (isExpStart()) {
            Expr e = parseExp();
            expect(Category.SC);
            return new ExprStmt(e);
        }
        return null;
    }

    private While parseWhile() {
        expect(Category.WHILE);
        expect(Category.LPAR);
        Expr cond = parseExp();
        expect(Category.RPAR);
        Stmt body = parseStmt();
        return new While(cond, body);
    }

    private If parseIf() {
        expect(Category.IF);
        expect(Category.LPAR);
        Expr cond = parseExp();
        expect(Category.RPAR);
        Stmt thenPart = parseStmt();
        Stmt elsePart = null;
        if (accept(Category.ELSE)) {
            expect(Category.ELSE);
            elsePart = parseStmt();
        }
        return new If(cond, thenPart, elsePart);
    }

    private Return parseReturn() {
        expect(Category.RETURN);
        Expr value = null;
        if (isExpStart()) {
            value = parseExp();
        }
        expect(Category.SC);
        return new Return(value);
    }

    private boolean isStmtStart() {
        return accept(Category.LBRA, Category.WHILE, Category.IF, Category.RETURN, Category.CONTINUE, Category.BREAK)
                || isExpStart();
    }

    private boolean isExpStart() {
        return accept(Category.IDENTIFIER, Category.INT_LITERAL, Category.CHAR_LITERAL,
                Category.STRING_LITERAL, Category.LPAR, Category.PLUS, Category.MINUS,
                Category.ASTERISK, Category.AND, Category.SIZEOF);
    }

    private Expr parseExp() {
        return parseAssignment();
    }

    private Expr parseAssignment() {
        Expr lhs = parseLogicalOr();     // parseLogicalOr, then see if = is next
        if (accept(Category.ASSIGN)) {
            expect(Category.ASSIGN);
            Expr rhs = parseAssignment();
            return new Assign(lhs, rhs);
        }
        return lhs;
    }

    private Expr parseLogicalOr() {
        Expr left = parseLogicalAnd();
        while (accept(Category.LOGOR)) {
            nextToken();
            Expr right = parseLogicalAnd();
            left = new BinOp(left, Op.OR, right);
        }
        return left;
    }

    private Expr parseLogicalAnd() {
        Expr left = parseEquality();
        while (accept(Category.LOGAND)) {
            nextToken();
            Expr right = parseEquality();
            left = new BinOp(left, Op.AND, right);
        }
        return left;
    }

    private Expr parseEquality() {
        Expr left = parseRelational();
        while (accept(Category.EQ, Category.NE)) {
            Token op = expect(Category.EQ, Category.NE);
            Expr right = parseRelational();
            if (op.category == Category.EQ) {
                left = new BinOp(left, Op.EQ, right);
            } else {
                left = new BinOp(left, Op.NE, right);
            }
        }
        return left;
    }

    private Expr parseRelational() {
        Expr left = parseAdditive();
        while (accept(Category.LT, Category.GT, Category.LE, Category.GE)) {
            Token opT = token;
            nextToken();
            Op op;
            switch (opT.category) {
                case LT: op = Op.LT; break;
                case GT: op = Op.GT; break;
                case LE: op = Op.LE; break;
                case GE: op = Op.GE; break;
                default: op = Op.EQ;     // fallback
            }
            Expr right = parseAdditive();
            left = new BinOp(left, op, right);
        }
        return left;
    }

    private Expr parseAdditive() {
        Expr left = parseMultiplicative();
        while (accept(Category.PLUS, Category.MINUS)) {
            Token op = expect(Category.PLUS, Category.MINUS);
            Expr right = parseMultiplicative();
            if (op.category == Category.PLUS) {
                left = new BinOp(left, Op.ADD, right);
            } else {
                left = new BinOp(left, Op.SUB, right);
            }
        }
        return left;
    }

    private Expr parseMultiplicative() {
        Expr left = parseUnary();
        while (accept(Category.ASTERISK, Category.DIV, Category.REM)) {
            Token opT = token;
            nextToken();
            Op op;
            switch (opT.category) {
                case ASTERISK: op = Op.MUL; break;
                case DIV:      op = Op.DIV; break;
                case REM:      op = Op.MOD; break;
                default:       op = Op.ADD;     // fallback
            }
            Expr right = parseUnary();
            left = new BinOp(left, op, right);
        }
        return left;
    }

    private Expr parseUnary() {
        if (accept(Category.SIZEOF)) {
            return parseSizeOf();
        } else if (accept(Category.MINUS, Category.PLUS, Category.ASTERISK, Category.AND)) {
            Token opT = token;
            nextToken();
            Expr unary = parseUnary();
            Op op;
            switch(opT.category) {
                case MINUS: op = Op.SUB; break;
                case AND: op = Op.AND; break;
                case ASTERISK: op = Op.MUL; break;
                default: op = Op.ADD; break;
            }
            // for unary +, - use BinOp with 0
            return new BinOp(new IntLiteral(0), op, unary);
        } else if (accept(Category.LPAR)) {
            // peek to decide if typecast or paren expr
            if (isType(lookAhead(1).category)) {
                return parseTypecast();
            } else {
                expect(Category.LPAR);
                Expr e = parseExp();
                expect(Category.RPAR);
                return e;
            }
        } else {
            return parsePrimary();
        }
    }

    private Expr parseTypecast() {
        expect(Category.LPAR);
        Type t = parseType();
        expect(Category.RPAR);
        Expr e = parseUnary();
        return new TypecastExpr(t, e);
    }

    // is token category a valid type?
    private boolean isType(Category category) {
        return category == Category.INT || category == Category.CHAR || category == Category.VOID || category == Category.STRUCT;
    }

    private Expr parsePrimary() {
        if (accept(Category.LPAR)) {
            expect(Category.LPAR);
            Expr e = parseExp();
            expect(Category.RPAR);
            return e;
        } else if (accept(Category.IDENTIFIER)) {
            Token ident = expect(Category.IDENTIFIER);
            if (accept(Category.LPAR)) {
                return parseFuncall(ident.data);
            } else if (accept(Category.LSBR)) {
                return parseArrayAccess(new VarExpr(ident.data));
            } else if (accept(Category.DOT)) {
                return parseFieldAccess(new VarExpr(ident.data));
            } else {
                return new VarExpr(ident.data);
            }
        } else if (accept(Category.INT_LITERAL)) {
            Token i = expect(Category.INT_LITERAL);
            return new IntLiteral(Integer.parseInt(i.data));
        } else if (accept(Category.CHAR_LITERAL)) {
            Token c = expect(Category.CHAR_LITERAL);
            return new ChrLiteral(c.data.charAt(0));
        } else if (accept(Category.STRING_LITERAL)) {
            Token s = expect(Category.STRING_LITERAL);
            return new StrLiteral(s.data);
        } else {
            error(Category.IDENTIFIER, Category.INT_LITERAL, Category.CHAR_LITERAL, Category.STRING_LITERAL, Category.LPAR);
            return new IntLiteral(0);
        }
    }


    private Expr parseFuncall(String fname) {
        expect(Category.LPAR);
        List<Expr> args = new ArrayList<>();
        if (isExpStart()) {
            args.add(parseExp());
            while (accept(Category.COMMA)) {
                expect(Category.COMMA);
                args.add(parseExp());
            }
        }
        expect(Category.RPAR);
        return new FunCallExpr(fname, args);
    }

    private Expr parseArrayAccess(Expr arr) {
        while (accept(Category.LSBR)) {
            expect(Category.LSBR);
            Expr idx = parseExp();
            expect(Category.RSBR);
            arr = new ArrayAccessExpr(arr, idx);
        }
        return arr;
    }

    private Expr parseFieldAccess(Expr structExpr) {
        while (accept(Category.DOT)) {
            expect(Category.DOT);
            Token fieldName = expect(Category.IDENTIFIER);
            structExpr = new FieldAccessExpr(structExpr, fieldName.data);
        }
        return structExpr;
    }

    private Expr parseSizeOf() {
        expect(Category.SIZEOF);
        expect(Category.LPAR);
        Type t = parseType();
        expect(Category.RPAR);
        return new SizeOfExpr(t);
    }
}