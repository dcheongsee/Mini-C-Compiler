struct S {
    int x;
    char y;
};

int main() {
    struct S s;
    s.x = 100;
    s.y = 'A';
    print_i(s.x);
    print_c(s.y);
    return 0;
}
