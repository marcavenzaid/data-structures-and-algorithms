#include <iostream>

template<typename T>
class Stack {
    public:
        Stack(int MAX);
        ~Stack();
        void push(T x);
        void pop();
        bool is_empty();
        T top();

    private:
        T* a;
        const int MAX;
        int i;
};

template<typename T>
Stack<T>::Stack(int MAX)
    : MAX(MAX) {
    a = new T[MAX]();
    i = -1;
}

template<typename T>
Stack<T>::~Stack() {
    delete[] a;
}

template<typename T>
void Stack<T>::push(T x) {
    if (i < MAX) {
        a[++i] = x;
    }
}

template<typename T>
void Stack<T>::pop() {
    if (!is_empty()) {
        a[i--];
    }
}

template<typename T>
bool Stack<T>::is_empty() {
    return i < 0;
}

template<typename T>
T Stack<T>::top() {
    return a[i];
}

int main() {
    Stack<int> stack(3);
    stack.push(2);
    stack.push(3);
    stack.push(5);

    std::cout << stack.top() << std::endl;
    stack.pop();
    std::cout << stack.top() << std::endl;
    stack.pop();
    std::cout << stack.top() << std::endl;

    return 0;
}
