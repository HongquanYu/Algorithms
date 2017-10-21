package binarysearch;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SplitArrayLargestSum_410 {
	public int splitArray(int[] nums, int m) {
		int max = 0;
		long sum = 0;
		for (int num : nums) {
			max = Math.max(num, max);
			sum += num;
		}
		if (m == 1)
			return (int) sum;
		// binary search
		long l = max;
		long r = sum;
		while (l <= r) {
			long mid = (l + r) / 2;
			if (valid(mid, nums, m)) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return (int) l;
	}

	public boolean valid(long target, int[] nums, int m) {
		int count = 1;
		long total = 0;
		for (int num : nums) {
			total += num;
			if (total > target) {
				total = num;
				count++;
				if (count > m) {
					return false;
				}
			}
		}
		return true;
	}
}
