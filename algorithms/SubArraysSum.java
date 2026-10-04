package marcavenzaid.lib.algo;

public class SubArraysSum {

	/**
     * Given an array a of size n, find the sum of all sub-arrays.
     *
     * <p>Time complexity: O(n)</p>
     * @param a
     * @return
     */
    public static int subarraysSum(int[] a) {
        int n = a.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i] * (i+1) * (n-i);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        System.out.println(subarraysSum(a)); // {1} + {2} + {3} + {1, 2} + {2, 3} + {1, 2, 3} = 20
    }
}
