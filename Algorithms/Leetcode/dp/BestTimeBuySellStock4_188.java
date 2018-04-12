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
	
	/** DP: dp(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T).
	 * Transition Function:
	 * dp[i][j] = max(dp[i][j - 1], prices[j] + bought)
	 * bought = max(bought, dp[i - 1][j - 1] - prices[j]) - 在j-1天做i-1个股票能获取的最大值，
	 * 
	 * 表示第 j 天，第 i 个交易能获取的最大利润可以从两者取最大：
	 * 1，第 j - 1 天有i笔交易(第 j 天不做交易)
	 * 2，或者在上一天交易的基础上（已经买入），卖掉当前股票 
	 * 
	 * Observation：题意里要求我们最多能进行 k 次交易，这表明了我们可以进行 0 到 k 次交易，只要能获取最大值
	 * 所以可能的情况就是，第8天的最大利润，还是第1天获取的最大利润，第二到第八天都没有进行任何交易！
	 * DP解法是一个暴力搜索算法，分别计算所有天里进行 1，2，3...次交易，直到第K次！ */
	
	public int maxProfit(int k, int[] prices) {
		int N = prices.length;
		
		if (k >= N / 2)
			return getMaxProfit(prices);

		int[][] dp = new int[k + 1][N];	// dp[0][i]没有 transaction 没有利润
		
		for (int i = 1; i <= k; i++) {	// 循环 k 个交易
			int bought = dp[i-1][0] - prices[0];		// 买入第一个股票价
			
			for (int j = 1; j < N; j++) {	// 对于每一个交易，看所有的时间
				dp[i][j] = Math.max(dp[i][j - 1], prices[j] + bought);
				bought = Math.max(bought, dp[i - 1][j - 1] - prices[j]);
			}
		}
		return dp[k][N - 1];
	}

	private int getMaxProfit(int[] prices) {
		int N = prices.length, profit = 0;
		
		for (int i = 1; i < N; i++)
			if (prices[i] > prices[i - 1])		// price gap exists, profit exists
				profit += (prices[i] - prices[i - 1]);
		
		return profit;
	}
}
