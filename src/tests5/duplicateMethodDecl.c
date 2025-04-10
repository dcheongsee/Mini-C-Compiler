class Calculator {
    int add(int x, int y) {
        return x + y;
    }
    // error, duplicate declaration for method add
    int add(int a, int b) {
        return a + b;
    }
}

void main() { }
