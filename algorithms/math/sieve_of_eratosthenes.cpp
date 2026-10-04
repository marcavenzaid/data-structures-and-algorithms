/* Sieve of eratosthenes: outputs all prime numbers smaller than or equal to n.
 *
 * Time complexity: O(sqrt n loglog n)
 */

/**
 * author: marcavenzaid
 * created: 2018-08-27-04.39
 */

#include <bits/stdc++.h>

using namespace std;

void sieve_of_eratosthenes(int n) {
    bool prime[n+1];
    memset(prime, true, sizeof(prime));
    for (int p = 2; p*p <= n; p++) {
        if (prime[p] == true) {
            for (int i = p*2; i <= n; i += p) {
                prime[i] = false;
            }
        }
    }
    for (int p = 2; p <= n; p++) {
        if (prime[p]) {
            cout << p << " ";
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n = 12;
    sieve_of_eratosthenes(n);
    return 0;
}
