struct point {
    int x;
    int y;
};

int main() {
    struct point p;
    p.x = 5;
    p.y = 10;
    return p.x + p.y;
}
