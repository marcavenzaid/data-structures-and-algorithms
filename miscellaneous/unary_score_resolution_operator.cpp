// Unary Scope Resolution Operator

#include <iostream>

using namespace std;

class Number {
public:
    int n = 5;
};

int foo = 0;

int main()
{
    int foo = 1;

    cout << foo << endl;    // outputs the local variable.
    cout << ::foo << endl;  // outputs the global variable.
    ::foo = 7;
    cout << ::foo << endl;

    return 0;
}
