int foo() {
    return 1;
}

int main() {
    {
        int foo;  // shadows any outer variable but does not conflict with global foo
        foo = 2;
    }
    return foo();  // calls the global foo()
}
