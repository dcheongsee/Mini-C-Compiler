int main() {
    int largeArray[1000];
    for (int i = 0; i < 1000; i++) {
        largeArray[i] = i;
    }
    return largeArray[999];
}