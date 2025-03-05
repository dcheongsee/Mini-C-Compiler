void main() {
    int n;
    int temp;
    int i;
    int bits[32]; // up to 32 bits
    print_s((char*)"Enter a number:\n");
    n = read_i();

    i = 0;
    while (i < 32) {
        bits[i] = 0;
        i = i + 1;
    }

    i = 0;
    temp = n;
    while (temp > 0) {
        bits[i] = temp % 2;
        temp = temp / 2;
        i = i + 1;
    }

    // print in reverse
    while (i > 0) {
        i = i - 1;
        print_i(bits[i]);
    }
}
