void fill_matrix(int a[1][2]) {
    a[0][0] = 7;
    a[0][1] = 3;

}

void main() {
    // declarations
    int mat[1][2];
    fill_matrix(mat);
    print_i(mat[0][-2]); //prints gibberish integer value
    print_i(mat[0][-1]); //prints 7 (instead of 0)
    print_i(mat[0][0]); //prints 3 (instead of 7)
    print_i(mat[0][1]); //prints 0 (instead of 3)
    print_i(mat[0][2]); //prints 0 (instead of 0)

}
