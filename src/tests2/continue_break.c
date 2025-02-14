int main() {
    int i;
    int sum;
    i = 0;
    sum = 0;
    while (i < 10) {
        i = i + 1;
        if (i == 5) {
            continue;
        }
        if (i == 8) {
            break;
        }
        sum = sum + i;
    }
    return sum;
}
