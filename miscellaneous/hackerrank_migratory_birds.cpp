/**
 * author: marcavenzaid
 * date created: September 30, 2018
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n;
    cin >> n;
    int a[n];
    map<int, int> map;
    for (int i = 0; i < n; ++i) {
        cin >> a[i];
        ++map[a[i]];
    }
    auto ans = max_element(
        map.begin(), 
        map.end(), 
        [] (const pair<int, int> &p1, const pair<int, int> &p2) {
            return p1.second < p2.second;
        }
    );
    cout << ans->first << "\n";
    return 0;
}