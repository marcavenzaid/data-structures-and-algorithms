/**
 * author: marcavenzaid
 * date created: September 28, 2018
 */

#include <bits/stdc++.h>

using namespace std;

int collatz_conjecture(int n, int steps) {
    if (n == 1) {
        return steps;
    }
    if (n % 2 == 0) {
        return collatz_conjecture(n / 2, steps + 1);
    } else {
        return collatz_conjecture(3*n + 1, steps + 1);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout << collatz_conjecture(9, 0) << "\n";
    return 0;
}