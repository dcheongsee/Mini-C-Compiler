int main() {
    int a;
    char c;
    int sizeA;
    int sizeC;
    a = 10;
    sizeA = sizeof(int);
    sizeC = sizeof(char);
    c = (char)a;
    return sizeA + sizeC;
}