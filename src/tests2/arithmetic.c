int main() {
    int a;
    int b;
    int c;

    a = 2 + 3 * 4;      // tests multiplication has higher precedence than addition
    b = (2 + 3) * 4;    // tests parentheses altering the natural precedence
    c = -a + +b - 10 / 2; // tests unary plus/minus and division

    return a + b + c;
}