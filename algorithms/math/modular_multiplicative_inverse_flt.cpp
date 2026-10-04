/* Modular Multiplicative Inverse FLT: Algorithm for computing the modular
 * multiplicative inverse x of a such that ax is congruent to 1 with respect to
 * modulo m (i.e. ax congruent to 1 (mod m)) using Fermat's Little Theorem.
 *
 * Assumptions:
 * m is prime.
 *
 * Time complexity: O(log m)
 */

/**
 * author: marcavenzaid
 * created: 2018-09-05-23.32
 */

#include <bits/stdc++.h>

using namespace std;

int modular_exponentiation(int b, unsigned int e, int m) {
    int res = 1;
    b %= m;
    while (e > 0) {
        if (e & 1) {
            res = (res*b) % m;
        }
        e >>= 1;
        b = (b*b) % m;
    }
    return res;
}

int modular_multiplicative_inverse_flt(int a, int m) {
    return modular_exponentiation(a, m-2, m);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int a = 3;
    int m = 11;
    cout << modular_multiplicative_inverse_flt(23, 1000000007);
    return 0;
}
