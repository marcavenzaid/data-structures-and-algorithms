package marcavenzaid.lib.algo;

import java.util.ArrayList;

public class Divisors {

	public static ArrayList<Integer> divisors(int n) {
        ArrayList<Integer> u = new ArrayList<>();
        ArrayList<Integer> v = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (n / i == i) {
                    u.add(i);
                } else {
                    u.add(i);
                    v.add(n / i);
                }
            }
        }
        u.addAll(v);
        return u;
    }

    public static void main(String[] args) {
        int n = 100;
        ArrayList<Integer> divisors = divisors(n);
        System.out.println("Divisors of " + n + ": " + divisors);
    }
}
