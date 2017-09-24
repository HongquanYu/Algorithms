package dp;

public class BurstBallons_312 {
	
	/* Divide and conquer with memoization */
	
	public int maxCoins1(int[] iNums) {
		
		int[] nums = new int[iNums.length + 2];
		int n = 1;
		
		for (int x : iNums) {		// eliminate negative elements in consecutive spaces[1,n]
			if (x > 0)
				nums[n++] = x;
		}
			
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
	
	/* DP */
	
	public int maxCoins2(int[] iNums) {
		int[] nums = new int[iNums.length + 2];		// pre-processing the array
		int n = 1;

		for (int x : iNums) {						// eliminate negative values
			if (x > 0) nums[n++] = x;
		}
			
		nums[0] = nums[n++] = 1;	// valid actual array range[1, n]. nums[0] = nums[n + 1] = 1
		int[][] dp = new int[n][n];
		
		for (int k = 2; k < n; ++k)					// k = [2, n-1]
			for (int l = 0; l < n - k; ++l) {		// sub-array left boundary - range [0, n-3]
				int r = l + k;						// sub-array right boundary - range [2, ]
				for (int i = l + 1; i < r; ++i)
					dp[l][r] = Math.max(dp[l][r], nums[l] * nums[i] * nums[r] + dp[l][i] + dp[i][r]);
			}

		return dp[0][n - 1];
	}
	
}
