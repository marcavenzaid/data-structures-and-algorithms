#include <cstdlib>
#include <iostream>
#include <sstream>

std::string to_binary(int n);
int parse_int(std::string str);
std::string to_string(int num);
std::string reverse_str(std::string str);

int main()
{
    int n;

    std::cout << "Enter a number: ";
    std::cin >> n;

    std::cout << to_binary(n);

    return EXIT_SUCCESS;
}

std::string to_binary(int n) {
    int dec = n;
    std::string binary = "";


    while(dec > 0) {
        binary += to_string(dec % 2);
        dec /= 2;
    }

    return reverse_str(binary);
}

// Converts a string to integer.
int parse_int(std::string str) {
    return atoi(str.c_str());
}

// Converts an integer to string.
std::string to_string(int num) {
    std::ostringstream oss;
    oss << num;
    return oss.str();
}

// Returns the reverse of the given string.
std::string reverse_str(std::string str) {
    return std::string(str.rbegin(), str.rend());;
}
