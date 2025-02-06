struct Inner {
    int x;
};

struct Outer {
    struct Inner inner;
};

int main() {
    struct Outer o;
    o.inner.x = 10;
    return o.inner.x;
}