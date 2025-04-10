class A {
    int a;
}

class B extends A {
    int b;
}

void main() {
    class B b;
    b = new class B();
    // error, downcasting from B to A is not supported
    class B b2;
    b2 = (class B) b;

    class A a;
    a = new class B();
}
