package dp;

public class IntegerBreak_343 {
	static int counter = 0;
	
	// Top down Brute force solution
	
	public static int integerBreak(int n) {
		if (n <= 1)
			return 0;
		if (n == 2)
			return 1;
		
		int[] memo = new int[n + 1];
		memo[0] = 0;
		memo[1] = 1;
		
		return helper(n, memo);
	}
	
	
    public static int helper(int n, int[] memo) {
    	if (memo[n] != 0)
    		return memo[n];
    	else {
    		int max = Integer.MIN_VALUE;
    		for (int i = 1; i < n; ++i) {
    			max = Math.max(max, i * Math.max(n - i, helper(n - i, memo)));
    		}
    		
    		memo[n] = max;
    		return max;
    	}
    }

	// fastest solution
    
	public static int integerBreak2(int n) {
		if (n == 2) return 1;
		if (n == 3) return 2;
		
		int maxP = 1;
		while (n > 4) {
			maxP *= 3;
			n -= 3;
		}
		maxP *= n;

		return maxP;
	}
	
	// DP solution
	
	public int integerBreak3(int n) {
	       int[] dp = new int[n + 1];
	       
	       dp[1] = 1;
	       
	       for(int i = 2; i <= n; i ++) {
	           for(int j = 1; j < i; j ++) {
	               dp[i] = Math.max(dp[i], (Math.max(j, dp[j])) * (Math.max(i - j, dp[i - j])));
	           }
	       }
	       return dp[n];
	    }
	
	// Since dp[i + j] can be i * j.
	// dp[i+j] = max(max(dp[i], i) * max(dp[j], j)), dp[i+j]).
	
	public int integerBreak4(int n) {
		int[] dp = new int[n + 1];

		for (int i = 1; i < n; i++) {	// range [1, n)
			for (int j = 1; j < i + 1; j++) {	// range [1, i]
				if (i + j <= n) {
					dp[i + j] = Math.max(Math.max(dp[i], i) * Math.max(dp[j], j), dp[i + j]);
				}
			}
		}

		return dp[n];
	}
    
    public static void main(String[] args) {
    	int i = 26;
    	System.out.println("answer: " + integerBreak2(i));
    	System.out.println("my fuc: " + integerBreak(i));
    	System.out.println("counter: " + counter);
    }
}
