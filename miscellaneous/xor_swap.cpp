#include <iostream>

void xor_swap(int &x, int &y) {
    if(&x != &y) {
        x = x ^ y;
        y = y ^ x;
        x = x ^ y;
    }
}

int main() {

    // Input
    int x, y;
    std::cout << "Enter x: ";
    std::cin >> x;
    std::cout << "Enter y: ";
    std:: cin >> y;

    std::cout << "Before swap:" << std::endl;
    std::cout << "x = " << x << std::endl;
    std::cout << "y = " << y << std::endl;

    xor_swap(x, y);

    // Output
    std::cout << "After swap:" << std::endl;
    std::cout << "x = " << x << std::endl;
    std::cout << "y = " << y << std::endl;

    return 0;
}