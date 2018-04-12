package binarySearch;

/** @author: Hongquan Yu
 *  @date: Oct 11, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindMinimumInRotatedSortedArray_153 {
	
	/* Index to search */
	
	public int findMin(int[] nums) {
		int lo = 0, hi = nums.length - 1;

		while (lo < hi) {
			if (nums[lo] < nums[hi])		// no rotation, return first
				return nums[lo]; 

			int mid = (lo + hi) / 2;
			if (nums[mid] >= nums[lo])	// rotation is in the 2nd half
				lo = mid + 1; 
			else							// rotation is in the 1st half
				hi = mid;
		}
		
		return nums[lo];
	}
}
