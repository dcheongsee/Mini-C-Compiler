void main() {
    char board[3][3];
    int i;
    int j;
    // initialize board
    i = 0;
    while (i < 3) {
        j = 0;
        while (j < 3) {
            board[i][j] = '.';
            j = j + 1;
        }
        i = i + 1;
    }
    board[0][0] = 'X';
    board[1][1] = 'O';
    board[2][2] = 'X';

    // print board
    i = 0;
    while (i < 3) {
        j = 0;
        while (j < 3) {
            print_c(board[i][j]);
            j = j + 1;
        }
        print_s((char*)"\n");
        i = i + 1;
    }
}
