package binarysearch;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ArrangingCoins_441 {
	public int arrangeCoins(int n) {
		int start = 0;
		int end = n;
		int mid = 0;
		while (start <= end) {
			mid = (start + end) >>> 1;
			if ((0.5 * mid * mid + 0.5 * mid) <= n) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return start - 1;
	}
	
	public int arrangeCoins2(int n) {
		return (int) ((Math.sqrt(1 + 8.0 * n) - 1) / 2);
	}
}
