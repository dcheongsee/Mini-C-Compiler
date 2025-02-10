
struct node {
    int data;
    struct node *next;
};

struct node* sort(struct node *head) {
    struct node *i;
    struct node *j;
    struct node *min;
    int temp;


    if (head == 0) return head;

    i = head;

    while (i != 0) {
        min = i;
        j = (*i).next;

        while (j != 0) {
            if ((*j).data < (*min).data) {
                min = j;
            }
            j = (*j).next;
        }
        if (min != i) {
            temp = (*i).data;
            (*i).data = (*min).data;
            (*min).data = temp;
        }
        i = (*i).next;
    }
    return head;
}

int main() {

    struct node n1, n2, n3;

    n1.data = 3;
    n2.data = 1;
    n3.data = 2;

    n1.next = &n2;
    n2.next = &n3;
    n3.next = 0;

    struct node* sorted = sort(&n1);

    return 0;
}