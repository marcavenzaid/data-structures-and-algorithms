/* Bubble sort: Sorts an array by repeatedly swapping adjacent elements if it is
 * not in the right order.
 *
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 *
 * a = input array.
 * N = number of items.
 */

/**
 * author: marcavenzaid
 * created: 2018-07-04-19.51
 */

#include <iostream>

void bubble_sort(int a[], const int N) {
    for (int i = 0; i < N - 1; i++) {
        for (int j = 0; j < N-i-1; j++) {
            if(a[j] > a[j+1]) {
                std::swap(a[j], a[j+1]);
            }
        }
    }
}

int main() {
    int a[] = {4, 7, 5, 2, 1, 0, 8, 9, 4, 6};
    const int N = sizeof(a) / sizeof(a[0]);
    for (int i = 0; i < N; i++) {
        std::cout << a[i] << " ";
    }
    bubble_sort(a, N);
    std::cout << std::endl;
    for (int i = 0; i < N; i++) {
        std::cout << a[i] << " ";
    }
    return 0;
}
