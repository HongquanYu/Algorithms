package binarysearch;

/** @author: Hongquan Yu
 *  @date: Oct 11, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class FindTheDuplicateNumber_287 {
	public int findDuplicate(int[] nums) {
		int slow = nums[0];			// Point to first item
		int fast = nums[nums[0]];	// Point to 
		
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[nums[fast]];
		}

		fast = 0;
		while (fast != slow) {
			fast = nums[fast];
			slow = nums[slow];
		}
		return slow;
	}
	
	public int findDuplicate1(int[] nums) {
		int low = 1;
		int high = nums.length - 1;
		while (low <= high) {
			int mid = (int) (low + (high - low) * 0.5);	
			int cnt = 0;
			for (int a : nums) {
				if (a <= mid)
					++cnt;
			}
			if (cnt <= mid)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return low;
	}
}
