int square(int x) {
    return x * x;
}

int compute(int func_param, int val) {
    return square(val);  // call square directly
}

int main() {
    return compute(0, 5);
}