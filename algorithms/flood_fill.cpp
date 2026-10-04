/* Flood fill: determines the area connected to a given node/cell/point in a
 * multi-dimensional array.
 * 
 * Famous implementations of flood fill algorithm:
 * Bucket fill in paint, solving a maze, minesweeper, go.
 * 
 * Time complexity: O(n*m)
 */

/**
 * author: marcavenzaid
 * date created: September 28, 2018
 */

#include <bits/stdc++.h>

using namespace std;

const int NX = 10, NY = 10;

void flood_fill_util(int a[NY][NX], int fill, int orig_fill, int x, int y) {
    if (x < 0 || y < 0 || x >= NX || y >= NY || a[y][x] != orig_fill) {
        return;
    }
    a[y][x] = fill;
    flood_fill_util(a, fill, orig_fill, x - 1, y);
    flood_fill_util(a, fill, orig_fill, x, y - 1);
    flood_fill_util(a, fill, orig_fill, x + 1, y);
    flood_fill_util(a, fill, orig_fill, x, y + 1);
}

void flood_fill(int a[NY][NX], int fill, int x, int y) {
    int orig_fill = a[y][x];
    flood_fill_util(a, fill, orig_fill, x, y);
}

void print(int a[NY][NX]) {
    for (int i = 0; i < NY; ++i) {
        for (int j = 0; j < NX; ++j) {
            cout << a[i][j] << " ";
        }
        cout << "\n";
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int a[NY][NX] = {{0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                     {0, 1, 0, 0, 0, 0, 0, 1, 1, 0},
                     {1, 1, 0, 0, 0, 1, 1, 0, 1, 0},
                     {0, 0, 0, 1, 1, 0, 0, 0, 1, 0},
                     {0, 0, 1, 1, 0, 0, 0, 0, 1, 0},
                     {0, 0, 1, 0, 0, 0, 1, 1, 1, 0},
                     {0, 1, 0, 0, 0, 1, 1, 0, 0, 0},
                     {0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                     {1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                     {1, 1, 0, 0, 0, 0, 1, 0, 1, 1}};
    int x = 4, y = 5;
    int fill = 9;
    print(a);    
    cout << "\nx = " << x << ", y = " << y << ", fill = " << fill << "\n";
    flood_fill(a, fill, x, y);
    print(a);
    x = 4; 
    y = 0;
    fill = 3;
    cout << "\nx = " << x << ", y = " << y << ", fill = " << fill << "\n";
    flood_fill(a, fill, x, y);
    print(a);
    return 0;
}