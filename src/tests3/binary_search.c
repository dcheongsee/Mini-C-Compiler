int binary_search(int arr[6], int target) {
    int left;
    int right;
    int mid;
    left = 0;
    right = 5;
    while (left <= right) {
        mid = (left + right) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return -1;
}

void main() {
    int sorted[6];
    int index;
    int key;

    // 6 sorted elements
    sorted[0] = 2;
    sorted[1] = 4;
    sorted[2] = 7;
    sorted[3] = 9;
    sorted[4] = 15;
    sorted[5] = 99;

    print_s((char*)"Enter key:\n");
    key = read_i();
    index = binary_search(sorted, key);
    print_i(index); // -1 if not found, otherwise print index
}
