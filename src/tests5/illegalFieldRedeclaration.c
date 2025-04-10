class A {
    int data;
}

class B extends A {
    // error, fField data redeclared in subclass B
    int data;
}

void main() { }
