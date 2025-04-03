int rec(int n);
int main();

int rec(int n) {
    int a;
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n1;
    int o;

    if (n == 0) {
        a = 111;
        b = 222;
        c = 333;
        d = 444;
        e = 555;
        f = a + b;      // 111 + 222 = 333
        g = c + d;      // 333 + 444 = 777
        h = e + 10;     // 555 + 10 = 565
        i = f + g;      // 333 + 777 = 1110
        j = h + i;      // 565 + 1110 = 1675
        k = j + 5;      // 1675 + 5 = 1680
        l = k;
        m = l;
        n1 = m;
        o = n1;         // base value: 1680
    } else {
        a = rec(n - 1); // first recursive call returns 1680
        b = a + 1;      // 1681
        c = a + 2;      // 1682
        d = rec(n - 1); // second recursive call returns 1680
        e = c + d;      // 1682 + 1680 = 3362
        f = e + a;      // 3362 + 1680 = 5042
        g = f + 3;      // 5042 + 3 = 5045
        h = g + 4;      // 5045 + 4 = 5049
        i = h + 5;      // 5049 + 5 = 5054
        j = i + a;      // 5054 + 1680 = 6734  (critical use of first rec()’s result)
        k = j + 6;      // 6734 + 6 = 6740
        l = k;
        m = l;
        n1 = m;
        o = n1;         // final value: 6740
    }
    return o;
}

int main() {
    int result;
    result = rec(1);
    print_i(result);
    return 0;
}
