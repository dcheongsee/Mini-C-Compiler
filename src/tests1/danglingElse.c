int main() {
    if (1)
        if (0)
            return 1;
        else
            return 2; // should match the nearest  if
    return 0;
}