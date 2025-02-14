int main() {
    int a;
    a = 5;
    {
        int b;
        b = 10;
        a = a + b;
        {
            int c;
            c = 3;
            a = a + c;
        }
    }
    return a;
}
