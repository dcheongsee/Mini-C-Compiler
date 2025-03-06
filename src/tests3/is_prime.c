int is_prime(int x) {
    int i;
    if (x < 2) {
        return 0;
    }
    i = 2;
    while (i * i <= x) {
        if ((x % i) == 0) {
            return 0;
        }
        i = i + 1;
    }
    return 1;
}

void main() {
    int val;
    print_s("Enter number:\n");
    val = read_i();
    if (is_prime(val) == 1) {
        print_s("Prime\n");
    } else {
        print_s("Not prime\n");
    }
}
