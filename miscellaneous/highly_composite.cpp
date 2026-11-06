#include <cstdlib>
#include <iostream>
#include <vector>

std::vector<int> get_divisors(int n);
bool is_highly_composite(int n);

int main()
{
    int n;

    std::cout << "Enter a number: ";
    std::cin >> n;

    // Check input.
    if(std::cin.fail()) {
        std::cerr << "Input Error!" << std::endl;
        exit(EXIT_FAILURE);
    }

    std::cout << "Input: " << n << std::endl;

    std::vector<int> divisors = get_divisors(n);

    std::cout << "Conversion: ";

    for(std::vector<int>::const_iterator i = divisors.begin(); i != divisors.end(); ++i) {
        std::cout << *i << " ";
    }

    std::cout << "\n";

    if(is_highly_composite(n)) {
        std::cout << "Output: Highly Composite" << std::endl;
    } else {
        std::cout << "Output: Not Highly Composite" << std::endl;
    }

    return 0;
}

std::vector<int> get_divisors(int n) {
    std::vector<int> divisors;

    for(int i = 1; i < n; i++) {
        if(n % i == 0) {
            divisors.push_back(i);
        }
    }

    return divisors;
}

bool is_highly_composite(int n) {
    std::vector<int> divisors = get_divisors(n);
    int total_divisors = divisors.size();

    for(int i = 1; i < n; i++) {
         std::vector<int> temp_divisors = get_divisors(i);

         if(temp_divisors.size() >= total_divisors) {
            return false;
         }
    }

    return true;
}
