// clang -emit-llvm -c sample.c -o sample.bc
int main() {
    int a = 2;
    a = a + 5;
    return a;
}
