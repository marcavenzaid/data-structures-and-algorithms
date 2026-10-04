/* Euclidean algorithm: Algorithm for computing the GCD (greatest common
 * divisor) of two numbers (largest number that divides the two numbers without
 * leaving a remainder).
 *
 * Time complexity: O(log min(a, b))
 */

/**
 * author: marcavenzaid
 * created: 2018-09-05-20.55
 */

#include <bits/stdc++.h>

using namespace std;

int gcd(int a, int b) {
    if (a == 0) {
        return b;
    }
    gcd(b % a, a);
}

int gcd_array(int a[], int n) {
    int result = a[0];
    for (int i = 1; i < n; ++i) {
        result = gcd(result, a[i]);
    }
    return result;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int a = 24;
    int b = 16;
    cout << gcd(a, b) << "\n";
    int n = 5;
    int arr[n] = {2, 4, 6, 8, 16};
    cout << gcd_array(arr, n) << "\n";
    return 0;
}
