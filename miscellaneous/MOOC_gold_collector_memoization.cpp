/**
 * author: marcavenzaid
 * date created: October 03, 2018
 */

#include <bits/stdc++.h>

using namespace std;

int solve(int a[], int n, int i, int memo[]) {
    if (i >= n) {
        return 0;
    }
    if (memo[i] != 0) {
        return memo[i];
    }
    int x = solve(a, n, i + 1, memo);
    int y = solve(a, n, i + 2, memo) + a[i];
    memo[i] = max(x, y);
    return max(x, y);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n = 6;
    int a[n] = {33, 10, 20, 5, 14, 26};
    int memo[n] = {0};
    cout << solve(a, n, 0, memo) << "\n";
    return 0;
}