/* Modular Exponentiation: Algorithm for computing (b^e) % mod. Exponentiation
 * over a modulus.
 *
 * Time complexity: O(log y)
 * Space complexity: O(1)
 */

/**
 * author: marcavenzaid
 * created: 2018-09-04-19.32
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

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int b = 2;
    int e = 5;
    int m = 13;
    cout << modular_exponentiation(b, e, m);
    return 0;
}
