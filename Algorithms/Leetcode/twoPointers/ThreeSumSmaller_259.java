package twoPointers;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ThreeSumSmaller_259 {
	public int threeSumSmaller(int[] nums, int target) {
		Arrays.sort(nums);
		int sum = 0;
		for (int i = 0; i < nums.length - 2; i++) {
			sum += twoSumSmaller(nums, i + 1, target - nums[i]);
		}
		return sum;
	}

	private int twoSumSmaller(int[] nums, int startIndex, int target) {
		int sum = 0;
		int left = startIndex;
		int right = nums.length - 1;
		while (left < right) {
			if (nums[left] + nums[right] < target) {
				sum += right - left;
				left++;
			} else {
				right--;
			}
		}
		return sum;
	}
}
