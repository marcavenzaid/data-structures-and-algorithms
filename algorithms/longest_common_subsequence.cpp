/**
 * author: marcavenzaid
 * created: 2018-08-15-19.14
 */

#include <bits/stdc++.h>

using namespace std;

int longest_common_subsequence(string a, string b, int m, int n) {
    int l[m+1][n+1];
    for (int i = 0; i <= m; ++i) {
        for (int j = 0; j <= n; ++j) {
            if (i == 0 || j == 0) {
                l[i][j] = 0;
            } else if (a[i-1] == b[j-1]) {
                l[i][j] = l[i-1][j-1] + 1;
            } else {
                l[i][j] = max(l[i-1][j], l[i][j-1]);
            }
        }
    }
    return l[m][n];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    string a = "asdfghj";
    string b = "xsdfgj";
    cout << longest_common_subsequence(a, b, a.length(), b.length());
    return 0;
}
