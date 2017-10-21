package binarysearch;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SearchInRotatedSortedArrayII_81 {
	public boolean search(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
		if (lo > hi)
			return false;

		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (nums[mid] == target)
				return true;

			if (nums[mid] > nums[hi]) { // rotation happens in the first half of array
				if (target < nums[mid] && target >= nums[lo]) // target is in the 1st half
					hi = mid;
				else // target is in the 2nd half
					lo = mid + 1;
			} else if (nums[mid] < nums[hi]) { // rotation happens in the second half
				if (target > nums[mid] && target <= nums[hi]) // target is in the 2nd half
					lo = mid + 1;
				else // target is in the 1st half
					hi = mid;
			} else
				hi--; // rotation happens in the exact middle
		}
		return nums[lo] == target ? true : false;
	}
}

