package array;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Jan 8, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MaximumLengthOfPairChain_646 {
	public int findLongestChain(int[][] pairs) {
		
		Arrays.sort(pairs, (p1, p2) -> p1[1] - p2[1]);

		int count = 0, end = Integer.MIN_VALUE;
		
		for (int[] pair : pairs) {
			if (pair[0] > end) {
				count++;
				end = pair[1];
			}
		}
		
		return count;
	}
}
