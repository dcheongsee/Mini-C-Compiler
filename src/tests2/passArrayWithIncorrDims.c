int f(int a[3]) {
    return a[0];
}

int main() {

    int arr[3][3];
    int result;

    arr[0][0] = 10; arr[0][1] = 11; arr[0][2] = 12;
    arr[1][0] = 20; arr[1][1] = 21; arr[1][2] = 22;
    arr[2][0] = 30; arr[2][1] = 31; arr[2][2] = 32;


    result = f(arr);

    return result;
}
