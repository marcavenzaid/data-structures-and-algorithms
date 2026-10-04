/**
 * author: marcavenzaid
 * created: 2018-08-15-19.16
 */

#include <bits/stdc++.h>

using namespace std;

vector<char> common_subsequences(string a, string b, int m, int n) {
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
    int k = l[m][n];
    vector<char> lcs(k);
    int i = m, j = n;
    while (i > 0 && j > 0) {
        if (a[i-1] == b[j-1]) {
            lcs[k-1] = a[i-1];
            --i;
            --j;
            --k;
        } else if (l[i-1][j] > l[i][j-1]) {
            --i;
        } else {
            --j;
        }
    }
    return lcs;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    string a = "asdfghj";
    string b = "xsdfgj";
    vector<char> c = common_subsequences(a, b, a.length(), b.length());
    for (int k = 0; k < c.size(); ++k) {
        cout << c[k] << " ";
    }
    return 0;
}
