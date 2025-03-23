struct Data {
    int x;
};

struct Data addOne(struct Data d) {
    d.x = d.x + 1;
    return d;
}

struct Data recurse(struct Data d, int n) {
    // print the current state before processing the recursion step
    print_s((char*)"Entering recurse: d.x = ");
    print_i(d.x);
    print_s((char*)", n = ");
    print_i(n);
    print_s((char*)"\n");

    if (n <= 0) {
        return d;
    }
    return recurse(addOne(d), n - 1);
}

void main() {
    struct Data d;
    d.x = 1;
    d = recurse(d, 3);
    // expected final output
    print_s((char*)"Final d.x = ");
    print_i(d.x);
}