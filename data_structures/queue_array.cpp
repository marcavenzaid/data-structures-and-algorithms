#include <iostream>

template<typename T>
class Queue {
    public:
        Queue(int MAX);
        ~Queue();
        void push_back(T x);
        void pop_front();
        T front();
        T back();
        bool is_empty();
        bool is_full();

    private:
        const int MAX;
        T* a;
        int i_front;
        int i_back;
};

template<typename T>
Queue<T>::Queue(const int MAX)
    : MAX(MAX) {
    a = new T[MAX]();
    i_front = -1;
    i_back = -1;
}

template<typename T>
Queue<T>::~Queue() {
    delete[] a;
}

template<typename T>
void Queue<T>::push_back(T x) {
    if (!is_full()) {
        if(is_empty()) {
            i_front = 0;
        }
        i_back = (i_back + 1) % MAX;
        a[i_back] = x;
    }
}

template<typename T>
void Queue<T>::pop_front() {
    if (i_front == i_back) {
        i_front = -1;
        i_back = -1;
    }
    if (!is_empty()) {
        i_front = (i_front + 1) % MAX;
    }
}

template<typename T>
T Queue<T>::front() {
    return a[i_front];
}

template<typename T>
T Queue<T>::back() {
    return a[i_back];
}

template<typename T>
bool Queue<T>::is_empty() {
    return i_front == -1;
}

template<typename T>
bool Queue<T>::is_full() {
    return (i_back + 1) % MAX == i_front;
}

int main() {
    Queue<int> queue(5);
    queue.push_back(2);
    queue.push_back(3);
    queue.push_back(5);

    std::cout << queue.front() << std::endl;
    queue.pop_front();
    std::cout << queue.front() << std::endl;
    queue.pop_front();
    std::cout << queue.front() << std::endl;
    queue.pop_front();

    return 0;
}
