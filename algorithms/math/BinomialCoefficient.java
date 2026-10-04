package marcavenzaid.lib.algo;

public class BinomialCoefficient {

	/**
     * Returns the answer to nCr or the number of ways of choosing
     * r unordered outcomes from n possibilities. More formally,
     * the number of r-element subsets (or r-combinations) of an
     * n-element set. Also known as a combination or combinatorial number.
     * <p>Time complexity: O(n*k)</p>
     * <p>Space complexity: O(k)</p>
     * @param n
     * @param r
     * @param p
     * @return
     */
    public static int binomialCoefficient(int n, int r, int p) {
        int[] c = new int[r + 1];
        c[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, r); j > 0; j--) {
                c[j] = (c[j] + c[j-1])%p;
            }
        }
        return c[r];
    }

    public static void main(String[] args) {
        int n = 5, r = 2, p = 13;
        System.out.println("Value of C(" + n + "," + r + ") mod " + p + " is " + binomialCoefficient(n, r, p)); // Output: 10
    }
}
