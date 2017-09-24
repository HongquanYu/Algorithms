package dp;

public class CombinationSum4_377 {
	
	// brute force solution, TLE
	
	private static int time = 0;
	
    public static int combinationSum4(int[] nums, int target) {
        return helper(nums, target);
    }
    
    private static int helper(int[] nums, int rem) {
    	if (rem == 0) {
    		time++;
    	}
    		
    	
    	for (int i = 0; i < nums.length; ++i) {
    		if (rem - nums[i] >= 0)
    			helper(nums, rem - nums[i]);
    	}
    	
    	return time;
    }
    
  //--------------------------------------------------------------------------
    /* top-down DP solution
     *  DP[i] represents the # of possible combinations to target i */
    
    public static int combinationSum4_2(int[] nums, int target) {
    	int[] dp = new int[target + 1];
    	
    	return helper2(nums, target, dp);
    }
    
    public static int helper2(int[] nums, int rem, int[] dp) {
    	
		for (int j = 0; j < nums.length; ++j) {
			if (rem - nums[j] == 0)
				dp[rem]++;
			else if (rem - nums[j] > 0) {
				if (dp[rem - nums[j]] == 0 && rem - nums[j] > 0)
					dp[rem - nums[j]] = helper2(nums, rem - nums[j], dp);
				dp[rem] += dp[rem - nums[j]];
			}
		}
    	
    	return dp[rem];
    }
    
    /**--------------------------------------------------------------------------
     DP solutions
     DP[n] = DP[n - nums[i]], if n - nums[i] >= 0, i = [0, nums.length) */
    
    public static int combinationSum4_3(int[] nums, int target) {
    	int[] dp = new int[target + 1];
    	dp[0] = 1;
    	
    	for(int j = 1; j < dp.length; ++j) {
    		for (int i = 0; i < nums.length; ++i) {
    			if (j - nums[i] >= 0) {
    				dp[j] += dp[j - nums[i]];
    			}
    		}
    	}
    	
    	return dp[target];
    }
    
    public static void main(String[] args) {
    	int[] a = {3, 33, 334};
    	int target = 10000;
    	//System.out.println("answer: " + combinationSum4(a, target));
    	System.out.println("try: " + combinationSum4_2(a, target));
    }
}
