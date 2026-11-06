#include <iostream>
#include <stack>

bool is_syntactical_correct(std::string expression) {
    std::stack<int> b_stack;

    for (int i = 0; i < expression.length(); i++) {
        char c = expression.at(i);
        if (c == '{' || c == '[' || c == '(') {
            b_stack.push(c);
        } else {
            if(b_stack.empty()) {
                return false;
            }
            char top = b_stack.top();
            if (c - top == 1 || c - top == 2) {
                b_stack.pop();
            } else {
                return false;
            }
        }
    }
    return (b_stack.empty()) ? true : false;
}

int main() {
    char o[] = {'(', '[', '{'};
    char c[] = {')', ']', '}'};
    std::string expression = "{{()(())[]}}";

    if (is_syntactical_correct(expression)) {
        std::cout << "Syntactically Correct!" << std::endl;
    } else {
        std::cout << "Syntactically Incorrect!" << std::endl;
    }

    return 0;
}
