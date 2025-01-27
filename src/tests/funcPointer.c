int add(int a, int b) {
    return a + b;
}

int main() {
    int (*func_ptr)(int, int) = add;
    return func_ptr(3, 4); // should return 7
}