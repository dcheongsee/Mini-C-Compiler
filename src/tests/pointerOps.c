int main() {
    int a = 10;
    int* p = &a;
    *p = 20;
    return a; // should return 20
}