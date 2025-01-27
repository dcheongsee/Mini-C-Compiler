struct A {
    struct B* b;
};

struct B {
    struct A* a;
};

int main() {
    return 0;
}