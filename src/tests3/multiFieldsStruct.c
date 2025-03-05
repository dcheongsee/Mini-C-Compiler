struct Data {
    int a;
    int b;
    int c;
};

struct Data f(int a, int b, int c, int d, int e, int f, int g, int h) {
    struct Data ret;
    ret.a = a + b;
    ret.b = c + d;
    ret.c = e + f + g + h;
    return ret;
}

void main() {
    struct Data result;
    int x;
    int y;
    int z;
    int u;
    int v;
    int w;
    int p;
    int q;

    // initialize params
    x = 1;
    y = 2;
    z = 3;
    u = 4;
    v = 5;
    w = 6;
    p = 7;
    q = 8;

    result = f(x, y, z, u, v, w, p, q);
    // expected
    // ret.a = 1+2 = 3
    // ret.b = 3+4 = 7
    // ret.c = 5+6+7+8 = 26
    // so sum = 3 + 7 + 26 = 36
    print_i(result.a + result.b + result.c);
}
