package twoPointers;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ThreeSumClosest_16 {
	
	public int threeSumClosest(int[] nums, int target) {
		int N = nums.length;
		int result = nums[0] + nums[1] + nums[N - 1];
		Arrays.sort(nums);
		
		for (int i = 0; i < N - 2; i++) {
			int start = i + 1, end = N - 1;
			
			while (start < end) {
				int sum = nums[i] + nums[start] + nums[end];
				
				if (sum > target)	end--;
				else					start++;
				
				if (Math.abs(sum - target) < Math.abs(result - target))	// 差距小就更新
					result = sum;
			}
		}
		
		return result;
	}
	
	/** Improvement: skip duplicates! */
	
	public int threeSumClosest2(int[] nums, int target) {
		int N = nums.length;
		Arrays.sort(nums);
		int res = nums[0] + nums[1] + nums[N - 1];

		for (int i = 0; i < N - 2; i++) {
			if (i == 0 || nums[i] != nums[i - 1]) {		// Skip Duplicates
				int l = i + 1, r = N - 1;
				while (l < r) {
					int sum = nums[l] + nums[r] + nums[i];
					
					if (sum == target)	return sum;
					else if (sum < target) {
						while (l < r && nums[l] == nums[l + 1]) l++;
						l++;
					} else {
						while (l < r && nums[r] == nums[r - 1]) r--;
						r--;
					}
					
					if (Math.abs(target - sum) < Math.abs(target - res))
						res = sum;
				}
			}
		}
		return res;
	}
}
