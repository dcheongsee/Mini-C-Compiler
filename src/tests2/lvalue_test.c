struct data {
    int value;
};

int main() {
    int arr[2];
    struct data d;

    d.value = 100;
    arr[0] = d.value;
    arr[1] = 200;

    return arr[0] + d.value;
}
