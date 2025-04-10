class Alpha {
    int x;
}

void main() {
    class Alpha a;
    a = new class Alpha();
    // error,alpha does not declare a method nonExistent
    a.nonExistent();
}
