int binomRecHelper(int n, int k) {
   int res;
   int tempA;
   int tempB;
   print_s((char*)"Entering binomRecHelper: n = ");
   print_i(n);
   print_s((char*)" k = ");
   print_i(k);
   print_s((char*)"\n");
   if (k == 0) {
      res = 1;
   } else {
      if (n == k) {
         res = 1;
      } else {
         tempA = binomRecHelper(n - 1, k - 1);
         tempB = binomRecHelper(n - 1, k);
         res = tempA + tempB;
      }
   }
   print_s((char*)"Exiting binomRecHelper: n = ");
   print_i(n);
   print_s((char*)" k = ");
   print_i(k);
   print_s((char*)" returning ");
   print_i(res);
   print_s((char*)"\n");
   return res;
}
int binomRec() {
   int n;
   int k;
   n = 25;
   k = 12;
   return binomRecHelper(n, k);
}
int main() {
   int r;
   r = binomRec();
   print_i(r);
   return r;
}
