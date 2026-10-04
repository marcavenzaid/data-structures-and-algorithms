/* Sub-arrays sum: Given an array a of size n, find the sum of all sub-arrays.
 *
 * Time complexity: O(n)
 */

/**
 * author: marcavenzaid
 * created: 2018-08-12-23.47
 */

#include <bits/stdc++.h>

using namespace std;

int subarrays_sum(int a[], int n) {
    int sum = 0;
    for (int i = 0; i < n; ++i) {
        sum += a[i] * (i+1) * (n-i);
    }
    return sum;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int a[] = {1, 2, 3};
    int n = sizeof(a) / sizeof(a[0]);
    cout << subarrays_sum(a, n);
    return 0;
}
