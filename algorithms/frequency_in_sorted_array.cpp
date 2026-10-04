#include <bits/stdc++.h>

using namespace std;

int frequency_in_sorted_array(int a[], int n, int x) {
    int *lower = lower_bound(a, a+n, x);
    if (lower == (a+n) || *lower != x) {
        return 0;
    }
    int *upper = upper_bound(lower, a+n, x);
    return upper - lower;
}

int main() {
    int n = 10;
    int a[10] = {2, 4, 3, 1, 6, 6, 6, 6, 8, 3};
    sort(a, a+n);
    int x = 6;
    cout << frequency_in_sorted_array(a, n, x);
}