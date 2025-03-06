struct Entry {
    int key;
    int value;
};

struct Table {
    struct Entry entries[5];
};

struct Table modify(struct Table * tbl, int offset) {
    int i;
    i = 0;
    while (i < 5) {
        // Instead of the disallowed "->", use explicit dereferencing.
        (*tbl).entries[i].value = (*tbl).entries[i].value + offset;
        i = i + 1;
    }
    return *tbl;
}

void main() {
    // Declarations at the top
    struct Table t;
    int i;
    struct Table result;
    int sum;

    // Initialize the table.
    t.entries[0].key = 10; t.entries[0].value = 100;
    t.entries[1].key = 20; t.entries[1].value = 200;
    t.entries[2].key = 30; t.entries[2].value = 300;
    t.entries[3].key = 40; t.entries[3].value = 400;
    t.entries[4].key = 50; t.entries[4].value = 500;

    // Compute offset from an arithmetic expression using a field in the struct.
    // i = t.entries[2].key / 10 => 30 / 10 = 3.
    i = t.entries[2].key / 10;

    // Call modify with the address of t and the computed offset.
    result = modify(&t, i);

    // Sum up all the values in the returned struct.
    sum = 0;
    i = 0;
    while (i < 5) {
        sum = sum + result.entries[i].value;
        i = i + 1;
    }
    // Expected:
    // For each entry, value becomes: original value + 3.
    // Thus: 103, 203, 303, 403, 503; sum = 103 + 203 + 303 + 403 + 503 = 1515.
    print_i(sum);
}
