void print_s(char* s);
void print_i(int i);
void print_c(char c);
char read_c();
int read_i();
void* mcmalloc(int size);

int main() {
    print_s((char*)"Hello, World!");
    print_i(123);
    print_c('Z');
    return 0;
}
