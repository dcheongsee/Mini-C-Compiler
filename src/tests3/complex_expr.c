int foo(int a, int b) {
    return a * b;
}

int main() {
    int x;
    int y;
    int z;
    x = 2;
    y = 3;
    z = foo(x + 1, y + 2) - (x * y);  // foo(3,5)=15; 2*3=6; 15-6=9
    print_i(z);
    return 0;
}
