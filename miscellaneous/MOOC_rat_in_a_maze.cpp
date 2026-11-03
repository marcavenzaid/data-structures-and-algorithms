/**
 * author: marcavenzaid
 * date created: October 03, 2018
 */

#include <bits/stdc++.h>

using namespace std;

const int N = 5;

int solve(int a[][N], int y, int x, int memo[][N]) {
    if (y == N || x == N) {
        return 0;
    }
    if (a[y][x] == -1) {
        return 0;
    }
    if (y == N - 1 && x == N - 1) {
        return 1;
    }
    if (memo[y][x] > 0) {
        return memo[y][x];
    }
    int _a = solve(a, y, x + 1, memo);
    int _b = solve(a, y + 1, x, memo);
    memo[y][x] = _a + _b;
    return _a + _b;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int a[N][N] = {{0, 0, 0, 0, -1},
                   {0, -1, 0, 0, 0},
                   {0, 0, 0, 0, 0},
                   {0, 0, 0, 0, 0},
                   {-1, 0, 0, 0, 0}};
    int memo[N][N] = {0};
    cout << solve(a, 0, 0, memo) << "\n";
    return 0;
}