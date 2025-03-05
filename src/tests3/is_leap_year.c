int is_leap(int year) {
    if ((year % 400) == 0) {
        return 1;
    }
    if ((year % 100) == 0) {
        return 0;
    }
    if ((year % 4) == 0) {
        return 1;
    }
    return 0;
}

void main() {
    int y;
    print_s((char*)"Enter a year:\n");
    y = read_i();
    if (is_leap(y) == 1) {
        print_s((char*)"Leap year");
    } else {
        print_s((char*)"Not leap year");
    }
}
