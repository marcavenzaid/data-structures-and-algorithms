/* Prime factorization: outputs all prime factors of n.
 *
 * Time complexity: O(sqrt(n))
 */

/**
 * author: marcavenzaid
 * created: 2018-08-15-05.37
 */

#include <bits/stdc++.h>

using namespace std;

vector<int> prime_factorization(int n) {
    vector<int> a;
    while (n%2 == 0) {
        a.push_back(2);
        n /= 2;
    }
    for (int i = 3; i <= sqrt(n); i += 2) {
        while (n%i == 0) {
            a.push_back(i);
            n = n/i;
        }
    }
    if (n > 2) {
        a.push_back(n);
    }
    return a;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n = 12;
    vector<int> a = prime_factors(n);
    for (int i = 0; i < a.size(); ++i) {
        cout << a[i] << " ";
    }
    return 0;
}
