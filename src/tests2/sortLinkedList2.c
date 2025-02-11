struct Node {
    int data;
    struct Node *next;
};

struct Node *insertSorted(struct Node *sorted, struct Node *node) {
    if (sorted == 0) {
        (*node).next = 0;
        return node;
    }
    if ((*node).data < (*sorted).data) {
        (*node).next = sorted;
        return node;
    }
    {
        struct Node *current;
        current = sorted;
        while (((*current).next != 0) && (((*((*current).next)).data) < (*node).data)) {
            current = (*current).next;
        }
        (*node).next = (*current).next;
        (*current).next = node;
    }
    return sorted;
}

struct Node *sortList(struct Node *head) {
    struct Node *sorted;
    sorted = 0;
    while (head != 0) {
        struct Node *temp;
        temp = (*head).next;
        sorted = insertSorted(sorted, head);
        head = temp;
    }
    return sorted;
}

int main() {
    struct Node n1;
    struct Node n2;
    struct Node n3;
    struct Node n4;
    struct Node *head;
    int sum;
    n1.data = 7;
    n2.data = 3;
    n3.data = 9;
    n4.data = 1;

    n1.next = &n2;
    n2.next = &n3;
    n3.next = &n4;
    n4.next = 0;


    head = &n1;

    head = sortList(head);

    sum = 0;
    while (head != 0) {
        sum = sum + (*head).data;
        head = (*head).next;
    }

    return sum;
}
