package twoPointers;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RemoveDuplicatesFromSortedArray_26 {
	public int removeDuplicates(int[] nums) {
		if (nums.length == 0)
			return 0;
		int i = 0;
		for (int j = 1; j < nums.length; j++) {
			if (nums[j] != nums[i]) {
				i++;
				nums[i] = nums[j];
			}
		}
		return i + 1;
	}
	
	public int removeDuplicates2(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int res = 0, N = nums.length;
		for (int i = 0; i < N; ++i) {
			if (i == N - 1 || nums[i] != nums[i + 1])
				nums[res++] = nums[i];
		}

		return res;
	}
}
