int increment(int x) {
    return x + 1;
}

int main() {
    return increment(increment(increment(5))); // chained func calls
}