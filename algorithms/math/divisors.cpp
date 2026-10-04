/* Outputs all of the divisors of a given number.
 *
 * Time complexity: O(sqrt(n))
 * Space complexity: O(1)
 */

/**
 * author: marcavenzaid
 * created: 2018-08-05-22.51
 */

#include <bits/stdc++.h>

using namespace std;

void unordered_divisors(int n) {
    for (int i = 1; i <= sqrt(n); ++i) {
        if (n % i == 0) {
            if (n / i == i) {
                cout << i << " ";
            } else {
                cout << i << " " << n / i << " ";
            }
        }
    }
}

void divisors(int n) {
    vector<int> v;
    for (int i = 1; i <= sqrt(n); ++i) {
        if (n % i == 0) {
            if (n / i == i) {
                cout << i << " ";
            } else {
                cout << i << " ";
                v.push_back(n / i);
            }
        }
    }
    for (int i = v.size()-1; i >= 0; --i) {
        cout << v[i] << " ";
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n = 6;
    unordered_divisors(n);
    cout << "\n";
    divisors(n);
    return 0;
}
