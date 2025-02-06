// syntactically should pass parsing but semantically wrong(later stage of compiler)
int add(int a, int b) {
    return a + b;
}

char add(char a, char b) {
    return a + b;
}

int main() {
    return add(1, 2);
}
