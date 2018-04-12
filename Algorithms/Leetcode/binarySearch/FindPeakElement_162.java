package binarySearch;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindPeakElement_162 {
	
	/* Linear algorithm:
	 * First solution take advantage of the fact: nums[i] != nums[i+1] 
	 * We only need to see if nums[i] > nums[i+1]. No business of nums[i-1]
	 * 
	 * 1, the array is in a descending order: first element meets the criteria
	 * 2, the array is in an ascending order: last element meets the criteria
	 * 3, the peak point is located in the middle of array, then the two sides
	 * 	  of peak element can be divided to case 1 and case 2. Then we can find
	 *    the peak element. */
	
	public int findPeakElement(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] > nums[i + 1])
				return i;
		}

		return nums.length - 1;
	}
    
    /* Recursion Binary search: */
    
	public int findPeakElement2(int[] nums) {
		return search(nums, 0, nums.length - 1);
	}

	public int search(int[] nums, int l, int r) {
		if (l == r)
			return l;
		int mid = (l + r) / 2;
		if (nums[mid] > nums[mid + 1])
			return search(nums, l, mid);
		
		return search(nums, mid + 1, r);
	}
	
    /* Iterative Binary search */
	
	public int findPeakElement3(int[] nums) {
		int l = 0, r = nums.length - 1;
		
		while (l < r) {
			int mid = (l + r) / 2;
			if (nums[mid] > nums[mid + 1])
				r = mid;
			else
				l = mid + 1;
		}
		
		return l;
	}
}
