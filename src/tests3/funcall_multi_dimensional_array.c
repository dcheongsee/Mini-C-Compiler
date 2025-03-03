void fill_matrix(int a[2][3]) {
    a[0][0] = 11;
    a[0][1] = 12;
    a[0][2] = 13;
    a[1][0] = 21;
    a[1][1] = 22;
    a[1][2] = 23;
}

void main() {
    // declarations
    int mat[2][3];
    fill_matrix(mat);
    print_i(mat[0][0]);
    print_i(mat[0][1]);
    print_i(mat[0][2]);
    print_i(mat[1][0]);
    print_i(mat[1][1]);
    print_i(mat[1][2]); // 23
}
