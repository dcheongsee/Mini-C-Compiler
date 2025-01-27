struct Point {
    int x, y;
};

int main() {
    struct Point p;
    p.z = 10; // accessing undefined member
    return 0;
}