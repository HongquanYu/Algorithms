package dp;

import utility.printMatrix;

public class BurstBallons_312 {
	
	/** DP
	 * 思想是我们按照最后一个 burst 的 ballon 进行划分不同的 case，这样我们的子case 就
	 * 和 当前 case 不会有影响。
	 * 我们对每一个 subarray， 长度从 1 到 n，进行计算burst 的最大值，然后用他们来计算
	 * 比他们更长的subarray 的最大值，最后一步计算原数组的最大值！ */
	
	public int maxCoins2(int[] A) {
		int[] nums = new int[A.length + 2];		// pre-processing the array
		int N = 1;
		
		for (int x : A)	// Copy elements and eliminate negative values
			if (x > 0) 	nums[N++] = x;
		
		nums[0] = nums[N++] = 1;
		int[][] dp = new int[N][N];
		
		for (int k = 2; k < N; ++k) {
			for (int l = 0; l < N - k; ++l) {
				int r = l + k;
				for (int i = l + 1; i < r; ++i)
					dp[l][r] = Math.max(dp[l][r], nums[l] * nums[i] * nums[r] + dp[l][i] + dp[i][r]);
			}
		}
		new printMatrix().print(dp);
		
		return dp[0][N - 1];
	}
	
	public static void main(String[] args) {
		int[] tmp = new int[] {3, 1, 5, 8};
		BurstBallons_312 b = new BurstBallons_312();
		
		System.out.println(b.maxCoins2(tmp));
	}
	
	/** Divide and conquer with memoization */
	
	public int maxCoins1(int[] A) {
		int[] nums = new int[A.length + 2];
		int n = 1;
		
		for (int x : A)		// eliminate negative elements in consecutive spaces[1,n]
			if (x > 0)
				nums[n++] = x;
			
		nums[0] = nums[n++] = 1; 	// first and last are 1s
		int[][] memo = new int[n][n];
		
		return burst(memo, nums, 0, n - 1);
	}

	public int burst(int[][] memo, int[] nums, int l, int r) {
		if (l + 1 == r)
			return 0;
		
		if (memo[l][r] > 0)		// it has been computed, retrieve it
			return memo[l][r];
		
		int ans = 0;
		for (int i = l + 1; i < r; ++i)
			ans = Math.max(ans, nums[l] * nums[i] * nums[r] + burst(memo, nums, l, i) + burst(memo, nums, i, r));
		
		memo[l][r] = ans;
		
		return ans;
	}
	
}
