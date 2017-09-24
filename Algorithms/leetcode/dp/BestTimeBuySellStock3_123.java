package dp;

/**
 * 123. Best Time to Buy and Sell Stock III
 * 
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most two
 * transactions.
 * 
 * Note: You may not engage in multiple transactions at the same time (ie, you
 * must sell the stock before you buy again).
 */

public class BestTimeBuySellStock3_123 {
	
	public int maxProfit1(int[] prices) {
		
        int buy1 = Integer.MIN_VALUE;
        int buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        
        for(int i : prices){                        // Assume we only have 0 money at first
            sell2 = Math.max(sell2, buy2 + i);     	// The maximum if we've just sold 2nd stock so far.
            buy2  = Math.max(buy2,  sell1 - i);  	// The maximum if we've just buy  2nd stock so far.
            sell1 = Math.max(sell1, buy1 + i);     	// The maximum if we've just sold 1st stock so far.
            buy1  = Math.max(buy1,  -i);            // The maximum if we've just buy  1st stock so far. 
        }
        
        return sell2; // Since sell1 is initiated as 0, so sell2 will always higher than sell1.
    }
	
	/** Formula:
	 * DP [K, i]: Max profit up to day i using at most K transactions.
	 * DP [K, i] = max (DP[K, i-1], prices[i] - prices[j] + DP[K-1, j]). price[i] is a constant
	 * 			 = max (DP[K, i-1], prices[i] + max(DP[K-1, j] - prices[j])). i: [1, n-1], j: [0, i-1].
	 * 
	 * 		prices[i] - prices[j] = profit of current transaction
	 * 		Note: subtracting price at j is for the last additional transaction to sell at day i
	 * 
	 * DP [0, i] = 0; no transaction makes 0 profit
	 * DP [K, 0] = 0; First day cannot complete a complete transaction, therefore 0 profit
	 */
	
	public int maxProfit2(int[] prices) {
		int K = 2; 							// number of max transaction allowed
		int N = prices.length;

		if (N <= 1) return 0;

		int[][] DP = new int[K + 1][N];
		
		for (int i = 1; i <= K; i++) { 				// curMax - current max until i-1th transaction
			
			int curMax = DP[i - 1][0] - prices[0]; 	// initialized as buy one stock at i-1th transaction

			for (int j = 1; j < N; j++) { 			// traverse start from 2nd item
				
				DP[i][j] = Math.max(DP[i][j - 1], prices[j] + curMax);
				curMax = Math.max(curMax, DP[i - 1][j] - prices[j]);
			}
		}
		
		return DP[K][N - 1];
	}
	
	/**
	 * This can be solve by "divide and conquer". We use left[i] to track the
	 * maximum profit for transactions before i, and use right[i] to track the
	 * maximum profit for transactions after i. You can use the following
	 * example to understand the Java solution:
	 * 		Prices: 1, 4, 5, 7, 6, 3, 2, 9
	 * 		left = [0, 3, 4, 6, 6, 6, 6, 8]
	 * 		right= [8, 7, 7, 7, 7, 7, 7, 0]
	 */
	
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2)
			return 0;

		// highest profit in 0 ... i
		int[] left = new int[prices.length];
		int[] right = new int[prices.length];

		// DP from left to right
		left[0] = 0;
		int min = prices[0];
		
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			left[i] = Math.max(left[i - 1], prices[i] - min);
		}

		// DP from right to left
		right[prices.length - 1] = 0;
		int max = prices[prices.length - 1];
		
		for (int i = prices.length - 2; i >= 0; i--) {
			max = Math.max(max, prices[i]);
			right[i] = Math.max(right[i + 1], max - prices[i]);
		}

		int profit = 0;
		
		for (int i = 0; i < prices.length; i++)
			profit = Math.max(profit, left[i] + right[i]);

		return profit;
	}
}
