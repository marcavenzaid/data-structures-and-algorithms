package marcavenzaid.lib.algo;

public class LCM {

	/**
     * Return the LCM (Least Common Multiple) of 2 or more numbers.
     * @param args
     * @return
     */
    public static int lcm(int... args) {
        if (args.length < 1) { return -1; }
        if (args.length < 2) { return args[0]; }
        if (args.length == 2) {
            return lcmUtil(args[0], args[1]);
        }
        int result = args[0];
        for (int i = 1; i < args.length; i++) {
            result = (result * args[i]) / gcd(result, args[i]);
        }
        return result;
    }
    
    /**
     * Return the LCM (Least Common Multiple) of 2 numbers.
     * @param a
     * @param b
     * @return
     */
    public static int lcmUtil(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    /**
     * Computes the GCD (Greatest Common Factor) of the varargs.
     *
     * <p>Time complexity: If {@code args.length == 2} O(log min(args[0], args[1])).</p>
     * @param args
     * @return
     */
    public static int gcd(int... args) {
        if (args.length < 1) { return -1; }
        if (args.length < 2) { return args[0]; }
        if (args.length == 2) {
            return gcdUtil(args[0], args[1]);
        }
        int result = args[0];
        for (int i = 1; i < args.length; i++) {
            result = gcd(result, args[i]);
        }
        return result;
    }

    /**
     * Computes the GCD (Greatest Common Factor) of two integers.
     *
     * <p>Time complexity: O(log min(a, b)).</p>
     * @param a
     * @param b
     * @return
     */
    private static int gcdUtil(int a, int b) {
        if (a == 0) { return b; }
        return gcd(b % a, a);
    }

    public static void main(String[] args) {
        System.out.println(lcm(2, 3, 4, 5));
    }
}
