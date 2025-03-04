struct Data {
    int nums[3];
    char label;
};

void init_data(struct Data * d) {
    (*d).nums[0] = 7;
    (*d).nums[1] = 0;
    (*d).nums[2] = 14;
    (*d).label = 'Z';
}

void main() {
    // declarations at the top
    struct Data d;

    // initialize data by passing address of d using &
    init_data(&d);

    // print middle element and label
    print_i(d.nums[1]);  // expect 0
    print_c(d.label);    // expect Z
}
