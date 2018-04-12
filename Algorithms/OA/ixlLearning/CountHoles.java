package ixlLearning;

/** @author: Hongquan Yu
 *  @date: Apr 10, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class CountHoles {
	public int countHoles(int num) {
		int[] table = new int[] { 1, 0, 0, 0, 1, 0, 1, 0, 2, 1 };
		int count = 0;
		
		for (char c : String.valueOf(num).toCharArray())
			count += table[c - '0'];
		return count;
	}
}
