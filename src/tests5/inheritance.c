class A extends B {  // error, A cannot extend B if B (directly or indirectly) extends A
    int data;
}

class B extends A {  // this forms a cycle.
    int data;
}

void main() {
}
