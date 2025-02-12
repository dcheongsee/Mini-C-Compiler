int main() {
    char a;

    a = (('\n' + '\t') * '\n') - ('\\' - '\'') + '\"';
    return a;
}
