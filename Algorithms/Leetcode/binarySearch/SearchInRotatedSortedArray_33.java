package binarySearch;

/** @author: Hongquan Yu
 *  @date: Oct 11, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class SearchInRotatedSortedArray_33 {
	
	/* Direct search */
	
	public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
		int lo = 0, hi = nums.length - 1;

		while (lo < hi) { 		// do the binary search
			int mid = (lo + hi) / 2;
			if (nums[mid] == target)
				return mid;
			
			// 正中间旋转，奇数个元素单调递增则落在前半部分，偶数为后半部分
			if (nums[lo] <= nums[mid]) { 	// 前半部分旋转，现在前半部分是单调递增
				if (target >= nums[lo] && target < nums[mid])	// target处于递增区间
					hi = mid - 1;	// 搜索前半部分
				else 				// 搜索后半部分
					lo = mid + 1;
			} else { 						// 后半部分旋转，现在后半部分是递增区间
				if (target > nums[mid] && target <= nums[hi]) 	// 后半部分是递增区间
					lo = mid + 1;	// 搜索后半部分
				else 				// 搜索前半部分
					hi = mid - 1;
			}
		}
		
		return nums[lo] == target ? lo : -1; // return index or -1
	}
	
	/* Direct search */
	
	public int search2(int[] nums, int target) {
		int len = nums.length;
		int lo = 0, hi = len - 1;
		
		while (lo < hi) {		// find the index of the smallest value using binary search.
			int mid = (lo + hi) / 2;
			if (nums[mid] > nums[hi])
				lo = mid + 1;
			else
				hi = mid;
		}
		
		int pivot = lo;			// lo==hi is the index of the smallest value and also the number of places pivotated.
		lo = 0;
		hi = len - 1;
		
		while (lo <= hi) { 		// The usual binary search and accounting for pivotation.
			int mid = (lo + hi) / 2;

			int realmid = (mid + pivot) % len; // current mid + offset = real middle. % to ensure not overflow
			if (nums[realmid] == target)
				return realmid;
			if (nums[realmid] < target)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return -1;
	}
}
