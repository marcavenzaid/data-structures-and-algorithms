package marcavenzaid.lib.algo;

public class IsPrime {

	/**
     * Returns {@code true} if the integer argument is true.
     * @param x
     * @return a {@code boolean} value.
     */
    public static boolean isPrime(int x) {
        if (x <= 1) { return false; }
        if (x <= 3) { return true; }
        if (x % 2 == 0 || x % 3 == 0) { return false; }
        for (int i = 5; i * i <= x; i = i + 6) {
            if (x % i == 0 || x % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPrime(1)); // false
        System.out.println(isPrime(2)); // true
        System.out.println(isPrime(3)); // true
        System.out.println(isPrime(4)); // false
        System.out.println(isPrime(5)); // true
        System.out.println(isPrime(6)); // false
        System.out.println(isPrime(7)); // true
        System.out.println(isPrime(8)); // false
        System.out.println(isPrime(9)); // false
        System.out.println(isPrime(10)); // false
        System.out.println(isPrime(11)); // true
    }
}
