int main() {
    int a;
    int b;
    int c;
    a = 5;
    b = 10;
    c = a + b * (a - b) / 2 % 3;
    if (c >= 0 && (a != b || b < a)) {
        return c;
    } else {
        return 0;
    }
}