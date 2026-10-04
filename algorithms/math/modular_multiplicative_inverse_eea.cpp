/* Modular Multiplicative Inverse EEA: Algorithm for computing the modular
 * multiplicative inverse x of a such that ax is congruent to 1 with respect to
 * modulo m (i.e. ax congruent to 1 (mod m)) using Extended Euclidean Algorithm.
 *
 * Assumptions:
 * a and m are co-primes i.e. gcd(a, m) = 1.
 *
 * Time complexity: O(log m)
 */

/**
 * author: marcavenzaid
 * created: 2018-09-04-22.04
 */

#include <bits/stdc++.h>

using namespace std;

int modular_multiplicative_inverse_eea(int a, int m) {
    int m0 = m;
    int y = 0, x = 1;
    if (m == 1) {
        return 0;
    }
    while (a > 1) {
        int q = a / m;
        int temp = m;
        m = a % m;
        a = temp;
        temp = y;
        y = x - q * y;
        x = temp;
    }
    if (x < 0) {
        x += m0;
    }
    return x;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int a = 3;
    int m = 7;
    cout << modular_multiplicative_inverse_eea(a, m);
    return 0;
}
