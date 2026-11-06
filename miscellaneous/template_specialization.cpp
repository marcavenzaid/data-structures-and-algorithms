#include <iostream>

template<class T>
class MyClass {

    public:
        MyClass (T x) {
            std::cout << x <<" -  not a char\n";
        }
};

template<>
class MyClass<char> {

    public:
        MyClass (char x) {
        std::cout << x <<" is a char!\n";
    }
};

int main() {
    MyClass<int> x(7);
    MyClass<double> y(3.14);
    MyClass<char> z('g');

    return 0;
}
