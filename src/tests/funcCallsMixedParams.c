int compute(int a, int* b, char c[10]);

int main() {
    int x;
    int y;
    char flags[10];
    compute(x, &y, flags);
    return 0;
}

int compute(int a, int* b, char c[10]) {
    return a + (*b);
}