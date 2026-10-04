/* Sieve prime factorization: calculate the prime factorization of a number with
 * pre-calculation of smallest prime factor of every number up to n using
 * Sieve of Eratosthenes algorithm.
 *
 * Time complexity: O(log n)
 * Space complexity: O(n)
 */

/**
 * author: marcavenzaid
 * created: 2018-08-27-05.00
 */

#include <bits/stdc++.h>

using namespace std;

void sieve_smallest_prime_factors(int spf[], int n) {
    spf[1] = 1;
    for (int i = 2; i < n; i++) {
        spf[i] = i;
    }
    for (int i = 4; i < n; i += 2) {
        spf[i] = 2;
    }
    for (int i = 3; i*i < n; i++) {
        if (spf[i] == i) {
            for (int j = i*i; j < n; j += i) {
                if (spf[j] == j) {
                    spf[j] = i;
                }
            }
        }
    }
}

vector<int> prime_factorization(int spf[], int x) {
    vector<int> ret;
    while (x != 1) {
        ret.push_back(spf[x]);
        x = x / spf[x];
    }
    return ret;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int x = 100;
    int y = 200;
    int spf_max = 100001;
    int spf[spf_max];
    sieve_smallest_prime_factors(spf, spf_max);
    vector<int> f1 = prime_factorization(spf, x);
    vector<int> f2 = prime_factorization(spf, y);
    for (int i = 0; i < f1.size(); i++) {
        cout << f1[i] << " ";
    }
    cout << "\n";
    for (int i = 0; i < f2.size(); i++) {
        cout << f2[i] << " ";
    }
    return 0;
}
