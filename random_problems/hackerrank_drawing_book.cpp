/**
 * author: marcavenzaid
 * date created: October 07, 2018
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n, p;
    cin >> n >> p;
    int ans = p / 2;
    if (p > (n / 2)) {
        ans = (n / 2) - ans;
    }
    cout << ans << "\n";
    return 0;
}