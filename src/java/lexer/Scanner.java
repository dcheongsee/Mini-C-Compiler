package lexer;

import java.io.*;

/**
 * @author cdubach
 */
public class Scanner {

    private BufferedReader input;
    private int peeked = -1;

    private int line = 1;
    private int column = 1;

    public Scanner(File source) throws FileNotFoundException {
        input = new BufferedReader(new FileReader(source));
    }


    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }


    private int read() {
        int i;
        try {
            i = input.read();
        } catch (IOException ioe) {
            throw new Error(ioe); // propagate as unchecked exception
        }
        return i;
    }

    public char peek() {
        if (peeked != -1)
            return (char)peeked;

        peeked = read();
        if (peeked == -1)
            throw new Error(new EOFException()); // throw as unchecked exception

        return (char) peeked;
    }


    public char next() {
        char p = peek();
        peeked = -1;
        if (p == '\n') {
            line++;
            column = 1;
        } else
            column++;

        return p;
    }

    /**
     * Returns true only if next() or peek() can produce another character
     */
    public boolean hasNext() {
        if (peeked != -1)
            return true;

        peeked = read();
        if (peeked == -1)
            return false;

        return true;
    }


    public void close() throws IOException {
        input.close();
    }



}
