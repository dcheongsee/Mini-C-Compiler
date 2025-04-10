class A {
    int value;
    void printValue() {
        print_s((char*)"Value: ");
        print_i(value);
        print_s((char*)"\n");
    }
}

class B extends A {
    void doublePrint() {
        // calls inherited printValue
        printValue();
        printValue();
    }
}

void main() {
    class B b;
    b = (class B) new class B();
    b.value = 25;
    b.doublePrint();
}
