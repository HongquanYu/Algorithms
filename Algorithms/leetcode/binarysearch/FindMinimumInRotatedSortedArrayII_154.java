package binarysearch;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindMinimumInRotatedSortedArrayII_154 {
	public int findMin(int[] nums) {
		int lo = 0, hi = nums.length - 1;
		int mid = 0;

		while (lo < hi) {
			mid = lo + (hi - lo) / 2;

			if (nums[mid] > nums[hi])
				lo = mid + 1;
			else if (nums[mid] < nums[hi])
				hi = mid;
			else
				hi--; // when nums[mid] and nums[hi] are same
		}
		
		return nums[lo];
	}
}
