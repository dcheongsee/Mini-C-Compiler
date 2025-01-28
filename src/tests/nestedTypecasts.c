struct Inner {
    int val;
};

int main() {
    int x;
    struct Inner* p;
    x = 42;
    p = (struct Inner*)(char*)(void*)&x; // Typecast chain: &x → void* → char* → struct Inner*
    return (*p).val;
}