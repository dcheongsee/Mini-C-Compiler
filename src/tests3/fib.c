int fib(int n) {
    int a;
    int b;
    int temp;

    if (n < 2) {
        return n;
    }
    a = 0;
    b = 1;
    while (n > 1) {
        temp = a + b;
        a = b;
        b = temp;
        n = n - 1;
    }
    return b;
}

void main() {
    int x;
    print_s((char*)"Enter n for fibonacci:\n");
    x = read_i();
    print_i(fib(x));
}
