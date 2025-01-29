int add(int a, int b);
int multiply(int* a, int b[10]);
struct Matrix {
    int data[3][3];
};
int process(struct Matrix m, int* values, char flags[5]);

int main() {
    return 0;
}

int add(int a, int b) {
    return a + b;
}

int multiply(int* a, int b[10]) {
    return *a * b[0];
}

int process(struct Matrix m, int* values, char flags[5]) {
    return m.data[0][0] + values[0];
}