/* Sum consecutive range: Formula to find the sum of integers between l and r
 * (both inclusive).
 *
 * Time complexity: O(1)
 */

/**
 * author: marcavenzaid
 * created: 2018-08-29-21.44
 */

#include <bits/stdc++.h>

using namespace std;

long long sum_consecutive_range(long long l, long long r) {
    return r*(r+1)/2 - l*(l-1)/2;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    long long l = 6, r = 7;
    cout << sum_consecutive_range(l, r);
    return 0;
}
