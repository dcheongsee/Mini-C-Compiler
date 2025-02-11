int main() {
    char a;

    a = (('\n' + '\t') * '\r') - ('\\' - '\'') + '\"';
    return a;
}
