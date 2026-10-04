/* Generate combination k-ary strings: prints all combination of k elements of
 * size n.
 *
 * Time complexity: O(k^n)
 */

/**
 * author: marcavenzaid
 * date created: October 02, 2018
 */

#include <bits/stdc++.h>

using namespace std;

void print_array(int a[], int n) {
    for (int i = 0; i < n; ++i) {
        cout << a[i];
    }
    cout << "\n";
}

void k_ary(int a[], int k, int b[], int n, int i) {
    if (i == n) {
        print_array(b, n);
        return;
    }
    for (int j = 0; j < k; ++j) {
        b[i] = a[j];
        k_ary(a, k, b, n, i + 1);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int k = 2, n = 3;
    int a[k] = {1, 2};
    int b[n];
    k_ary(a, k, b, n, 0);
    return 0;
}