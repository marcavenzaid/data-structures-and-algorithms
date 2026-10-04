/* Counting sort: Sorts an array by counting how many times each unique value
 * appeared and using arithmetic to determine the position of each value in the
 * output array.
 *
 * The running time of this algorithm is based on the number of items and the
 * difference between the maximum and minimum values, so it is only recommended
 * to use in cases where the variation in values is not significantly greater
 * than the number of items.
 *
 * Time complexity: O(n+k)
 * Space complexity: O(k)
 *
 * a = input array.
 * N = number of items.
 * K = maximum value.
 */

/**
 * author: marcavenzaid
 * created: 2018-06-28-16.01
 */

#include <iomanip>
#include <iostream>
#include <algorithm>

void counting_sort(int a[], const int N, const int K) {
    int frequencies[K + 1] = {};
    for (int i = 0; i < N; i++) {
        frequencies[a[i]]++;
    }
    for (int i = 1; i <= K; i++) {
        frequencies[i] += frequencies[i - 1];
    }
    int sorted_arr[N] = {};
    for (int i = 0; i < N; i++) {
        sorted_arr[frequencies[a[i]] - 1] = a[i];
        frequencies[a[i]]--;
    }
    std::copy(sorted_arr, sorted_arr + N, a);
}

int main() {
    int a[] = {0, 4, 4, 1, 6, 3, 7, 7, 7, 9, 15};
    const int N = sizeof(a) / sizeof(a[0]);
    const int K = *std::max_element(a, a + N);
    std::cout << " Input Data: ";
    for (int i = 0; i < N; i++) {
        std::cout << std::setw(2) << a[i] << " ";
    }
    counting_sort(a, N, K);
    std::cout << "\n     Sorted: ";
    for (int i = 0; i < N; i++) {
        std::cout << std::setw(2) << a[i] << " ";
    }
    std::cout << std::endl;
    return 0;
}
