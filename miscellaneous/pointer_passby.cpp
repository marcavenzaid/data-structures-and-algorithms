#include <iostream>

using namespace std;

void pass_by_pointer_swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void pass_by_reference_swap(int &a, int &b) {
    int temp = a;
    a = b;
    b = temp;
}

struct demo {
    int a;
};

int main()
{
    // Example of pass by pointer and pass by reference.

    int a = 1, b = 2;

    cout << "Pass by pointer" << endl;
    cout << a << ' ' << b << endl;

    pass_by_pointer_swap(&a, &b);

    cout << a << ' ' << b << endl;

    a = 1;
    b = 2;

    cout << "Pass by reference" << endl;
    cout << a << ' ' << b << endl;

    pass_by_reference_swap(a, b);

    cout << a << ' ' << b << endl;

    // Demonstrate difference between reference and pointer.
    int x = 5;
    int y = 6;
    demo d;

    int *p;
    p =  &x;
    p = &y;                             // 1. Pointer reintialization allowed
    int &r = x;
    // &r = y;                          // 1. Compile Error
    r = y;                              // 1. x value becomes 6

    p = NULL;
    // &r = NULL;                       // 2. Compile Error

    p++;                                // 3. Points to next memory location
    r++;                                // 3. x values becomes 7

    cout << &p << " " << &x << endl;    // 4. Different address
    cout << &r << " " << &x << endl;    // 4. Same address

    demo *q = &d;
    demo &qq = d;

    q->a = 8;
    // q.a = 8;                         // 5. Compile Error
    qq.a = 8;
    // qq->a = 8;                       // 5. Compile Error

    cout << p << endl;                  // 6. Prints the address
    cout << r << endl;                  // 6. Print the value of x

    return 0;
}
