int main() {
    int a[3];
    a[0] = 10;
    a[1] = 20;
    a[2] = 30;
    print_i(a[-1]); //prints 0
    print_i(a[0]); //prints 10
    print_i(a[1]); //prints 20
    print_i(a[2]); //prints 30
    print_i(a[3]); //prints 0
    return 0;
}
