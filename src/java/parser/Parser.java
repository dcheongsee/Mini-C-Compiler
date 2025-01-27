package parser;


import lexer.Token;
import lexer.Token.Category;
import lexer.Tokeniser;
import util.CompilerPass;

import java.util.LinkedList;
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

    public void parse() {
        // get the first token
        nextToken();
        parseProgram();
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


    private void parseProgram() {
        parseIncludes();
        while (accept(Category.STRUCT, Category.INT, Category.CHAR, Category.VOID)) {
            if (token.category == Category.STRUCT &&
                    lookAhead(1).category == Category.IDENTIFIER &&
                    lookAhead(2).category == Category.LBRA) {
                parseStructDecl();
            }
            else {
                parseType();
                Token ident = expect(Category.IDENTIFIER);
                if (accept(Category.LPAR)) {
                    parseFunction(ident);
                } else {
                    parseVarDeclSuffix();
                }
            }
        }
        expect(Category.EOF);
    }

    // includes are ignored, so does not need to return an AST node
    private void parseIncludes() {
        if (accept(Category.INCLUDE)) {
            nextToken();
            expect(Category.STRING_LITERAL);
            parseIncludes();
        }
    }

    private void parseStructDecl() {
        expect(Category.STRUCT);
        expect(Category.IDENTIFIER);
        expect(Category.LBRA);
        do {
            parseVariableDecl();
        } while (accept(Category.INT, Category.CHAR, Category.VOID, Category.STRUCT));
        expect(Category.RBRA);
        expect(Category.SC);
    }

    private void parseFunction(Token ident) {
        expect(Category.LPAR);
        parseParameters();
        expect(Category.RPAR);
        if (accept(Category.LBRA)) {
            parseBlock();
        } else {
            expect(Category.SC);
        }
    }

    private void parseVariableDecl() {
        parseType();
        expect(Category.IDENTIFIER);
        parseArrayDimensions();
        expect(Category.SC);
    }

    // those that appear after type and identifier
    private void parseVarDeclSuffix() {
        parseArrayDimensions();
        expect(Category.SC);
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
    private void parseParameters() {
        if (!accept(Category.RPAR)) {
            parseParam();
            while (accept(Category.COMMA)) {
                expect(Category.COMMA);
                parseParam();
            }
        }
    }

    // single param in func decl
    private void parseParam() {
        parseType();
        expect(Category.IDENTIFIER);
        parseArrayDimensions();
    }

    private void parseType() {
        if (accept(Category.STRUCT)) {
            expect(Category.STRUCT);
            expect(Category.IDENTIFIER);
        } else {
            expect(Category.INT, Category.CHAR, Category.VOID);
        }
        while (accept(Category.ASTERISK)) expect(Category.ASTERISK);
    }

    private void parseBlock() {
        expect(Category.LBRA);
        while (accept(Category.INT, Category.CHAR, Category.VOID, Category.STRUCT)) parseVariableDecl();
        while (parseStmt()) ;
        expect(Category.RBRA);
    }

    private boolean parseStmt() {
        if (accept(Category.LBRA)) {
            parseBlock();
            return true;
        } else if (accept(Category.WHILE)) {
            parseWhile();
            return true;
        } else if (accept(Category.IF)) {
            parseIf();
            return true;
        } else if (accept(Category.RETURN)) {
            parseReturn();
            return true;
        } else if (accept(Category.CONTINUE, Category.BREAK)) {
            nextToken();
            expect(Category.SC);
            return true;
        } else if (isExpStart()) {
            parseExp();
            expect(Category.SC);
            return true;
        }
        return false;
    }

    private void parseWhile() {
        expect(Category.WHILE);
        expect(Category.LPAR);
        parseExp();
        expect(Category.RPAR);
        parseStmt();
    }

    private void parseIf() {
        expect(Category.IF);
        expect(Category.LPAR);
        parseExp();
        expect(Category.RPAR);
        parseStmt();
        if (accept(Category.ELSE)) {
            expect(Category.ELSE);
            parseStmt();
        }
    }

    private void parseReturn() {
        expect(Category.RETURN);
        if (isExpStart()) parseExp();
        expect(Category.SC);
    }

    private boolean isExpStart() {
        return accept(Category.IDENTIFIER, Category.INT_LITERAL, Category.CHAR_LITERAL,
                Category.STRING_LITERAL, Category.LPAR, Category.PLUS, Category.MINUS,
                Category.ASTERISK, Category.AND, Category.SIZEOF);
    }

    private void parseExp() {
        parseAssignment();
    }

    private void parseAssignment() {
        parseLogicalOr();
        if (accept(Category.ASSIGN)) {
            expect(Category.ASSIGN);
            parseAssignment();
        }
    }

    private void parseLogicalOr() {
        parseLogicalAnd();
        while (accept(Category.LOGOR)) {
            expect(Category.LOGOR);
            parseLogicalAnd();
        }
    }

    private void parseLogicalAnd() {
        parseEquality();
        while (accept(Category.LOGAND)) {
            expect(Category.LOGAND);
            parseEquality();
        }
    }

    private void parseEquality() {
        parseRelational();
        while (accept(Category.EQ, Category.NE)) {
            expect();
            parseRelational();
        }
    }

    private void parseRelational() {
        parseAdditive();
        while (accept(Category.LT, Category.GT, Category.LE, Category.GE)) {
            expect();
            parseAdditive();
        }
    }

    private void parseAdditive() {
        parseMultiplicative();
        while (accept(Category.PLUS, Category.MINUS)) {
            expect();
            parseMultiplicative();
        }
    }

    private void parseMultiplicative() {
        parseUnary();
        while (accept(Category.ASTERISK, Category.DIV, Category.REM)) {
            expect();
            parseUnary();
        }
    }

    private void parseUnary() {
        if (accept(Category.MINUS, Category.PLUS, Category.ASTERISK, Category.AND, Category.SIZEOF)) {
            expect();
            parseUnary();
        } else if (accept(Category.LPAR)) {
            if (isType(lookAhead(1).category)) {
                parseTypecast();
            } else {
                parsePrimary();
            }
        } else {
            parsePrimary();
        }
    }

    private void parseTypecast() {
        expect(Category.LPAR);
        parseType();
        expect(Category.RPAR);
        parseUnary();
    }

    // is token category a valid type?
    private boolean isType(Category category) {
        return category == Category.INT || category == Category.CHAR || category == Category.VOID || category == Category.STRUCT;
    }

    private void parsePrimary() {
        if (accept(Category.LPAR)) {
            expect(Category.LPAR);
            parseExp();
            expect(Category.RPAR);
        } else if (accept(Category.IDENTIFIER)) {
            Token ident = expect(Category.IDENTIFIER);
            if (accept(Category.LPAR)) parseFuncall(ident);
            else if (accept(Category.LSBR)) parseArrayAccess(ident);
            else if (accept(Category.DOT)) parseFieldAccess(ident);
        } else if (accept(Category.INT_LITERAL, Category.CHAR_LITERAL, Category.STRING_LITERAL)) {
            expect(Category.INT_LITERAL, Category.CHAR_LITERAL, Category.STRING_LITERAL);
        }   else {
            error(Category.IDENTIFIER, Category.INT_LITERAL, Category.CHAR_LITERAL, Category.STRING_LITERAL, Category.LPAR);
        }
    }

    private void parseFuncall(Token ident) {
        expect(Category.LPAR);
        if (isExpStart()) {
            parseExp();
            while (accept(Category.COMMA)) {
                expect(Category.COMMA);
                parseExp();
            }
        }
        expect(Category.RPAR);
    }

    private void parseArrayAccess(Token ident) {
        expect(Category.LSBR);
        parseExp();
        expect(Category.RSBR);
        while (accept(Category.LSBR)) {
            expect(Category.LSBR);
            parseExp();
            expect(Category.RSBR);
        }
    }

    private void parseFieldAccess(Token ident) {
        expect(Category.DOT);
        expect(Category.IDENTIFIER);
    }
}