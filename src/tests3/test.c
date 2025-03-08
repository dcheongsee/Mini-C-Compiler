struct Node {
    int vals[4];
};

struct Node transform1(struct Node node) {
    int i;
    i = 0;
    while (i < 4) {
        node.vals[i] = node.vals[i] + 2;
        i = i + 1;
    }
    return node;
}

struct Node transform2(struct Node node) {
    int i;
    i = 0;
    while (i < 4) {
        node.vals[i] = node.vals[i] * 3;
        i = i + 1;
    }
    return node;
}

struct Node chain(struct Node node, int n) {
    // base case, no more transformations
    if (n == 0) {
        return node;
    }
    // even n, apply transform1; odd n, apply transform2
    if (n % 2 == 0) {
        return chain(transform1(node), n - 1);
    } else {
        return chain(transform2(node), n - 1);
    }
}

void main() {
    struct Node start;
    struct Node result;
    int sum;
    int i;

    start.vals[0] = 1;
    start.vals[1] = 2;
    start.vals[2] = 3;
    start.vals[3] = 4;

    result = chain(start, 3);

    // expected
    // sum = 15 + 24 + 33 + 42 = 114
    sum = result.vals[0] + result.vals[1] + result.vals[2] + result.vals[3];
    print_i(sum);
}
