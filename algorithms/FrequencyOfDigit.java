package marcavenzaid.lib.algo;

public class FrequencyOfDigit {
	
	/**
     * Count how many times a digit {@code d} appears in {@code int n}.
     * @param n the integer.
     * @param d the digit.
     * @return the frequency of {@code d} in {@code n}.
     */
    public static int frequencyOfDigit(int n, int d) {
        int f = 0;
        while (n > 0) {
            if (n % 10 == d) {
                f++;
            }
            n /= 10;
        }
        return f;
    }

    public static void main(String[] args) {
        System.out.println(frequencyOfDigit(123, 1)); // 1
        System.out.println(frequencyOfDigit(1223, 2)); // 2
        System.out.println(frequencyOfDigit(12333, 3)); // 3
        System.out.println(frequencyOfDigit(123, 4)); // 0
    }
}
