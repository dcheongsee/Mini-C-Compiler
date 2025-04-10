class A {
    int data;
}

class B extends A {
    int data;  // error, cannot override parent's field data
}

void main() {
    class B b;
    b = new class B();
}
