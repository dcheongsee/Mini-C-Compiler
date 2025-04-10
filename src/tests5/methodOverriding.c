class A {
    int foo(int x) {
        return x;
    }
}

class B extends A {
    // error, mismatched signature, parameter count or types differ
    int foo() {
        return 0;
    }
}

void main() {
    class A a;
    a = new class B();
    a.foo(10);
}
