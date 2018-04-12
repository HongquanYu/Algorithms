package dp;

public class GuessNumberHigherOrLower2_375 {
	
	/* Brute Force solution. recursion
	 * cost(1, n) = i + max(cost(1, i-1), cost(i+1, n)) */
	
    public int calculate(int low, int high) {
        if (low >= high)
            return 0;
        int minres = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            int res = i + Math.max(calculate(i + 1, high), calculate(low, i - 1));
            minres = Math.min(res, minres);
        }

        return minres;
    }
    
    public int getMoneyAmount(int n) {
        return calculate(1, n);
    }
    
    /* Modified brute force
     * low pointer start from [(i+j)/2 - n] and march towards right will always 
     * larger than left segment. 
     */
    
    public int calculate1(int low, int high) {
        if (low >= high)
            return 0;
        int minres = Integer.MAX_VALUE;
        for (int i = (low + high) / 2; i <= high; i++) {
            int res = i + Math.max(calculate1(i + 1, high), calculate1(low, i - 1));
            minres = Math.min(res, minres);
        }
        return minres;
    }
    public int getMoneyAmount1(int n) {
        return calculate1(1, n);
    }
    
    /* DP solution
     *  dp(i,j) = min[pivot + max(dp(i, pivot-1), dp(pivot+1, n))]
     */
    
	public int getMoneyAmount2(int n) {
		int[][] dp = new int[n + 1][n + 1];
		
		for (int len = 2; len <= n; len++) {			// traverse all the number
			for (int start = 1; start <= n - len + 1; start++) {			// a sliding window, len and start are making
				int minres = Integer.MAX_VALUE;
				for (int piv = start; piv < start + len - 1; piv++) {		// traverse the sliding window
					int res = piv + Math.max(dp[start][piv - 1], dp[piv + 1][start + len - 1]);
					minres = Math.min(res, minres);
				}
				dp[start][start + len - 1] = minres;		// get a minimum of current sliding window
			}
		}
		return dp[1][n];
	}
	
	/* As in approach 2, we can start guess from (i+j)/2 towards right
	 * then dp(i,j) = min[pivot + max(dp(i, pivot-1), dp(pivot+1, n))]
	 */
	
    public int getMoneyAmount3(int n) {
        int[][] dp = new int[n + 1][n + 1];

        for (int len = 2; len <= n; len++) {
            for (int start = 1; start <= n - len + 1; start++) {
                int minres = Integer.MAX_VALUE;
                for (int piv = start + (len - 1) / 2; piv < start + len - 1; piv++) {
                    int res = piv + Math.max(dp[start][piv - 1], dp[piv + 1][start + len - 1]);
                    minres = Math.min(res, minres);
                }
                
                dp[start][start + len - 1] = minres;
            }
        }
        
        return dp[1][n];
    }
}
