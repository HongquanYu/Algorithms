package binarySearch;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SplitArrayLargestSum_410 {
	
	/* 子数组里最大的一个出现的range一定在 数组中最大元素 和 数组和 之间
	 * 所以，我们使用二叉搜索来缩小范围，直到找到最小的那个元素！
	 * 本算法有个helper来决定目前的mid是不是一个有效的数，并根据结果调整搜索区间 */
	
	public int splitArray(int[] nums, int m) {
		int max = 0;
		long sum = 0;
		
		for (int num : nums) {
			max = Math.max(num, max);
			sum += num;
		}
		if (m == 1) 	return (int) sum;
		
		// binary search
		long lo = max, hi = sum;
		while (lo <= hi) {
			long mid = (lo + hi) / 2;
			
			if (valid(mid, nums, m)) 	hi = mid - 1;		// narrow down right boundary
			else 						lo = mid + 1;		// narrow down left boundary
		}
		
		return (int) lo;
	}

	// 是否正好含有 m 个子数组的和大于等于target
	public boolean valid(long target, int[] nums, int m) {
		int count = 1;
		long total = 0;		// Number of valid sub-arrays
		
		for (int num : nums) {
			total += num;
			if (total > target) {		// Reach target, then reset total and increase count 
				total = num;
				count++;
				if (count > m) 	return false;	// Already large than m
			}
		}
		
		return true;
	}
}
