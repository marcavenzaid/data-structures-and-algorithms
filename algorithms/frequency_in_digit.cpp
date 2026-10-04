/* Frequency in digit: Count how many times a digit d appears in number n.
 */

/**
 * author: marcavenzaid
 * created: 2018-09-02-22.14
 */

#include <bits/stdc++.h>

using namespace std;

int frequency_in_digit(int n, int d) {
    int f = 0;
    while (n > 0) {
        if (n % 10 == d) {
            f++;
        }
        n /= 10;
    }
    return f;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int n = 13333345;
    cout << frequency_in_digit(n, 3);
    return 0;
}
