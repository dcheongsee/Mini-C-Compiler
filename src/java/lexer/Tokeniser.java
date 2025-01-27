package lexer;

import util.CompilerPass;

/**
 * @author cdubach
 */
public class Tokeniser extends CompilerPass {

    private final Scanner scanner;

    public Tokeniser(Scanner scanner) {
        this.scanner = scanner;
    }

    private void error(char c, int line, int col) {
        String msg = "Lexing error: unrecognised character (" + c + ") at " + line + ":" + col;
        System.out.println(msg);
        incError();
    }

    public Token nextToken() {
        while (true) {
            int line = scanner.getLine();
            int column = scanner.getColumn();

            if (!scanner.hasNext()) {
                return new Token(Token.Category.EOF, line, column);
            }

            char c = scanner.next();

            // whitespace
            if (Character.isWhitespace(c)) {
                continue;
            }

            // comments
            if (c == '/') {
                if (!scanner.hasNext()) {
                    return new Token(Token.Category.DIV, line, column);
                }
                char next = scanner.peek();
                if (next == '/') { // single line
                    scanner.next();
                    while (scanner.hasNext() && scanner.peek() != '\n') {
                        scanner.next();
                    }
                    continue;
                } else if (next == '*') { // multi line
                    scanner.next();
                    boolean commentClosed = false;
                    while (scanner.hasNext()) {
                        char curr = scanner.next();
                        if (curr == '*' && scanner.hasNext() && scanner.peek() == '/') {
                            scanner.next();
                            commentClosed = true;
                            break;
                        }
                    }
                    if (!commentClosed) { // we should detect unclosed comments
                        error('/', line, column);
                        return new Token(Token.Category.INVALID, line, column);
                    }
                    continue;
                } else {
                    return new Token(Token.Category.DIV, line, column);
                }
            }

            // include directive
            if (c == '#') {
                StringBuilder include = new StringBuilder();
                include.append(c);
                boolean validInclude = true;
                String expected = "include";
                for (int i = 0; i < expected.length(); i++) {
                    if (!scanner.hasNext() || scanner.next() != expected.charAt(i)) {
                        validInclude = false;
                        break;
                    }
                    include.append(expected.charAt(i));
                }
                if (validInclude) {
                    return new Token(Token.Category.INCLUDE, line, column);
                } else {
                    error(c, line, column);
                    return new Token(Token.Category.INVALID, line, column);
                }
            }

            // identifiers & kywords
            if (Character.isLetter(c) || c == '_') {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (scanner.hasNext()) {
                    char peek = scanner.peek();
                    if (Character.isLetterOrDigit(peek) || peek == '_') {
                        sb.append(scanner.next());
                    } else {
                        break;
                    }
                }
                String ident = sb.toString();
                switch (ident) {
                    case "void":
                        return new Token(Token.Category.VOID, line, column);
                    case "while":
                        return new Token(Token.Category.WHILE, line, column);
                    case "char":
                        return new Token(Token.Category.CHAR, line, column);
                    case "int":
                        return new Token(Token.Category.INT, line, column);
                    case "return":
                        return new Token(Token.Category.RETURN, line, column);
                    case "break":
                        return new Token(Token.Category.BREAK, line, column);
                    case "struct":
                        return new Token(Token.Category.STRUCT, line, column);
                    case "sizeof":
                        return new Token(Token.Category.SIZEOF, line, column);
                    case "if":
                        return new Token(Token.Category.IF, line, column);
                    case "else":
                        return new Token(Token.Category.ELSE, line, column);
                    case "continue":
                        return new Token(Token.Category.CONTINUE, line, column);
                    default:
                        return new Token(Token.Category.IDENTIFIER, ident, line, column);
                }
            }

            // int literals
            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (scanner.hasNext() && Character.isDigit(scanner.peek())) {
                    sb.append(scanner.next());
                }
                return new Token(Token.Category.INT_LITERAL, sb.toString(), line, column);
            }

            // char literals
            if (c == '\'') {
                StringBuilder sb = new StringBuilder();
                int startLine = line;
                int startCol = column;
                boolean valid = true;

                if (!scanner.hasNext()) { // unterminated literal case
                    error(c, startLine, startCol);
                    return new Token(Token.Category.INVALID, startLine, startCol);
                }

                char nextChar = scanner.next();
                if (nextChar == '\\') { // escape sequences
                    if (scanner.hasNext()) {
                        char escapeChar = scanner.next();
                        if (isValidEscape(escapeChar)) {
                            sb.append('\\').append(escapeChar);
                        } else {
                            error(escapeChar, scanner.getLine(), scanner.getColumn());
                            valid = false;
                            while (scanner.hasNext() && scanner.peek() != '\'') {
                                scanner.next();
                            }
                        }
                    } else {
                        error(nextChar, scanner.getLine(), scanner.getColumn());
                        valid = false;
                    }
                } else {
                    sb.append(nextChar);
                }

                if (scanner.hasNext()) {  // check for closing '
                    char closingQuote = scanner.next();
                    if (closingQuote != '\'') {
                        if (valid) {
                            error(c, startLine, startCol);
                            valid = false;
                        }
                    }
                } else {
                    error(c, startLine, startCol);
                    valid = false;
                }

                if (valid) {
                    if (sb.length() != 1 && (sb.length() != 2 || sb.charAt(0) != '\\')) {
                        error(c, startLine, startCol);
                        valid = false;
                    }
                }

                if (valid) {
                    return new Token(Token.Category.CHAR_LITERAL, sb.toString(), startLine, startCol);
                } else {
                    return new Token(Token.Category.INVALID, startLine, startCol);
                }
            }

            //str literals
            if (c == '"') {
                StringBuilder sb = new StringBuilder();
                int startLine = line;
                int startCol = column;

                while (scanner.hasNext()) {
                    char nextChar = scanner.next();
                    if (nextChar == '\\') {
                        if (scanner.hasNext() && scanner.peek() == '\n') {  // escaped newline
                            scanner.next();
                            continue;
                        }
                        if (scanner.hasNext()) {
                            char escapeChar = scanner.next();
                            if (isValidEscape(escapeChar)) {
                                sb.append('\\').append(escapeChar);
                            } else {
                                error(escapeChar, scanner.getLine(), scanner.getColumn());
                                return new Token(Token.Category.INVALID, startLine, startCol);
                            }
                        } else {
                            error(nextChar, scanner.getLine(), scanner.getColumn());
                            return new Token(Token.Category.INVALID, startLine, startCol);
                        }
                    } else if (nextChar == '"') { // closing quote
                        return new Token(Token.Category.STRING_LITERAL, sb.toString(), startLine, startCol);
                    } else {
                        sb.append(nextChar);
                    }
                }


                error(c, startLine, startCol);
                return new Token(Token.Category.INVALID, startLine, startCol);
            }

            // multi-char op (also delimeters)
            switch (c) {
                case '+':
                    return new Token(Token.Category.PLUS, line, column);
                case '-':
                    return new Token(Token.Category.MINUS, line, column);
                case '*':
                    return new Token(Token.Category.ASTERISK, line, column);
                case '%':
                    return new Token(Token.Category.REM, line, column);
                case '&':
                    if (scanner.hasNext() && scanner.peek() == '&') {
                        scanner.next();
                        return new Token(Token.Category.LOGAND, line, column);
                    } else {
                        return new Token(Token.Category.AND, line, column);
                    }
                case '|':
                    if (scanner.hasNext() && scanner.peek() == '|') {
                        scanner.next();
                        return new Token(Token.Category.LOGOR, line, column);
                    } else {
                        error(c, line, column);
                        return new Token(Token.Category.INVALID, line, column);
                    }
                case '=':
                    if (scanner.hasNext() && scanner.peek() == '=') {
                        scanner.next();
                        return new Token(Token.Category.EQ, line, column);
                    } else {
                        return new Token(Token.Category.ASSIGN, line, column);
                    }
                case '!':
                    if (scanner.hasNext() && scanner.peek() == '=') {
                        scanner.next();
                        return new Token(Token.Category.NE, line, column);
                    } else {
                        error(c, line, column);
                        return new Token(Token.Category.INVALID, line, column);
                    }
                case '<':
                    if (scanner.hasNext() && scanner.peek() == '=') {
                        scanner.next();
                        return new Token(Token.Category.LE, line, column);
                    } else {
                        return new Token(Token.Category.LT, line, column);
                    }
                case '>':
                    if (scanner.hasNext() && scanner.peek() == '=') {
                        scanner.next();
                        return new Token(Token.Category.GE, line, column);
                    } else {
                        return new Token(Token.Category.GT, line, column);
                    }
                case '{':
                    return new Token(Token.Category.LBRA, line, column);
                case '}':
                    return new Token(Token.Category.RBRA, line, column);
                case '(':
                    return new Token(Token.Category.LPAR, line, column);
                case ')':
                    return new Token(Token.Category.RPAR, line, column);
                case '[':
                    return new Token(Token.Category.LSBR, line, column);
                case ']':
                    return new Token(Token.Category.RSBR, line, column);
                case ';':
                    return new Token(Token.Category.SC, line, column);
                case ',':
                    return new Token(Token.Category.COMMA, line, column);
                case '.':
                    return new Token(Token.Category.DOT, line, column);
            }

            // if we reach this point, it means we did not recognise a valid token
            error(c, line, column);
            return new Token(Token.Category.INVALID, line, column);
        }
    }

    private boolean isValidEscape(char c) {
        return c == 'a' || c == 'b' || c == 'n' || c == 'r' || c == 't' || c == '\\' || c == '\'' || c == '"' || c == '0';
    }
}