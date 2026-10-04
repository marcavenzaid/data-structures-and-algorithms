/**
 * author: marcavenzaid
 * date created: September 28, 2018
 */

#include <bits/stdc++.h>

using namespace std;

bool is_symmetrical(int a[], int l, int r) {
    if (l >= r) {
        return true;
    }
    if (a[l] != a[r]) {
        return false;
    }
    return is_symmetrical(a, l + 1, r - 1);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int a[] = {1, 2, 3, 4, 4, 3, 2, 1};
    int n = sizeof(a) / sizeof(a[0]);
    cout << is_symmetrical(a, 0, n - 1) << "\n";
    return 0;
}