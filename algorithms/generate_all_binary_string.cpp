/* Generate all binary string: Self-explanatory
 *
 * Time complexity: O(n 2^n)
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

void generate_all_binary_string(int a[], int n, int i) {
    if (i == n) {
        print_array(a, n);
        return;
    }
    a[i] = 0;
    generate_all_binary_string(a, n, i + 1);
    a[i] = 1;
    generate_all_binary_string(a, n, i + 1);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n = 3;
    int a[n];
    generate_all_binary_string(a, n, 0);
    return 0;
}