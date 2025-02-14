int sum2D(int arr[2][3]) {
    int sum;
    int i;
    int j;
    sum = 0;
    i = 0;
    while (i < 2) {
        j = 0;
        while (j < 3) {
            sum = sum + arr[i][j];
            j = j + 1;
        }
        i = i + 1;
    }
    return sum;
}

int main() {
    int matrix[2][3];
    matrix[0][0] = 1; matrix[0][1] = 2; matrix[0][2] = 3;
    matrix[1][0] = 4; matrix[1][1] = 5; matrix[1][2] = 6;
    return sum2D(matrix);
}
