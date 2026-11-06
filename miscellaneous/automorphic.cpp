#include <cmath>
#include <cstdlib>
#include <iostream>
#include <sstream>

bool is_automorphic(int n);
int get_int_length(int n);
std::string to_string(int num);
int parse_int(std::string str);

int main()
{
    int n;
    std::string output;

    std::cout << "Enter a number: ";
    std::cin >> n;

    // Check input.
    if(std::cin.fail()) {
        std::cerr << "Input Error!" << std::endl;
        exit(EXIT_FAILURE);
    }

    if(is_automorphic(n)) {
        output = "Automorphic";
    } else {
        output = "Not Automorphic";
    }

    std::cout << "Input: " << n << std::endl;
    std::cout << "Computation: " << n << "^2 = " << pow(n, 2) << std::endl;
    std::cout << "Output: " << output << std::endl;

    return EXIT_SUCCESS;
}

bool is_automorphic(int n) {
    int n_sqd = pow(n, 2);
    int n_length = get_int_length(n);
    int n_sqd_length = get_int_length(n_sqd);
    std::string n_sqd_str = to_string(n_sqd);
    std::string n_sqd_substr = n_sqd_str.substr(n_sqd_length - n_length, n_length);

    if(n == parse_int(n_sqd_substr)) {
        return true;
    }

    return false;
}

// Returns the length of an integer.
int get_int_length(int n) {
    int counter = 0;

    while(n) {
        n = n/10;
        counter++;
    }
    return (counter);
}

// Converts an integer to string.
std::string to_string(int num) {
    std::ostringstream oss;
    oss << num;
    return oss.str();
}

// Converts a string to integer.
int parse_int(std::string str) {
    int parsed;
    std::stringstream(str) >> parsed;
    return parsed;
}

/* Problem: Automorphic
 *
 * A number n is called automorphic if n^2 ends with n. For example, 376 is
 * automorphic since 376^2 = 141376. Write a program that determines if the
 * entered number is automorphic or  not and shows a computation.
 *
 * Example:
 *     Input: 5
 *     Computation: 5^2 = 25
 *     Output: Automorphic
 *
 *     Input: 4
 *     Computation: 4^2 = 8
 *     Output: Not Automorphic
 */