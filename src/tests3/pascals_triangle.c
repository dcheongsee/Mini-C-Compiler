void main() {
    int n;
    int i;
    int j;
    int row[20];
    int prev[20];
    int size;

    print_s((char*)"Enter number of rows (<= 20):\n");
    n = read_i();
    size = 0;
    while (size < 20) {
        row[size] = 0;
        prev[size] = 0;
        size = size + 1;
    }

    i = 0;
    while (i < n) {
        // copy row into prev
        j = 0;
        while (j < i+1) {
            prev[j] = row[j];
            j = j + 1;
        }
        // compute row
        j = 0;
        while (j <= i) {
            if (j == 0 || j == i) {
                row[j] = 1;
            } else {
                row[j] = prev[j-1] + prev[j];
            }
            j = j + 1;
        }
        // print row
        j = 0;
        while (j <= i) {
            print_s((char*)"\n");
            print_i(row[j]);
            j = j + 1;
        }
        i = i + 1;
    }
}
