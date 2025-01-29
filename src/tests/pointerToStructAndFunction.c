struct Node {
    int data;
    struct Node* next;
};

struct Node* createNode();

int main() {
    struct Node* head;
    head = createNode();
    (*head).next = createNode();
    return 0;
}

struct Node* createNode() {
    struct Node* newNode;
    newNode = malloc(sizeof(struct Node));
    (*newNode).data = 0;
    (*newNode).next = NULL;
    return newNode;
}