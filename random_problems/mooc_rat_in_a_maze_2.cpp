/**
 * author: marcavenzaid
 * date created: October 03, 2018
 */

#include <bits/stdc++.h>

using namespace std;

const int N = 5;
const int MOVES_N = 4, MOVES_AXIS = 2;

int solve(int a[N][N], int y, int x, int moves[MOVES_N][MOVES_AXIS]) {
    if (y < 0 || x < 0 || y == N || x == N) {
        return 0;
    }
    if (a[y][x] == -1) {
        return 0;
    }
    if (a[y][x] == 2) {
        return 1;
    }    
    int ans = 0;
    a[y][x] = -1;
    for (int i = 0; i < MOVES_N; ++i) {
        int _x = solve(a, y + moves[i][0], x + moves[i][1], moves);
        ans += _x;
    }
    a[y][x] = 0;
    return ans;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int a[N][N] = {{0, 0, 0, 0, -1},
                   {0, -1, 0, 0, 0},
                   {0, 0, 0, 0, 0},
                   {0, 0, 0, 2, 0},
                   {-1, 0, 0, 0, 0}};
    int moves[MOVES_N][MOVES_AXIS] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    cout << solve(a, 0, 0, moves) << "\n";
    return 0;
}