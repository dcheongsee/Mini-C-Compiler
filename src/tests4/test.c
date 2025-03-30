int arr[12];
int i;
int result;
int weight;
int adjust;

int weightedReduce(int n);
int adjustResult(int val);

int main() {
    i = 0;
    result = 0;
    weight = 2;
    adjust = 3;
    arr[0] = 5;
    arr[1] = 7;
    arr[2] = 9;
    arr[3] = 11;
    arr[4] = 13;
    arr[5] = 15;
    arr[6] = 17;
    arr[7] = 19;
    arr[8] = 21;
    arr[9] = 23;
    arr[10] = 25;
    arr[11] = 27;
    result = weightedReduce(12);
    result = adjustResult(result);
    print_i(result);
    return 0;
}

int weightedReduce(int n) {
    int i;
    int sum;
    int temp;
    i = 0;
    sum = 0;
    while (i < n) {
        temp = arr[i] * weight;
        sum = sum + temp;
        i = i + 1;
    }
    return sum;
}

int adjustResult(int val) {
    int finalVal;
    finalVal = val - adjust;
    return finalVal;
}
