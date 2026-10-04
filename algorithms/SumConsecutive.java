package marcavenzaid.lib.algo;

public class SumConsecutive {

	/**
     * Returns the sum of all integers between l[inclusive] and r[inclusive].
     *
     * <p>Time complexity: O(1)</p>
     * @param l                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
     * @param r
     * @return
     */
    public static long sumConsecutive(long l, long r) {
        return r*(r+1)/2 - l*(l-1)/2;
    }
    
    public static void main(String[] args) {
        System.out.println(sumConsecutive(1, 10)); // 55
        System.out.println(sumConsecutive(5, 10)); // 45
        System.out.println(sumConsecutive(1, 100)); // 5050
    }
}
