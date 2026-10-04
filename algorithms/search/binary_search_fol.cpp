/* Binary search fol: Searches for the first or last (depending on the value
 * that you specify in the bool first parameter) occurrence of X in a given
 * sorted array through binary search application.
 *
 * Time complexity: O(log n)
 * Space complexity: O(1)
 *
 * Parameters:
 * A = input array.
 * N = number of items.
 * X = value to search.
 * first = location of X, either first if true or last if false.
 */

/**
 * author: marcavenzaid
 * created: 2018-07-22-18.17
 */

#include <bits/stdc++.h>

using namespace std;

int binary_search_fol(const int A[], const int N, const int X, bool first) {
    int l = 0, m, r = N - 1;
    int i = -1;
    while (l <= r) {
        m = l + (r-l)/2;;
        if(A[m] == X) {
            i = m;
            if (first) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        } else if (A[m] < X) {
            l = m + 1;
        } else {
            r = m - 1;
        }
    }
    return i;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    // Input should be in ascending order.
    int input[] = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5};
    int N = sizeof(input) / sizeof(input[0]);
    int f = binary_search_fol(input, N, 3, true);
    int l = binary_search_fol(input, N, 3, false);
    cout << "first: " << f << "\n";
    cout << "last: " << l << "\n";
    return 0;
}
