package dp;

public class LongestIncreasingSubsequence_300 {
	
	/* DP[n] represents the longest increasing subsequence until nth character
	 * DP[n] = DP[i] + 1, if nums[i] < nums[n]. i = [1, n - 1]
	 */
	
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0)
        	return 0;
        
        int[] dp = new int[len];
        dp[0] = 1;
        int maxN = 1;
        
		for (int i = 1; i < len; ++i) {
			int max = 0;						// To find the longest length before current character
			
			for (int j = 0; j < i; ++j) {
				if (nums[j] < nums[i])			// update if there's lower sequence before, skip bigger sequence
					max = Math.max(max, dp[j]);
			}
			
			dp[i] = max + 1;
			maxN = Math.max(maxN, dp[i]);
		}
        
    	return maxN;
    }
}
