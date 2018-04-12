package binarySearch;

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

			if (nums[mid] > nums[hi]) { 	// 前半部分旋转，旋转后数组前半部分递增，为简化，我们判断target应该在单调区间
				if (target >= nums[lo] && target < nums[mid]) 	// target 在前半部分
					hi = mid;
				else // target 在后半部分
					lo = mid + 1;
			} else if (nums[mid] < nums[hi]) { // 前半部分旋转，旋转后数组后半部分递增
				if (target > nums[mid] && target <= nums[hi]) // target在 后半部分
					lo = mid + 1;
				else // target 在前半部分
					hi = mid;
			} else	// rotation happens in the exact middle
				hi--; 
		}
		return nums[lo] == target ? true : false;
	}
}

