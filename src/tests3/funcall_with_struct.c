struct Pair {
    int a;
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
};

struct Pair make_pair(int x, int y, int z, int t, int q, int w, int r) {
    struct Pair res;
    res.a = x;
    res.b = y;
    res.c = z;
    res.d = t;
    res.e = q;
    res.f = w;
    res.g = r;
    return res;
}

void main() {
    struct Pair p;
    p = make_pair(5, 7, 4, 3, 22, 454, 9);
    print_i(p.a); // 5
    print_i(p.b); // 7
    print_i(p.c);
    print_i(p.d);
    print_i(p.e);
    print_i(p.f);
    print_i(p.g);
}
