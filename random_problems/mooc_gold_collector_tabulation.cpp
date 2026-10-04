/**
 * author: marcavenzaid
 * date created: October 03, 2018
 */

#include <bits/stdc++.h>

using namespace std;

int solve(int a[], int n, int table[]) {
    table[0] = a[0];
    table[1] = max(a[1], table[0]);
    for (int i = 2; i < n; ++i) {
        table[i] = max(table[i - 1], table[i - 2] + a[i]);
    }
    return table[n - 1];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n = 6;
    int a[n] = {33, 10, 20, 5, 14, 26};
    int table[n] = {0};
    cout << solve(a, n, table) << "\n";
    return 0;
}