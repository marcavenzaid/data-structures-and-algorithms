/* Insertion sort: Sort an array the same way as if we are sorting cards in our
 * hands.
 *
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 */

/**
 * author: marcavenzaid
 * created: 2018-09-15-00.52
 */

#include <bits/stdc++.h>

using namespace std;

void insertion_sort(int a[], int n) {
    for (int i = 1; i < n; ++i) {
        int key = a[i];
        int j = i-1;
        while (j >= 0 && a[j] > key) {
            a[j+1] = a[j];
            --j;
        }
        a[j+1] = key;
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int a[] = {4, 7, 5, 2, 1, 0, 8, 9, 4, 6};
    int n = sizeof(a) / sizeof(a[0]);
    insertion_sort(a, n);
    for (int i = 0; i < n; ++i) {
        cout << a[i] << " ";
    }
    return 0;
}
