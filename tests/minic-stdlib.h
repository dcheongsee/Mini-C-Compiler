#include <stdio.h>
#include <stdlib.h>

void print_s(const char* s) {
  fprintf(stdout,"%s",s);
}

void print_i(int i) {
  fprintf(stdout,"%d",i);
}

void print_c(char c) {
  fprintf(stdout,"%c",c);
}

char read_c() {
  char c;
  fscanf(stdin, "%c", &c);
  return c;
}

int read_i() {
  int i;
  fscanf(stdin, "%d", &i);
  return i;
}

void* mcmalloc(int size) {
  return malloc(size);
}
