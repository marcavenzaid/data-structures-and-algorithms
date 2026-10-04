package marcavenzaid.lib.algo;

import java.util.ArrayList;

public class PrimeFactorization {

	/**
     * Returns an {@code ArrayList<Integer>} of all prime factors of argument {@code n}.
     * @param x
     * @return an {@code ArrayList<Integer>} of prime factors.
     */
    public static ArrayList<Integer> primeFactorization(int x) {
        ArrayList<Integer> al = new ArrayList<>();
        while (x % 2 == 0) {
            al.add(2);
            x /= 2;
        }
        for (int i = 3; i <= java.lang.Math.sqrt(x); i += 2) {
            while (x % i == 0) {
                al.add(i);
                x = x / i;
            }
        }
        if (x > 2) {
            al.add(x);
        }
        return al;
    }

    public static void main(String[] args) {
        int n = 315;
        System.out.println(primeFactorization(n));
    }
}
