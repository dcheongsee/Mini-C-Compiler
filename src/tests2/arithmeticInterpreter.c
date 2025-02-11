int main() {
    int a;
    int b;
    int arr[3];

    a = 42;
    b = 7;
    arr[0] = a;
    arr[1] = b;
    arr[2] = a + b;

    return
         (a = a - 5)
       + ((int)(*(&(*(&b)))))
       - (-(+(-a)))
       + sizeof(int)
       + ((a > b) && (a < arr[0]))
       + ((a == b) || ((arr[1] * arr[2]) > 0))
       + (+(+(+(b))));
}
