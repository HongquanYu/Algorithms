package array;

import java.util.Arrays;

import utility.Swap;
import divideAndConquer.KthLargestElementInAnArray_215;

/** @author: Hongquan Yu
 *  @date: Apr 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class WiggleSortII_324 {
	
	/**  O(n*lgn) 解法
	 * 先给数组排序，然后在做调整。调整的方法是找到数组的中间的数，相当于把有序数组从中间分成两部分，
	 * 然后从前半段的末尾取一个，在从后半的末尾去一个，这样保证了第一个数小于第二个数，然后从前半段
	 * 取倒数第二个，从后半段取倒数第二个，这保证了第二个数大于第三个数，且第三个数小于第四个数，
	 * 以此类推直至都取完 */
	
	public void wiggleSort(int[] nums) {
		int N = nums.length, k = (N + 1) / 2, j = N;
		int[] tmp = Arrays.copyOf(nums, N);
		Arrays.sort(tmp);

		for (int i = 0; i < N; ++i)
			nums[i] = (i % 2 != 0) ? tmp[--j] : tmp[--k];
	}
	
	/** 线性解法O(n)的时间复杂度和O(1)的空间复杂度, 太难了 */
	
	private KthLargestElementInAnArray_215 kth = new KthLargestElementInAnArray_215();
	
	public void wiggleSort2(int[] nums) {
		int median = kth.findKthLargest(nums, (nums.length + 1) / 2);
		int n = nums.length;

		int left = 0, i = 0, right = n - 1;

		while (i <= right) {
			if (nums[newIndex(i, n)] > median)
				new Swap().swap(nums, newIndex(left++, n), newIndex(i++, n));
			else if (nums[newIndex(i, n)] < median)
				new Swap().swap(nums, newIndex(right--, n), newIndex(i, n));
			else
				i++;
		}
	}

	private int newIndex(int index, int n) {
		return (1 + 2 * index) % (n | 1);
	}
}
