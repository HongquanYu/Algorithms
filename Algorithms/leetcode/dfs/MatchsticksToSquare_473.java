package dfs;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MatchsticksToSquare_473 {
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
		if (index == nums.length) {
			if (sums[0] == target && sums[1] == target && sums[2] == target) {
				return true;
			}
			return false;
		}

		for (int i = 0; i < 4; i++) {
			if (sums[i] + nums[index] > target)
				continue;
			sums[i] += nums[index];
			if (dfs(nums, sums, index + 1, target))
				return true;
			sums[i] -= nums[index];
		}

		return false;
	}
}
