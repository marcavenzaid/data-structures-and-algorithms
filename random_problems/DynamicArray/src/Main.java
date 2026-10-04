/**
 * author: marcavenzaid
 * created: Nov 11, 2018
 */

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		DynamicArray<Integer> da = new DynamicArray<Integer>();
		for (int i = 0; i < 16; ++i) {
			da.add(i);
		}		
		String s = da.toString();
		System.out.println(s);
		da.removeAt(15);
		s = da.toString();
		System.out.println(s);
	}	
}
