
struct node {
    int data;
    struct node *next;
};


    int swapped;
    struct node *curr;
    int temp;   // temp is declared here so that no declaration occurs after a statement
    if (head == 0) return head;
    while (1) {
         swapped = 0;
         curr = head;
         while (((*curr).next) != 0) {
             if (((*curr).data) == (((*((*curr).next)).data))) {
                 curr = (*curr).next;
                 continue;
             }
             if (((*curr).data) > (((*((*curr).next)).data))) {
                 temp = (*curr).data;
                 (*curr).data = (*((*curr).next)).data;
                 (*((*curr).next)).data = temp;
                 swapped = 1;
             }
             curr = (*curr).next;
         }
         if (swapped == 0) break;
    }
    return head;
}

int main() {
    struct node n1;
    struct node n2;
    struct node* sorted;

    n1.data = 3;
    n2.data = 1;

    n1.next = &n2;
    n2.next = 0;

    sorted = sort(&n1);

    return 0;
}