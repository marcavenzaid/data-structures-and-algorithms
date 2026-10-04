package marcavenzaid.lib.algo;

public class BinarySearch {

	/**
     * Searches the specified array of ints in a given sorted array
     * for the specified value using the binary search algorithm.
     * If the array contains multiple elements with the specified
     * value, there is no guarantee which one will be found.
     * <p>Time complexity: O(log n)</p>
     * <p>Space complexity: O(1)</p>
     * @param a the array to be searched.
     * @param x the {@code int} to search.
     * @return the index of {@code x} if present. Else return -1.
     */
    public static int binarySearch(int[] a, int x) {
        int l = 0, m, r = a.length - 1;
        while (l <= r) {
            m = l + (r - l)/2;
            if(a[m] == x) {
                return m;
            } else if (a[m] < x) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }
    
    /**
     * Searches for the first occurrence of {@code x} in a given sorted array
     * of ints using the binary search algorithm.
     * <p>Time complexity: O(log n)</p>
     * <p>Space complexity: O(1)</p>
     * @param a the array to be searched.
     * @param x the {@code int} to search.
     * @return the index of the first occurrence of {@code x} if present. Else return -1.
     */
    public static int binarySearchFirst(int[] a, int x) {
        int l = 0, m, r = a.length - 1;
        int first = -1;
        while (l <= r) {
            m = l + (r - l)/2;
            if(a[m] == x) {
                first = m;
                r = m - 1;
            } else if (a[m] < x) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return first;
    }

    /**
     * Searches for the last occurrence of {@code x} in a given sorted array
     * of ints using the binary search algorithm.
     * <p>Time complexity: O(log n)</p>
     * <p>Space complexity: O(1)</p>
     * @param a the array to be searched.
     * @param x the {@code int} to search.
     * @return the index of the last occurrence of {@code x} if present. Else return -1.
     */
    public static int binarySearchLast(int[] a, int x) {
        int l = 0, m, r = a.length - 1;
        int last = -1;
        while (l <= r) {
            m = l + (r - l)/2;
            if(a[m] == x) {
                last = m;
                l = m + 1;
            } else if (a[m] < x) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return last;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 5, 5, 6, 7};
        System.out.println(binarySearch(a, 5));
        System.out.println(binarySearchFirst(a, 5));
        System.out.println(binarySearchLast(a, 5));
    }
}
