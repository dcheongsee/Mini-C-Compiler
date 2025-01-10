package lexer;

import util.Position;

/**
 * @author cdubach
 */
public class Token {

    public enum Category {

        // ' is used to enclose the input that needs to be recognized

        // LowerCaseAlpha = 'a'|...|'z'
        // UpperCaseAlpha = 'A'|...|'Z'
        // Digit          = '0' | '1' | ... | '9'
        IDENTIFIER, // (LowerCaseAlpha | UpperCaseAlpha | '_') (Digit | LowerCaseAlpha | UpperCaseAlpha | '_')*

        ASSIGN, // '='

        // delimiters
        LBRA,  // '{' // left brace
        RBRA,  // '}' // right brace
        LPAR,  // '(' // left parenthesis
        RPAR,  // ')' // right parenthesis
        LSBR,  // '[' // left square brace
        RSBR,  // ']' // left square brace
        SC,    // ';' // semicolon
        COMMA, // ','

        // types
        INT,  // 'int'
        VOID, // 'void'
        CHAR, // 'char'

        // keywords
        IF,     // 'if'
        ELSE,   // 'else'
        WHILE,  // 'while'
        RETURN, // 'return'
        STRUCT, // 'struct'
        SIZEOF, // 'sizeof'
        CONTINUE, // 'continue'
        BREAK, // 'break'

        // include
        INCLUDE, // '#include'

        // SpecialCharWithoutSingleQuote = One of the following 30 characters: ` ~ @ ! $ # ^ * % & ( ) [ ] { } < > + = _ - | / ; : , . ? "
        // SpecialCharWithoutDoubleQuote = One of the following 30 characters: ` ~ @ ! $ # ^ * % & ( ) [ ] { } < > + = _ - | / ; : , . ? '
        // WhiteSpace                    = ' '
        // EscapedChar                   = '\a' | '\b' | '\n' | '\r' | '\t' | '\\' | '\'' | '\"' | '\0'
        // literals
        CHAR_LITERAL,   // ''' (LowerCaseAlpha | UpperCaseAlpha | Digit |  SpecialCharWithoutSingleQuote  | WhiteSpace | EscapedChar) '''  any character (except single quote) enclosed within  a pair of single quotes
        STRING_LITERAL, // '"' (LowerCaseAlpha | UpperCaseAlpha | Digit |  SpecialCharWithoutDoubleQuote  | WhiteSpace | EscapedChar)* '"'  any sequence of characters (except double quote) enclosed within two double quotes
        INT_LITERAL,    // Digit+

        // logical operators
        LOGAND, // '&&'
        LOGOR,  // '||'

        // comparisons
        EQ, // '=='
        NE, // '!='
        LT, // '<'
        GT, // '>'
        LE, // '<='
        GE, // '>='

        // operators
        PLUS,    // '+'
        MINUS,   // '-'
        ASTERISK, // '*'  // can be used for multiplication or pointers
        DIV,     // '/'
        REM,     // '%'
        AND,     // '&'

        // struct member access
        DOT, // '.'

        // special tokens
        EOF,    // signal end of file
        INVALID // in case we cannot recognise a character as part of a valid token
    }


    public final Category category;
    public final String data;
    public final Position position;

    public Token(Category category, int lineNum, int colNum) {
        this(category, "", lineNum, colNum);
    }

    public Token (Category category, String data, int lineNum, int colNum) {
        assert (category != null);
        this.category = category;
        this.data = data;
        this.position = new Position(lineNum, colNum);
    }



    @Override
    public String toString() {
        if (data.equals(""))
            return category.toString();
        else
            return category.toString()+"("+data+")";
    }

}


