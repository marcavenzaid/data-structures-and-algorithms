#include <iostream>
#include <sstream>
#include <vector>

std::string to_string(int num) {
    std::ostringstream oss;
    oss << num;
    return oss.str();
}

int main()
{
    std::string str;
    std::vector<int> vect_input;
    std::vector< std::vector<int> > chutes;

    std::cin >> str;
    std::stringstream ss_input;
    ss_input.str(std::string());
    ss_input.str(str);

    int integer;

    while (ss_input >> integer) {
        vect_input.push_back(integer);

        if(ss_input.peek() == ',' || ss_input.peek() < 1 || ss_input.peek() > 50) {
            ss_input.ignore();
        }
    }

    chutes.resize(vect_input[1], std::vector<int>(vect_input[0], 0));

    for(int i = 0; i < vect_input[0]; i++) {
        std::cin >> str;
        std::stringstream chutes_m;
        chutes_m.str(std::string());
        chutes_m.str(str);

        for(int j = 0; chutes_m >> integer; j++) {
            chutes[j][i] = integer;

            if(chutes_m.peek() == ',') {
                chutes_m.ignore();
            }
        }
    }

    std::string largest_int = "";
    std::vector<int> col_n_iterator;
    col_n_iterator.resize(vect_input[1], vect_input[1] - 1);

    for(std::size_t i = 0; i < chutes.size() * chutes[0].size(); i++) {
        int max_chute_n = -1;
        int max_chute_col = 0;

        for(std::size_t col = 0; col < chutes.size(); col++) {
            if(col_n_iterator[col] < 0) {
                continue;
            }

            if(chutes[col_n_iterator[col]][col] > max_chute_n) {
                max_chute_n = chutes[col_n_iterator[col]][col];
                max_chute_col = col;
            }
        }

        col_n_iterator[max_chute_col] = col_n_iterator[max_chute_col] - 1;
//        std::cout << max_chute_n;
        largest_int += to_string(max_chute_n);
    }

    std::cout << std::endl;
    std::cout << largest_int << std::endl;

    return 0;
}
