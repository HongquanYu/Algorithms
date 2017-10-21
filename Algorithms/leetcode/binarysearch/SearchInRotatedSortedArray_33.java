package binarysearch;

/** @author: Hongquan Yu
 *  @date: Oct 11, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class SearchInRotatedSortedArray_33 {
	
	/* Direct search */
	
	public int search(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;

		while (lo < hi) { // do the binary search
			int mid = (lo + hi) / 2;
			if (nums[mid] == target)
				return mid;

			if (nums[lo] <= nums[mid]) { 	// rotation happens in first half of array
				if (target >= nums[lo] && target < nums[mid])
					hi = mid - 1;	// target is in first half
				else 				// target is in second half
					lo = mid + 1;
			} else { 						// rotation happens in second half.
				if (target > nums[mid] && target <= nums[hi]) 
					lo = mid + 1;	// target is in second half
				else 				// target is in first half
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
