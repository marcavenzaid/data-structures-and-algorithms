/* Binary search (recursive): Searches a value in a sorted array by comparing X (the value
 * to search) with the value in the middle of the array. If they are unequal,
 * the half of the array in which X cannot lie is disregarded and the search
 * continues on the other half until X is found.
 *
 * Time complexity: O(log n)
 * Space complexity: O(1)
 */

/**
 * author: marcavenzaid
 * date created: September 29, 2018
 */

#include <bits/stdc++.h>

using namespace std;

int binary_search_recusive(int a[], int n, int l, int r, int x) {
    if (l > r) {
        return -1;
    }
    int m = (l + r) / 2;
    if (a[m] == x) {
        return m; 
    }
    if (a[m] < x) {
        return binary_search_recusive(a, n, m + 1, r, x);
    } else {
        return binary_search_recusive(a, n, l, m - 1, x);
    }    
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n = 10;
    int a[n] = {1, 3, 4, 5, 6, 7, 9, 9, 10, 12};
    int x = 5;
    cout << binary_search_recusive(a, n, 0, n - 1, x) << "\n";
    return 0;
}