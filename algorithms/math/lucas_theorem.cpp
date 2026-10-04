/* Lucas theorem: expresses nCr % p or the modulo of a binomial coefficient
 * (n, r) by a prime number p.
 *
 * Time complexity: O(p^2 * logp(n))
 * Space complexity: O(p)
 */

/**
 * author: marcavenzaid
 * created: 2018-07-14-18.18
 */

#include <bits/stdc++.h>

using namespace std;

int binomial_coefficient(int n, int r, int p) {
    int c[r+1];
    memset(c, 0, sizeof(c));
    c[0] = 1;
    for (int i = 1; i <= n; ++i) {
        for (int j = min(i, r); j > 0; --j) {
            c[j] = (c[j] + c[j-1]) % p;
        }
    }
    return c[r];
}

int lucas_theorem(int n, int r, int p) {
    if (r == 0) {
        return 1;
    }
    int ni = n % p, ri = r % p;
    return (binomial_coefficient(n/p, r/p, p) * binomial_coefficient(ni, ri, p)) % p;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n = 10, r = 3, p = (int)1e9+7;
    cout << lucas_theorem(n, r, p);
    return 0;
}
