struct Pair {
    int a;
    int b;
};

struct Pair make_pair(int x, int y) {
    struct Pair res;
    res.a = x;
    res.b = y;
    return res;
}

void main() {
    struct Pair p;
    p = make_pair(5, 7);
    print_i(p.a); // 5
    print_i(p.b); // 7
}
