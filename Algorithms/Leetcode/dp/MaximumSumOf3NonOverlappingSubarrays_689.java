package dp;

public class MaximumSumOf3NonOverlappingSubarrays_689 {
	
	/** 思想就是，找出左中右三个子数组的索引！
	 * 先算出数组的累加和并保存下来，left 和 right 分别存的左边和右边的 子数组的起始索引 */
	
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		int N = nums.length, maxsum = 0;
		int[] sum = new int[N + 1];		// 数组累加和数组
		int[] left = new int[N], right = new int[N];		// left 左边子数组起始索引，right右边子数组起始索引
		int[] ans = new int[3];			// 结果
		
		for (int i = 0; i < N; i++)		// 数组前面元素的累加和
			sum[i + 1] = sum[i] + nums[i];
		
		for (int i = k, ksum = sum[k] - sum[0]; i < N; i++) {
			if (sum[i + 1] - sum[i + 1 - k] > ksum) {	// 遇到一个更大的子数组，更新起始索引
				left[i] = i + 1 - k;
				ksum = sum[i + 1] - sum[i + 1 - k];
			} else		// 保持最初的那个索引
				left[i] = left[i - 1];
		}
		
		right[N - k] = N - k;
		for (int i = N - k - 1, ksum = sum[N] - sum[N - k]; i >= 0; i--) {
			if (sum[i + k] - sum[i] >= ksum) {		// 遇到一个更大的子数组，更新起始索引
				right[i] = i;
				ksum = sum[i + k] - sum[i];
			} else		// 保持最初的那个索引
				right[i] = right[i + 1];
		}
		
		for (int i = k; i <= N - 2 * k; i++) {
			int l = left[i - 1], r = right[i + k];
			int ksum = (sum[i + k] - sum[i]) + (sum[l + k] - sum[l]) + (sum[r + k] - sum[r]);
			if (ksum > maxsum)  {
				maxsum = ksum;
				ans = new int[]{l, i, r};	// 更新结果坐标
			}
		}
		return ans;
	}
}
