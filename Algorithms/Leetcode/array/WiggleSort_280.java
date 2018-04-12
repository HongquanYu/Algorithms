package array;

import java.util.Arrays;
import utility.Swap;

/** @author: Hongquan Yu
 *  @date: Apr 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class WiggleSort_280 {
	
	/** 第一种解法：
	 * 先来看一种时间复杂度为O(nlgn)的方法，思路是先给数组排个序，然后我们只要每次把第三个数和第二个数
	 * 调换个位置，第五个数和第四个数调换个位置，以此类推直至数组末尾，这样我们就能完成摆动排序了 */

	public void wiggleSort(int[] nums) {
		Arrays.sort(nums);
		if (nums.length <= 2)
			return;
		
		for (int i = 2; i < nums.length; i += 2)
			new Swap().swap(nums, i, i - 1);
	}
	
	/** 线性解法：
	 * 根据题目要求的nums[0] <= nums[1] >= nums[2] <= nums[3]....，我们可以总结出如下规律：
	 * 	- 当i为奇数时，nums[i] >= nums[i - 1]
	 * 	- 当i为偶数时，nums[i] <= nums[i - 1]
	 * 那么我们只要对每个数字，根据其奇偶性，跟其对应的条件比较，如果不符合就和前面的数交换位置即可 */
	
	public void wiggleSort2(int[] nums) {
		if (nums.length <= 1)
			return;
		for (int i = 1; i < nums.length; ++i) {
			if (i % 2 == 1 && nums[i] < nums[i - 1] ||
					i % 2 == 0 && nums[i] > nums[i - 1])
				new Swap().swap(nums, i, i - 1);
		}
	}
	
	public static void main(String[] args) {
		int[] array = new int[] {3, 5, 2, 1, 6, 4};
		new WiggleSort_280().wiggleSort2(array);
		System.out.println(Arrays.toString(array));
	}
}
