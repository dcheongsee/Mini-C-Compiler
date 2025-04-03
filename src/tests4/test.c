void main() {
    int amount;
    int tens;
    int ones;

    print_s((char*)"Enter amount:\n");
    amount = read_i();
    tens = amount / 10;
    ones = amount % 10;
    print_s((char*)"Tens:\n");
    print_i(tens);
    print_s((char*)"\n");
    print_s((char*)"Ones:\n");
    print_i(ones);
}
