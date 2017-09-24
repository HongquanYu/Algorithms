package dp;

/** 188. Best Time to Buy and Sell Stock IV
 * 
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most k
 * transactions.
 * 
 * Note: You may not engage in multiple transactions at the same time (ie, you
 * must sell the stock before you buy again). */

public class BestTimeBuySellStock4_188 {
	
	/** DP: dp(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T). */
	
	public int maxProfit(int k, int[] prices) {
		
		int len = prices.length;
		
		if (k >= len / 2)
			return helper(prices);

		int[][] dp = new int[k + 1][len];
		
		for (int i = 1; i <= k; i++) {
			
			int tmpMax = dp[i-1][0] - prices[0];
			
			for (int j = 1; j < len; j++) {
				dp[i][j] = Math.max(dp[i][j - 1], prices[j] + tmpMax);
				tmpMax = Math.max(tmpMax, dp[i - 1][j - 1] - prices[j]);
			}
		}
		
		return dp[k][len - 1];
	}

	private int helper(int[] prices) {
		
		int len = prices.length, profit = 0;
		
		for (int i = 1; i < len; i++)
			if (prices[i] > prices[i - 1])		// price gap exists, profit exists
				profit += (prices[i] - prices[i - 1]);
		
		return profit;
	}
}
