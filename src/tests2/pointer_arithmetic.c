int main() {
    int a;
    int *p;
    int b;
    a = 42;
    p = &a;   // assign the address of a to p
    b = *p;   // dereference p to get the value of a
    return b;
}
