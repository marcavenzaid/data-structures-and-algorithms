/* Bubble sort optimized: Sorts an array by repeatedly swapping adjacent
 * elements if it is not in the right order. The algorithm stops when no swap is
 * made in a step or simply when the array is already sorted.
 *
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 *
 * a = input array.
 * N = number of items.
 */

/**
 * author: marcavenzaid
 * created: 2018-07-04-22.50
 */

#include <iostream>

void bubble_sort(int a[], const int N) {
    bool swapped;
    for (int i = 0; i < N - 1; i++) {
        swapped = false;
        for (int j = 0; j < N-i-1; j++) {
            if(a[j] > a[j+1]) {
                std::swap(a[j], a[j+1]);
                swapped = true;
            }
        }
        if(!swapped) {
            break;
        }
    }
}

int main() {
    int a[] = {3, 2, 0, 1, 4, 3, 6, 7, 8, 9};
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
