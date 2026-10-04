/* Merge sort: is a divide and conquer algorithm. It divides an array into two,
 * then calls itself in each of the two divided array until the size becomes 1, 
 * and finally calls the merge function which sort the array while merging it.
 *
 * Time complexity: O(n log n)
 * Space complexity: O(n)
 */

/**
 * author: marcavenzaid
 * date created: October 01, 2018
 */
#include <bits/stdc++.h>

using namespace std;

void merge(int a[], int l, int m, int r) {
    int ln = m - l + 1, rn = r - m;
    int l_arr[ln], r_arr[rn];
    for (int i = 0; i < ln; ++i) {
        l_arr[i] = a[l + i];
    }
    for (int i = 0; i < rn; ++i) {
        r_arr[i] = a[m + i + 1];
    }
    int i = 0, j = 0, k = l;
    while (i < ln && j < rn) {
        if (l_arr[i] <= r_arr[j]) {
            a[k] = l_arr[i];
            ++i;
        } else {
            a[k] = r_arr[j];
            ++j;
        }
        ++k;
    }
    while (i < ln) {
        a[k] = l_arr[i];
        ++i;
        ++k;
    }
    while (j < rn) {
        a[k] = r_arr[j];
        ++j;
        ++k;
    }
}

void merge_sort(int a[], int l, int r) {
    if (l < r) {
        int m = l + (r-l)/2;
        merge_sort(a, l, m);        
        merge_sort(a, m + 1, r);
        merge(a, l, m, r);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int a[] = {4, 7, 5, 2, 1, 0, 8, 9, 4, 6};
    int n = sizeof(a) / sizeof(a[0]);
    merge_sort(a, 0, n-1);
    for (int i = 0; i < n; ++i) {
        cout << a[i] << " ";
    }
    return 0;
}