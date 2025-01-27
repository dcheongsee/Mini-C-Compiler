int main() {
    int x = 10;
    int y = ++x + x--;
    return y; // should handle mixed unary and binary operators
}