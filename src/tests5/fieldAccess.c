class A {
    int a;
    void printB() {
        // error, there's no field b
        print_i(b);
    }
}

void main() {
    class A a;
    a = new class A();
    a.a = 5;
    a.printB();
}
