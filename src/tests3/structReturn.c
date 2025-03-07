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
        (*tbl).entries[i].value = (*tbl).entries[i].value + offset;
        i = i + 1;
    }
    return *tbl;
}

void main() {
    struct Table t;
    int i;
    struct Table result;
    int sum;

    // initialize
    t.entries[0].key = 10; t.entries[0].value = 100;
    t.entries[1].key = 20; t.entries[1].value = 200;
    t.entries[2].key = 30; t.entries[2].value = 300;
    t.entries[3].key = 40; t.entries[3].value = 400;
    t.entries[4].key = 50; t.entries[4].value = 500;

    // i = t.entries[2].key / 10 => 30 / 10 = 3
    i = t.entries[2].key / 10;

    result = modify(&t, i);

    // sum up all values in returned struct
    sum = 0;
    i = 0;
    while (i < 5) {
        sum = sum + result.entries[i].value;
        i = i + 1;
    }
    // expected
    // 103, 203, 303, 403, 503, sum = 103 + 203 + 303 + 403 + 503 = 1515
    print_i(sum);
}
