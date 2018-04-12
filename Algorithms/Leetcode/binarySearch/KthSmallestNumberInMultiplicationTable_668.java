package binarySearch;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class KthSmallestNumberInMultiplicationTable_668 {
	
	/** 做space binary search */
	
	/** 当前乘法表里比 mid 小的数的个数 */
	private boolean greatThanK(int mid, int m, int n, int k) {
		int count = 0;
		for (int i = 1; i <= m; i++) 
			count += Math.min(mid / i, n);		// 每一排数比 mid 小的数，每一行最多Math.min(mid / i, n)这么多个数
		
		return count >= k;
	}

	public int findKthNumber(int m, int n, int k) {
		int lo = 1, hi = m * n;
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (!greatThanK(mid, m, n, k))
				lo = mid + 1;
			else
				hi = mid;
		}
		return lo;
	}
}
