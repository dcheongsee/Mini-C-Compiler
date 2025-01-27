int main() {
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                for (int l = 0; l < 5; l++) {
                    print_i(i + j + k + l);
                }
            }
        }
    }
    return 0;
}