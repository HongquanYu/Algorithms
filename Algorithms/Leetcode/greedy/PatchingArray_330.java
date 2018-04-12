package greedy;

/** @author: Hongquan Yu
 *  @date: Mar 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class PatchingArray_330 {
	
	/** Let missing be the smallest sum in [0, n] that we might be missing. 
	 * Meaning we already know we can build all sums in [0, missing). 
	 * Then if we have a number num <= missing in the given array, we can add it to those smaller
	 * sums to build all sums in [0, missing + num). 
	 * If we don’t, then we must add such a number to the array, and it’s best to add missing itself, 
	 * to maximize the reach. */

	public int minPatches(int[] nums, int n) {
		long missing = 1;		// 初始化为 1 以确保能够得到 reachable 区间的下一个 missing 元素
		int patches = 0, ptr = 0;

		while (missing <= n) {
			if (ptr < nums.length && nums[ptr] <= missing) {
				missing += nums[ptr++];
			} else {			// 添加 missing 以最大化 能够组成的区间
				missing += missing;
				patches++;
			}
		}

		return patches;
	}
}
