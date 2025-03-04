struct Node {
    int val;
    struct Node * next;
};

void swap_nodes(struct Node * n1, struct Node * n2) {
    int temp;
    temp = (*n1).val;
    (*n1).val = (*n2).val;
    (*n2).val = temp;
}

void bubble_sort(struct Node * head) {
    struct Node * i;
    int swapped;
    if (head == 0) {
        return;
    }
    swapped = 1;  // initialize to true
    while (swapped == 1) {
        swapped = 0;
        i = head;
        while (((*i).next) != 0) {
            if (((*i).val) > (((*((*i).next)).val))) {
                swap_nodes(i, (*i).next);
                swapped = 1;
            }
            i = (*i).next;
        }
    }
}

void main() {
    struct Node n1;
    struct Node n2;
    struct Node n3;
    struct Node * head;

    n1.val = 30;
    n2.val = 10;
    n3.val = 20;
    n1.next = &n2;
    n2.next = &n3;
    n3.next = 0;
    head = &n1;

    // function call to sort the list
    bubble_sort(head);

    // print sorted list
    while (head != 0) {
        print_i((*head).val);
        head = (*head).next;
    }
}
