struct FibPair {
    int current;
    int next;
};

struct FibPair fibRec(int n);

int main() {
    struct FibPair fp;
    fp = fibRec(7);
    print_i(fp.current);  // Print Fibonacci number F(7)
    return 0;
}

struct FibPair fibRec(int n) {
    struct FibPair result;
    struct FibPair temp;
    if (n == 0) {
        result.current = 0;
        result.next = 1;
        return result;
    }
    temp = fibRec(n - 1);
    result.current = temp.next;
    result.next = temp.current + temp.next;
    return result;
}
