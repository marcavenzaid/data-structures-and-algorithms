package marcavenzaid.lib.algo;

public class ModularExponentiation {

	/**
     * Computes (b^e) % mod.
     *
     * <p>Time complexity: O(log y)</p>
     * <p>Space complexity: O(1)</p>
     * @param b the base number.
     * @param e the exponent.
     * @param mod the modulus.
     * @return
     */
    public static int modularExponentiation(int b, int e, int mod) {
        int res = 1;
        b %= mod;
        while (e > 0) {
            if ((e & 1) == 1) {
                res = (res * b) % mod;
            }
            e >>= 1;
            b = (b * b) % mod;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(modularExponentiation(2, 10, 1000)); // Output: 24
    }
}
