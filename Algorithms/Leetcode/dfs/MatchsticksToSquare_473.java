package dfs;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MatchsticksToSquare_473 {
	
	/** 一个数组能组成正方形的条件就是，能够将所有元素分配到四条边并且这四条边相等。
	 * 我们使用 dfs 做法，加到当前边的时候进行 dfs 递归，如果行就返回 true，如果不行
	 * 就回溯当前加上的元素，有点像是 backtrack */
	
	public boolean makesquare(int[] nums) {
		if (nums == null || nums.length < 4)
			return false;
		int sum = 0;
		for (int num : nums)
			sum += num;
		if (sum % 4 != 0)
			return false;

		return dfs(nums, new int[4], 0, sum / 4);
	}

	private boolean dfs(int[] nums, int[] sums, int index, int target) {
		if (index == nums.length)
			return sums[0] == target && sums[1] == target && sums[2] == target;

		for (int i = 0; i < 4; i++) {
			if (sums[i] + nums[index] > target)
				continue;
			sums[i] += nums[index];
			if (dfs(nums, sums, index + 1, target))
				return true;
			sums[i] -= nums[index];	// 说明加到当前边行不通
		}

		return false;
	}
}
