int main() {
    int a;
    int b;
    int c;
    int d;
    int e;
    int f;
    int i;
    int sum;
    a = 1;
    b = 2;
    c = 3;
    d = 4;
    e = 5;
    f = 6;
    i = 0;
    while (i < 5) {
        a = b + f;
        b = a + e;
        c = b + d;
        d = c + e;
        e = d + f;
        f = e + a;
        i = i + 1;
    }
    sum = a + b + c + d + e + f;
    print_i(sum);
    return 0;
}