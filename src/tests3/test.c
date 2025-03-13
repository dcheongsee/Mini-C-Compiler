struct Data {
    int x;
};

struct Data addOne(struct Data d) {
    d.x = d.x + 1;
    return d;
}

struct Data recurse(struct Data d, int n) {
    if (n <= 0) {
        return d;
    }
    return recurse(addOne(d), n - 1);
}

void main() {
    struct Data d;
    d.x = 0;
    d = recurse(d, 10);
    // Expected output: 10
    print_i(d.x);
}
