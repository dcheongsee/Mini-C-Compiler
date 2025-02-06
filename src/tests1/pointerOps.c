int main() {
    int a;
    int* p;
    a = 10;
    p = &a;
    *p = 20;
    return a; // should return 20
}