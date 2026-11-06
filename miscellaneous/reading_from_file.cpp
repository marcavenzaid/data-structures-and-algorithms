/* Count how many times each number appears in an input.
 *
 * Time complexity: O(n)
 *
 * This algorithm is only recommended in cases where the input size is small
 * because the memory will depend on biggest number in the input.
 */

#include <fstream>
#include <iostream>

// Algorithm
void get_appearances(std::fstream &data_in, int arr[], int arrsize) {
    for (int i = 0; i < arrsize; i++) {
        int x;
        data_in >> x;
        arr[x]++;
    }
}

int get_datalength(std::fstream &data_in) {
    int numbers = 0;

    while(!data_in.eof()) {
        int x;
        if (data_in >> x) {
            numbers++;
        }
    }
    data_in.clear();
    data_in.seekg(0, std::ios::beg);
    return numbers;
}

int main() {
    std::fstream data_in("data.in");
    int n = get_datalength(data_in);
    int arr[n] = {};

    get_appearances(data_in, arr, n);

    data_in.close();

    // Show output.
    std::cout << "Numbers:\t";
    for (int i = 0; i < n; i++) {
        std::cout << i << " ";
    }
    std::cout << "\nAppearances:\t";
    for (int i = 0; i < n; i++) {
        std::cout << arr[i] << " ";
    }

    return 0;
}
