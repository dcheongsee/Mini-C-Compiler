struct StringArray {
    char arr[3][10];
};

struct StringArray updateStrings(struct StringArray sa) {
    int i;
    int j;
    i = 0;
    while (i < 3) {
        j = 0;
        while (sa.arr[i][j] != '\0' && j < 10) {
            if (((int)sa.arr[i][j]) >= ((int)'a') && ((int)sa.arr[i][j]) <= ((int)'z')) {
                sa.arr[i][j] = (char)(((int)sa.arr[i][j]) - 32);
            }
            j = j + 1;
        }
        i = i + 1;
    }
    return sa;
}

void main() {
    struct StringArray sa;
    int i;

    // initialize each string in the 2D array field
    // string 0 = hello
    sa.arr[0][0] = 'h';
    sa.arr[0][1] = 'e';
    sa.arr[0][2] = 'l';
    sa.arr[0][3] = 'l';
    sa.arr[0][4] = 'o';
    sa.arr[0][5] = '\0';

    // string 1 = world
    sa.arr[1][0] = 'w';
    sa.arr[1][1] = 'o';
    sa.arr[1][2] = 'r';
    sa.arr[1][3] = 'l';
    sa.arr[1][4] = 'd';
    sa.arr[1][5] = '\0';

    // string 2 = test
    sa.arr[2][0] = 't';
    sa.arr[2][1] = 'e';
    sa.arr[2][2] = 's';
    sa.arr[2][3] = 't';
    sa.arr[2][4] = '\0';

    sa = updateStrings(sa);

    i = 0;
    while (i < 3) {
        print_s(sa.arr[i]);
        print_s((char*)"\n");
        i = i + 1;
    }
}
