package twoPointers;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SortColors_75 {
	public void sortColors(int[] nums) {
		if (nums == null || nums.length < 2)
			return;
		int low = 0, high = nums.length - 1;
		for (int i = low; i <= high; ) {
			if (nums[i] == 0)
				swap(nums, i++, low++);
			else if (nums[i] == 2)
				swap(nums, i, high--);
			else
				i++;
		}
	}

	private void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
}
