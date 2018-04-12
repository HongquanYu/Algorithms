package binarySearch;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Jan 16, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindKthSmallestPairDistance_719 {
	
	/** 所有的pair difference在最小的区间和最大的区间的range之内。
	 *  所以，我们先找出这个区间，然后再在这个区间里做二叉搜索，直到两个索引都到k
	 * */

    public int smallestDistancePair(int a[], int k) {
        int N = a.length;
        Arrays.sort(a);		// 有过排序
        
        // 找到搜索区间
        int low = a[1] - a[0];		// Minimum absolute difference
        for (int i = 1; i < N - 1; i++)
            low = Math.min(low, a[i + 1] - a[i]);

        int high = a[N - 1] - a[0];	// Maximum absolute difference

        // Do binary search for k-th absolute difference
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            if (countPairs(a, mid) < k) low = mid + 1;
            else 						high = mid;
        }
        return low;
    }
    
    // 对于任一个mid，找到数组中共有多少个pair它们的差是<=这个值的。Quadratic
    private int countPairs(int[] a, int mid) {
        int N = a.length, cnt = 0;
        
        for (int i = 0, j = i; i < N; ++i, j = i) {
            while (j < N && a[j] - a[i] <= mid) j++;
            cnt += j - i - 1;
        }
        
        return cnt;
    }
    
    /** Improve, countPairs use binary search too! */
    
	private int upperBound(int[] a, int low, int high, int key) {
		if (a[high] <= key)
			return high + 1;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (key >= a[mid]) 	low = mid + 1;
			else  				high = mid;
		}
		return low;
	}

	// Returns number of pairs with absolute difference less than or equal to
	// mid.
	private int countPairs2(int[] a, int mid) {
		int n = a.length, res = 0;
		for (int i = 0; i < n; i++)
			res += upperBound(a, i, n - 1, a[i] + mid) - i - 1;
		return res;
	}

	public int smallestDistancePair2(int a[], int k) {
		int n = a.length;
		Arrays.sort(a);

		// Minimum absolute difference
		int low = a[1] - a[0];
		for (int i = 1; i < n - 1; i++)
			low = Math.min(low, a[i + 1] - a[i]);

		// Maximum absolute difference
		int high = a[n - 1] - a[0];

		// Do binary search for k-th absolute difference
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (countPairs2(a, mid) < k) 	low = mid + 1;
			else 							high = mid;
		}

		return low;
	}
}
