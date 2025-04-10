class Parent {
    int getValue() {
        return 0;
    }
}

class Child extends Parent {
    // error, return type is char instead of int
    char getValue() {
        return 'A';
    }
}

void main() {
    class Parent p;
    p = (class Parent) new class Child();
    p.getValue();
}
