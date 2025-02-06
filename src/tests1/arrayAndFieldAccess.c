struct Matrix {
    int data[3][3];
};

int main() {
    struct Matrix m;
    int value;
    m.data[0][1] = 5;
    value = m.data[0][1] + m.data[1][2];
    return value;
}