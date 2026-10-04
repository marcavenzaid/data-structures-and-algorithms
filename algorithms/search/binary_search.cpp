/* Binary search: Searches a value in a sorted array by comparing X (the value
 * to search) with the value in the middle of the array. If they are unequal,
 * the half of the array in which X cannot lie is disregarded and the search
 * continues on the other half until X is found.
 *
 * Time complexity: O(log n)
 * Space complexity: O(1)
 */

/**
 * author: marcavenzaid
 * created: 2018-06-28-15.07
 */

#include <bits/stdc++.h>

using namespace std;

int _binary_search(const int A[], const int N, const int X) {
    int l = 0, m, r = N - 1;
    while (l <= r) {
        m = l + (r-l)/2;
        if(A[m] == X) {
            return m;
        } else if (A[m] < X) {
            l = m + 1;
        } else {
            r = m - 1;
        }
    }
    return -1;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    // Input should be in ascending order.
    int input[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 27};
    int N = sizeof(input) / sizeof(input[0]);
    int i = _binary_search(input, N, 7);
    cout << i << endl;
    return 0;
}
