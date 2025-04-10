class A {
    int data;
}

class B extends A {

    void printData() {
        print_i(data);
    }
}

void main() {
    class B b;
    b = new class B();
    b.printData();
}
