/**
 * author: marcavenzaid
 * created: Aug 3, 2018
 */

public class HackerRankJavaSubstringComparisons {
	
	public static String getSmallestAndLargest(String s, int k) {
		String substr = s.substring(0, k);
		String smallest = substr;
        String largest = substr;
        for (int i = 1; i <= s.length()-k; ++i) {
        	substr = s.substring(i, i+k);
        	if (substr.compareTo(largest) > 0) {
        		largest = substr;
        	}
        	if (substr.compareTo(smallest) < 0) {
        		smallest = substr;
        	}
        }
        return smallest + "\n" + largest;
    }
	
	public static void main(String[] args) {
        String s = "welcometojava";
        int k = 3;
        System.out.println(getSmallestAndLargest(s, k));
    }
}
