package twoPointers;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MaxConsecutiveOnesII_487 {
	public int findMaxConsecutiveOnes(int[] nums) {
		int max = 0, zero = 0, k = 1; // flip at most k zero
		for (int l = 0, h = 0; h < nums.length; h++) {
			if (nums[h] == 0)
				zero++;
			while (zero > k)
				if (nums[l++] == 0)
					zero--;
			max = Math.max(max, h - l + 1);
		}
		return max;
	}
}
