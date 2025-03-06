struct S {
    int a;
    int b;
};

struct T {
    struct S arr[3];
    int x;
};

int compute(struct T t, int i) {
    // compute sum of two fields in the ith element of arr plus t.x
    int result;
    print_i(i);
    print_i(777);
    print_i(t.arr[i].a);
    print_i(777);
    print_i(t.arr[i].b);
    print_i(777);
    print_i(t.x);
    print_i(777);
    result = t.arr[i].a + t.arr[i].b + t.x;
    return result;
}

void main() {
    struct T t;
    int i;
    int sum;

    t.arr[0].a = 1;
    t.arr[0].b = 2;
    t.arr[1].a = 3;
    t.arr[1].b = 4;
    t.arr[2].a = 5;
    t.arr[2].b = 6;
    // initialize additional field
    t.x = 10;

    // i = (t.arr[0].a + t.arr[1].a) % 3 = (1 + 3) % 3 = 4 % 3 = 1.
    i = ((t.arr[0].a + t.arr[1].a) % 3);
    print_i(777);
    print_i(i);
    print_i(777);

    sum = compute(t, i);

    // expected, compute(t,1) = t.arr[1].a + t.arr[1].b + t.x = 3 + 4 + 10 = 17
    print_i(sum);
    print_i(777);
}
