#include <cmath>
#include <cstdlib>
#include <iostream>
#include <vector>

void validate_input(int n, int k, const std::vector<int> &a, const std::vector<int> &b);
bool test_range(int value, int min_value, int max_value);
int get_min_product(int n, int k, const std::vector<int> &a, const std::vector<int> &b);

int main() {
    int n, k;
    std::vector<int> a;
    std::vector<int> b;

    std::cin >> n >> k;

    // Get inputs for a.
    for(int i = 0; i < n; i++) {
        int input_a;
        std::cin >> input_a;
        a.push_back(input_a);
    }

    // Get inputs for b.
    for(int i = 0; i < n; i++) {
        int input_b;
        std::cin >> input_b;
        b.push_back(input_b);
    }

    validate_input(n, k, a, b);

    int min_product = get_min_product(n, k, a, b);

    std::cout << min_product << std::endl;

    return EXIT_SUCCESS;
}

void validate_input(int n, int k, const std::vector<int> &a, const std::vector<int> &b) {
    bool valid_n = test_range(n, 1, pow(10, 5));
    bool valid_k = test_range(k, 0, pow(10, 9));
    bool valid_a = true;
    bool valid_b = true;

    // Check each input a.
    for(int i = 0; i < a.size(); i++) {
        if(std::abs(a[i]) > pow(10, 5)) {
            valid_a = false;
        }
    }

    // Check each input b.
    for(int i = 0; i < b.size(); i++) {
        if(std::abs(b[i]) > pow(10, 5)) {
            valid_b = false;
        }
    }

    // Validate.
    if(!(valid_n && valid_k && valid_a && valid_b)) {
        std::cerr << "Input error!" << std::endl;
        exit(EXIT_FAILURE);
    }
}

bool test_range(int value, int min_value, int max_value) {
    return value >= min_value && value <= max_value;
}

int get_min_product(int n, int k, const std::vector<int> &a, const std::vector<int> &b) {
    int min_product = 0;

    for(int i = 0; i < n; i++) {
        if(i == 0) {
            if(a[i] * b[i] > 0 && a[i] >= 0) {
                min_product += (a[i] - (2 * k)) * b[i];
            } else if(a[i] * b[i] > 0 && a[i] < 0) {
                min_product += (a[i] + (2 * k)) * b[i];
            } else if(a[i] * b[i] < 0 && a[i] >= 0) {
                min_product += (a[i] + (2 * k)) * b[i];
            } else if(a[i] * b[i] < 0 && a[i] < 0) {
                min_product += (a[i] + (2 * k)) * b[i];
            }
        } else {
            min_product += a[i] * b[i];
        }
    }

    for(int i = 1; i < n; i++) {
        int temp_min_product = 0;

        for(int j = 0; j < n; j++) {
            if(j == i) {
                if(a[j] * b[j] > 0 && a[j] >= 0) {
                    temp_min_product += (a[j] - (2 * k)) * b[j];
                } else if(a[j] * b[j] > 0 && a[j] < 0) {
                    temp_min_product += (a[j] + (2 * k)) * b[j];
                } else if(a[j] * b[j] < 0 && a[j] >= 0) {
                    temp_min_product += (a[j] + (2 * k)) * b[j];
                } else if(a[j] * b[j] < 0 && a[j] < 0) {
                    temp_min_product += (a[j] + (2 * k)) * b[j];
                }
            } else {
                temp_min_product += a[j] * b[j];
            }
        }

        if(temp_min_product < min_product) {
            min_product = temp_min_product;
        }
    }

    return min_product;
}

/* Problem: Minimum Product Array
 *
 * The task is to find the minimum sum of Products of two arrays of the same
 * size, given that k modifications are allowed on the first array. In each
 * modification, one array element of the first array can either be increased
 * or decreased by 2.
 *
 * Note- the product sum is Summation (A[i]*B[i]) for all i from 1 to n where
 * n is the size of both arrays.
 *
 * Input Format:
 * 1. First line of the input contains n and k delimited by whitespace
 * 2. Second line contains the Array A (modifiable array) with its values
 *    delimited by spaces
 * 3. Third line contains the Array B (non-modifiable array) with its values
 *    delimited by spaces
 *
 * Output Format:
 * Output the minimum sum of products of the two arrays
 *
 * Constraints:
 * 1. 1 <= N <= 10^5
 * 2. 0 <= |A[i]|, |B[i]| <= 10^5
 * 3. 0 <= K <= 10^9
 */