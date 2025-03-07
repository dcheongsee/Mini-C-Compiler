struct Greeting {
    char text[30];
};

struct Greeting concatGreeting(char s1[20], char s2[20]) {
    int i;
    int j;
    struct Greeting g;

    // copy s1 into g.text
    i = 0;
    while (i < 20 && s1[i] != '\0') {
        g.text[i] = s1[i];
        i = i + 1;
    }
    // append a space
    if (i < 30) {
        g.text[i] = ' ';
        i = i + 1;
    }
    // copy s2 into g.text
    j = 0;
    while (j < 20 && s2[j] != '\0' && i < 30 - 1) {
        g.text[i] = s2[j];
        i = i + 1;
        j = j + 1;
    }
    // null-terminate the string
    g.text[i] = '\0';
    return g;
}

void main() {
    struct Greeting greet;
    // call concatGreeting with two string literals
    greet = concatGreeting((char*)"Hello", (char*)"World!");
    print_s(greet.text);
}
