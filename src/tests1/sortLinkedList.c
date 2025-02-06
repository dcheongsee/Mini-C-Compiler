#include "list.h"

struct Node {
    int data;
    struct Node* next;
};

struct Node* sort(struct Node* head) {
    struct Node* curr;
    struct Node* prev;
    int swapped;

    if (head == (struct Node*)0) return head;

    swapped = 1;
    while (swapped) {
        struct Node* temp;
        swapped = 0;
        curr = head;
        prev = (struct Node*)0;

        while ((*curr).next != (struct Node*)0) {
            if ((*curr).data > (*(*curr).next).data) {
                temp = (*curr).next;
                (*curr).next = (*temp).next;
                (*temp).next = curr;

                if (prev == (struct Node*)0) head = temp;
                else (*prev).next = temp;

                prev = temp;
                swapped = 1;
            } else {
                prev = curr;
                curr = (*curr).next;
            }
        }
    }
    return head;
}

int main() {
    struct Node n1;
    struct Node n2;
    struct Node* head;

    n1.data = 3;
    n1.next = (struct Node*)0;
    n2.data = 1;
    n2.next = &n1;
    head = &n2;

    head = sort(head);
    return 0;
}