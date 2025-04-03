int getInput() {
   int x;
   x = 0;  // This function is expected to generate a readInt syscall.
   return x;
}
int computeHeavy() {
   int a;
   int b;
   int c;
   int d;
   int e;
   int f;
   int g;
   int h;
   int temp1;
   int temp2;
   int temp3;
   int temp4;
   int temp5;
   int temp6;
   a = 10;
   b = 20;
   c = 30;
   d = 40;
   e = 50;
   f = 60;
   g = 70;
   h = 80;
   temp1 = a + b;
   temp2 = c + d;
   temp3 = e + f;
   temp4 = g + h;
   temp5 = temp1 * temp2;
   temp6 = temp3 * temp4;
   return temp5 + temp6;
}
int main() {
   int result;
   int input;
   int total;
   result = computeHeavy();
   input = getInput();
   total = result + input;
   print_i(total);
   return total;
}
