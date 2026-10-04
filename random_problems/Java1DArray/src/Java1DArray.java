/**
 * author: marcavenzaid
 * created: Sep 18, 2018
 */

import java.util.Scanner;

public class Java1DArray {
	
	private static int[] a;
	private static int n;
	private static int leap;
	private static boolean isPossible; 
	
	static void solve(int i, boolean forward) {
		if (i >= n) {
			isPossible = true;
			return;
		} else {
			if (i < 0 || a[i] != 0) {
				return;
			}
		}
		a[i] = 1;
		solve(i + 1, true);
		solve(i - 1, false);
		solve(i + leap, true);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int q = scanner.nextInt();
		while (q-- > 0) {			
			n = scanner.nextInt();
			leap = scanner.nextInt();
			isPossible = false;
			a = new int[n];
			for (int i = 0; i < n; ++i) {
				a[i] = scanner.nextInt();
			}
			solve(0, true);
			if (isPossible) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		scanner.close();
	}
}
