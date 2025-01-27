int main() {
    int i;
    i = 0;
    while (i < 5) {
        int j;
        j = 0;
        while (j < 5) { // nested loops
        }
        i = i + 1;  // valid under grammar’s exp = exp rule
    }
    return 0;
}