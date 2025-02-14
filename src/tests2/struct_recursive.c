struct node {
    int value;
    struct node* next;  // recursive reference via pointer is legal
};

int main() {
    struct node n;
    n.value = 1;
    n.next = 0;  // use 0 for pointer initialization
    return n.value;
}
