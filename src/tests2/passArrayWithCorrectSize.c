int sumRow(int row[3]) {
    return row[0] + row[1] + row[2];
}


int sumMatrix(int matrix[2][3]) {

    return sumRow(matrix[0]) + sumRow(matrix[1]);
}

int main() {
    int row[3];
    int matrix[2][3];
    int result;

    row[0] = 1;
    row[1] = 2;
    row[2] = 3;


    matrix[0][0] = 10; matrix[0][1] = 20; matrix[0][2] = 30;
    matrix[1][0] = 40; matrix[1][1] = 50; matrix[1][2] = 60;


    result = sumRow(row) + sumMatrix(matrix);

    return result;
}
