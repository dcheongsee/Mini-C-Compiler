int factorial(int n) {
    int result;
    result = 1;
    while (n > 1) {
        result = result * n;
        n = n - 1;
    }
    return result;
}

void main() {
    int val;
    print_s((char*)"Enter a number:\n");
    val = read_i();
    print_i(factorial(val));
}
