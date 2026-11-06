#include <cmath>
#include <cstdlib>
#include <iostream>
#include <vector>

std::vector<int> get_divisors(int n) {
    std::vector<int> divisors;

    for(int i = 1; i <= n; i++) {
        if(n % i == 0) {
            divisors.push_back(i);
        }
    }

    return divisors;
}

int main()
{
    int n, k;
    int target_prod = 1;

    std::cin >> n >> k;

    // Get inputs for vector_n.
    for(int i = 0; i < n; i++) {
        int input;
        std::cin >> input;

        if(input > 5000 || input < 0) {
            std::cerr << "Input Error!" << std::endl;
            exit(EXIT_FAILURE);
        }

        target_prod *= input;
    }

    if(target_prod > pow(10, 9)) {
        std::cerr << "Input Error!" << std::endl;
        exit(EXIT_FAILURE);
    }

    if(std::cin.fail()) {
        std::cerr << "Input Error!" << std::endl;
        exit(EXIT_FAILURE);
    }

    int num_of_ways = 0;
    std::vector<int> divisors = get_divisors(target_prod);

    for(std::size_t i = divisors.size() - 1; i >= 1; i--) {
        int product = 0;

        for(int i2 = i; i2 >= 0; i2--) {
            product = divisors[i] * divisors[i2];

            if (product > target_prod){
                continue;
            }

            if(product < target_prod) {
                for(int counter = 1; counter < k; counter++) {
                    product *= divisors[i2];

                    if(product >= target_prod) {
                        break;
                    }
                }
            }

            if(product == target_prod) {
                num_of_ways++;
            }
        }
    }

    std::cout << num_of_ways << std::endl;

    return 0;
}
