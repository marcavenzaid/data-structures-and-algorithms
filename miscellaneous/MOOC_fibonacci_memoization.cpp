/**
 * author: marcavenzaid
 * date created: September 29, 2018
 */

#include <bits/stdc++.h>

using namespace std;

const int n = 7;
int memo[n];

int fibonacci(int n) {
    if (n <= 1) {
        return n;
    }
    if (memo[n] != -1) {
        return memo[n];
    }
    return memo[n] = fibonacci(n - 1) + fibonacci(n - 2);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n = 7;
    fill_n(memo, sizeof(memo), -1);
    cout << fibonacci(n) << "\n";
    return 0;
}