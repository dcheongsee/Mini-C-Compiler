int main() {
    int a;
    a = 5;
    {
        // this inner declar shadows the outer 'a'
        int a;
        a = 10;
    }
    return a;
}
