struct Point {
    int x;
    int y;
};
int main() {
    struct Point p;
    p.z = 10; // now that's a semantic error (invalid member)
    return 0;
} // parser should accept this code but not semantic analyzer