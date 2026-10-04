/* Binary exponentiation: (Exponentiation by squaring) Compute b^e.
 *
 * Time complexity: O(log e)
 * Space complexity: O(1)
 */

/**
 * author: marcavenzaid
 * created: 2018-09-04-20.08
 */

#include <bits/stdc++.h>

using namespace std;

int binary_exponentiation(int b, unsigned int e) {
    int res = 1;
    while (e > 0) {
        if (e & 1) {
            res *= b;
        }
        e >>= 1;
        b *= b;
    }
    return res;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int b = 2;
    int e = 3;
    cout << binary_exponentiation(b, e);
    return 0;
}
