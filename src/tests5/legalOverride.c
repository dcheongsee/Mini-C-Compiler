class Base {
    int compute(int a) {
        return a;
    }
}

class Derived extends Base {
    // same return type, same parameter type and count
    int compute(int a) {
        return a * 2;
    }
}

void main() {
    class Base obj;
    obj = (class Base) new class Derived();
    obj.compute(5);
}
