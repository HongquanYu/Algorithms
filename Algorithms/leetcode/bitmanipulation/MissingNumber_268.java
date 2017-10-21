package bitmanipulation;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MissingNumber_268 {
	public int missingNumber(int[] nums) {
		int xor = 0, i = 0;
		for (i = 0; i < nums.length; i++) {
			xor = xor ^ i ^ nums[i];
		}

		return xor ^ i;
	}
	
	public int missingNumber2(int[] nums) { // sum
		int len = nums.length;
		int sum = (0 + len) * (len + 1) / 2;
		for (int i = 0; i < len; i++)
			sum -= nums[i];
		return sum;
	}
	
	public int missingNumber3(int[] nums) { // binary search
		Arrays.sort(nums);
		int left = 0, right = nums.length, mid = (left + right) / 2;
		while (left < right) {
			mid = (left + right) / 2;
			if (nums[mid] > mid)
				right = mid;
			else
				left = mid + 1;
		}
		return left;
	}
}
