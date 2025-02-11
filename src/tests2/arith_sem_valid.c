int foo(int x) {
    return x;
}

struct S {
    int f;
};

int main() {
    int a;
    int b;
    int arr[3];
    struct S s;

    a = 5;
    b = 10;
    arr[0] = 1;
    arr[1] = 2;
    arr[2] = 3;
    s.f = 20;

    return
        (a = a + 1)
      + ((int)(*(&a)))
      - s.f
      * arr[1]
      / foo(a)
      % (b - a)
      + sizeof(int)
      + ((a + b) > 10)
      + ((a - b) < 0)
      + ((a * b) >= 50)
      + ((a / b) <= 2)
      + ((a % 3) != 0)
      + ((b % 3) == 1)
      + (((a + b) > 10) && ((a - b) < 0))
      + (((a * b) >= 50) || ((a / b) <= 2));
}