/* Binomial coefficient: is number of ways of picking unordered outcomes from
 * possibilities, also known as a combination or combinatorial number.
 *
 * Time complexity: O(n*k)
 * Space complexity: O(k)
 */

/**
 * author: marcavenzaid
 * created: 2018-08-14-23.01
 */

#include <bits/stdc++.h>

using namespace std;

int binomial_coefficient(int n, int r, int p) {
    int c[r+1];
    memset(c, 0, sizeof(c));
    c[0] = 1;
    for (int i = 1; i <= n; ++i) {
        for (int j = min(i, r); j > 0; --j) {
            c[j] = (c[j] + c[j-1])%p;
        }
    }
    return c[r];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n = 10, r = 3, p = (int)1e9+7;
    cout << n << "C" << r << " = " << binomial_coefficient(n, r, p);
    return 0;
}
