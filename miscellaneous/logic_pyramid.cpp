#include <cstdlib>
#include <iostream>

void validate_input(int n);
bool test_range(int value, int min_value, int max_value);
void draw_logic_pyramid(int n);
int get_hexagonal_num(int n);
int get_int_length(int n);

int main() {
    int n;

    std::cout << "Enter number of rows: ";
    std::cin >> n;

    validate_input(n);

    draw_logic_pyramid(n);

    return EXIT_SUCCESS;
}

void validate_input(int n) {
    bool valid_n = test_range(n, 1, 14);

    if(!valid_n) {
        std::cerr << "Input error!" << std::endl;
        exit(EXIT_FAILURE);
    }
}

bool test_range(int value, int min_value, int max_value) {
    return value >= min_value && value <= max_value;
}

void draw_logic_pyramid(int num_of_rows) {
    // 6, 28, 66, 120, 190, 276, are even hexagonal numbers.

    int n = 1;

    for (int i = 0; i <= num_of_rows; i++) {
        for (int pad = 0; pad < num_of_rows-i; pad++) {
            std::cout << "   ";
        }

        for (int j = 0; j < i; j++) {
            int hexagonal_num = get_hexagonal_num(n*2); // Get even (n*2).
            n++;

            std::string hexagonal_num_str = "";

            if(get_int_length(hexagonal_num) < 5) {
                for(int k = 0; k < 5 - get_int_length(hexagonal_num); k++) {
                    hexagonal_num_str += "0";
                }
            }

            std::cout << hexagonal_num_str << hexagonal_num << " ";
        }

        std::cout << '\n';
    }
}

int get_hexagonal_num(int n) {
    return n * (2*n - 1);
}

int get_int_length(int n) {
    int counter = 0;

    while(n) {
        n = n/10;
        counter++;
    }
    return (counter);
}

/* Problem: Logic Pyramid
 *
 * Identify the logic behind the series: 6 28 66 120 190 276....
 *
 * The numbers in the series should be used to create a Pyramid. The base of
 * the Pyramid will be the widest and will start converging towards the top
 * where there will only be one element. Each successive layer will have one
 * number less than that on the layer below it. The width of the Pyramid is
 * specified by an input parameter N. In other words there will be N numbers
 * on the bottom layer of the pyramid.
 *
 * The Pyramid construction rules are as follows
 * 1. First number in the series should be at the top of the Pyramid
 * 2. Last N number of the series should be on the bottom-most layer of the
 *    Pyramid, with Nth number being the right-most number of this layer.
 * 3. Numbers less than 5-digits must be padded with zeros to maintain the
 *    sanctity of a Pyramid when printed. Have a look at the examples below to
 *    get a pictorial understanding of what this rule actually means.
 *
 * Input Format:
 * First line of input will contain number N that corresponds to the width of
 * the bottom-most layer of the Pyramid.
 *
 * Output Format:
 * The Pyramid constructed out of numbers in the series as per stated
 * construction rules
 *
 * Constraints:
 * 1. 0 < N <= 14
 */