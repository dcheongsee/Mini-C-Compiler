/*
expected output:
4100003
Tricky edge jumps test passed
*/
int empty_func();
int tricky_func(int a, int b);

int x;
int y;
int z;
int temp;
int final_result;
int i;
int j;
int sum;
int arr[200];

int empty_func() {
  return 0;
}

int tricky_func(int a, int b) {
  int result;
  int dead;  /* dead computation */
  if ((a - b) > 0) {
    result = a - b + 7;
    dead = result * 123;  /* dead code */
  } else {
    result = b - a + 3;
  }
  return result;
}

int main() {
  x = 100;
  y = 150;
  z = 200;
  temp = empty_func();
  temp = tricky_func(x, y);
  sum = 0;
  i = 0;
  while(i < 200) {
    arr[i] = i + 1;
    i = i + 1;
  }
  i = 0;
  while(i < 200) {
    j = 0;
    while(j < 200) {
      temp = tricky_func(arr[i], z);
      sum = sum + temp;
      j = j + 1;
    }
    i = i + 1;
  }
  final_result = sum + temp;
  print_i(final_result);
  print_s((char*)"Tricky edge jumps test passed");
  return 0;
}
