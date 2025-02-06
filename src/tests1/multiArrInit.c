int main() {
    int arr[2][3];
     arr[0][0] = 1;
     arr[0][1] = 2;
     arr[0][2] = 3;
     arr[1][0] = 4;
     arr[1][1] = 5;
     arr[1][2] = 6;

    return arr[1][2]; // should return 6
}