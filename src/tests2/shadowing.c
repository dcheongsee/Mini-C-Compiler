void print_i(int i);
void print_c(char c);

struct B {
    int bVal;
    struct B* next;
};

struct A {
    struct B bArray[2];
    char c;
};

struct C {
    int cc;
};

int globalVar[3];

int foo(char c);
struct B* bar(struct A param);

void testFunc(int arr[2]);

int main() {
    struct A localA;

    int globalVar;
    struct B* bPtr;
    globalVar = 10;

    localA.bArray[0].bVal = globalVar;

    localA.bArray[0].next = (struct B*) localA.bArray;


    bPtr = &localA.bArray[1];
    (*bPtr).bVal = 2;
    // or equivalently: bPtr->bVal = 2;

    globalVar = globalVar + 1;

    {
        char globalVar; // shadows the int globalVar
        globalVar = (char)65; // cast int -> char is valid
        print_c(globalVar);   // should print 'A'
    }

    {
        int i;
        i = 0;
        while(i < 5) {
            i = i + 1;
            if(i == 2) {
                continue; // skip printing 2
            }
            if(i == 4) {
                break; // exit loop when i == 4
            }
            print_i(i); // prints 1, 3
        }
    }

    {
        char c;
        int x;
        c = 'Z';

        x = (int) c; // explicit cast char -> int
        print_i(x);  // prints ASCII code of 'Z'
    }

    foo('t');

    bar(localA);

    {
        int localArr[2];
        localArr[0] = 42;
        localArr[1] = 99;
        testFunc(localArr);
    }

    {
        struct C* cPtr;
        cPtr = (struct C*) bPtr; // struct B* -> struct C* cast is allowed
    }

    return 0;
}

int foo(char c) {
    if((int)c == 0) {
        return 1;
    } else {
        {
            int c;   // overshadow the function parameter c
            c = -5;  // unary minus test
            return c;
        }
    }
}

struct B* bar(struct A param) {
    param.bArray[1].bVal = 999;
    print_i(param.bArray[1].bVal);
    return (struct B*) param.bArray;
}


void testFunc(int arr[2]) {
    arr[1] = 7;
    arr[2] = 9; // out of bounds at runtime, but not a compile-time type error
}
