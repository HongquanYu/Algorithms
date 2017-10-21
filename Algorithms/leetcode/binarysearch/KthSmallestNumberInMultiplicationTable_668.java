package binarysearch;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class KthSmallestNumberInMultiplicationTable_668 {
	public boolean enough(int x, int m, int n, int k) {
		int count = 0;
		for (int i = 1; i <= m; i++) {
			count += Math.min(x / i, n);
		}
		return count >= k;
	}

	public int findKthNumber(int m, int n, int k) {
		int lo = 1, hi = m * n;
		while (lo < hi) {
			int mi = lo + (hi - lo) / 2;
			if (!enough(mi, m, n, k))
				lo = mi + 1;
			else
				hi = mi;
		}
		return lo;
	}
}
