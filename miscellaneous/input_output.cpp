#include <iostream>
#include <fstream>

int main() {
    std::ifstream input("data.in");
    std::ofstream output("data.out");

    int a, b, sum;

    input >> a >> b;
    sum = a + b;

    output << sum;

    return 0;
}
