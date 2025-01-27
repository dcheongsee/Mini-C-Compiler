int main() {
    int a = 10, b = 20, c = 30;
    return a > b ? (b > c ? b : c) : a; // nested ternary
}