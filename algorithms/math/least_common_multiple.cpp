/* Least common multiple: a number that is a multiple of 2 or more numbers.
 * /

/**
 * author: marcavenzaid
 * date created: September 25, 2018
 */

#include <bits/stdc++.h>

using namespace std;

int gcd(int a, int b) {
    if (a == 0) {
        return b;
    }
    gcd(b % a, a);
}

int lcm(int a, int b) {
    return (a * b) / gcd(a, b);
}

int lcm_array(int a[], int n) {
    int result = a[0];
    for (int i = 1; i < n; ++i) {
        result = (result * a[i]) / gcd(result, a[i]);
    }
    return result;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int a = 3, b = 4;
    cout << lcm(a, b) << "\n";
    int n = 5;
    int arr[n] = {1, 2, 3, 4, 5};
    cout << lcm_array(arr, n) << "\n";
    return 0;
}