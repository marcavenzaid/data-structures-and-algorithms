/**
 * author: marcavenzaid
 * created: 2018-09-16-00.01
 */

#include <bits/stdc++.h>

using namespace std;

int primes[1000000];

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
    int i = 0;
    for (int p = 2; p <= n; p++) {
        if (prime[p]) {
            primes[i] = p;
            ++i;
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int t;
    cin >> t;
    sieve_of_eratosthenes(1000);
    while (t--) {
        int l, r;
        cin >> l >> r;
        int total_pairs = 0;
        vector<pair<int, int>> pairs;
        set<int> _set;
        set<int> _iset;
        int i = 0;
        while (primes[i] < r && i < 1000) {
            if (_iset.find(i) == _iset.end()) {
                int j = i + 1;
                while (primes[j] < r && j < 1000) {
                    int x = abs(primes[i] - primes[j]);
                    if (_set.find(x) == _set.end()) {
                        ++total_pairs;
                        pairs.push_back(make_pair(primes[i], primes[j]));
                        _set.insert(x);
                        _iset.insert(j);
                        break;
                    }
                    ++j;
                }
            }
            ++i;
        }
        cout << total_pairs << "\n";
        for (int i = 0; i < pairs.size(); ++i) {
            cout << pairs[i].first << " " << pairs[i].second << "\n";
        }
    }
    return 0;
}
