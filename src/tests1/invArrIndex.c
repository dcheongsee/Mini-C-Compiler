int main() {
    int arr[5];
    arr[0] = 1;
    arr[1] = 2;
    return arr['a']; // syntactically valid (parser passes) but semantic error
}