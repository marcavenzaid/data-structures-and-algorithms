#include <iostream>

int main()
{
    int foo = 7;

    std::cout << &foo << std::endl;     // Outputs the memory address of the variable foo.

    int *p;                             // Create pointer p.

    std::cout << p << std::endl;        // Outputs pointer p which is a memory address.

    p = &foo;                            // Set pointer p to memory address of variable foo.

    std::cout << p << std::endl;        // Output is a memory address the same as foo.

    std::cout << *p << std::endl;       // Outputs value pointed to by p.

    std::cout << "----------------------------------------------" << std::endl;

    int value1, value2;
    int * pointer;

    pointer = &value1;
    *pointer = 1;
    pointer = &value2;
    *pointer = 2;

    std::cout << "value1 = " << value1 << std::endl;
    std::cout << "value2 = " << value2 << std::endl;

    std::cout << "----------------------------------------------" << std::endl;

    int x = 7;
    int *pointer2;

    pointer = &x;
    std::cout << pointer << std::endl;

    return 0;
}


