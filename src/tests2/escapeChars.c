int main() {
    char nl;  // newline: \n
    char tb;  // horizontal tab: \t
    char cr;  // carriage return: \r
    char bs;  // backslash: \\
    char sq;  // single quote: \'
    char dq;  // double quote: \"
    char ff;  // form feed: \f
    char bb;  // backspace: \b
    char vt;  // vertical tab: \v
    char al;  // bell (alert): \a
    char nz;  // null character: \0

    nl = '\n';
    tb = '\t';
    cr = '\r';
    bs = '\\';
    sq = '\'';
    dq = '\"';
    ff = '\f';
    bb = '\b';
    vt = '\v';
    al = '\a';
    nz = '\0';


    return nl + tb + cr + bs + sq + dq + ff + bb + vt + al + nz;
}
