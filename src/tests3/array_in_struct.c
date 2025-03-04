struct Bag {
    int items[4];
};

void fill_bag(struct Bag * b) {
    (*b).items[0] = 100;
    (*b).items[1] = 200;
    (*b).items[2] = 300;
    (*b).items[3] = 400;
}

void main() {
    // declarations at the top
    struct Bag myBag;

    // call func by passing address of myBag using &
    fill_bag(&myBag);

    // print 3rd element of items array in myBag
    print_i(myBag.items[2]);
}
