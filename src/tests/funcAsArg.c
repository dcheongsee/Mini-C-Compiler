int square(int x) {
    return x * x;
}

int compute(int (*func)(int), int val) {
    return func(val);
}

int main() {
    return compute(square, 5); // should return 25
}