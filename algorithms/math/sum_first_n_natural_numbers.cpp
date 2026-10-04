/* Sum first n natural numbers: Formula to find the sum of first natural numbers
 * (positive integers).
 *
 * Time complexity: O(1)
 */

/**
 * author: marcavenzaid
 * created: 2018-08-29-16.37
 */

#include <bits/stdc++.h>

using namespace std;

long long sum_first_n_natural_numbers(long long n) {
    return n*(n+1)/2;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    long long n = 100;
    cout << sum_first_n_natural_numbers(n);
    return 0;
}
