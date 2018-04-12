package dp;

/** 121. Best Time to Buy and Sell Stock
 * 
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum
 * profit. */

public class BestTimeBuySellStock1_121 {
	
	/** We need to find out the maximum difference (which will be the maximum
	 * profit) between two numbers in the given array. Also, the second number
	 * (selling price) must be larger than the first one (buying price).
	 * 
	 * In formal terms, we need to find max(prices[j] - prices[i]), j > i. */
	
	public int maxProfit1(int prices[]) {
		int maxProfit = 0;
		
		for (int i = 0; i < prices.length - 1; i++) {
			for (int j = i + 1; j < prices.length; j++) {
				int profit = prices[j] - prices[i];
				if (profit > maxProfit)
					maxProfit = profit;
			}
		}
		return maxProfit;
	}
	
	/** O(n) solution */
	
	public int maxProfit2(int prices[]) {
		int minPrice = Integer.MAX_VALUE, maxProfit = 0;

		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minPrice)
				minPrice = prices[i];
			else if (prices[i] - minPrice > maxProfit)
				maxProfit = prices[i] - minPrice;
		}
		return maxProfit;
	}
	
	/**
	 * maxCur = current maximum value
	 * maxSoFar = maximum value found so far
	 */
	
	public int maxProfit3(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        
        return maxSoFar;
    }
}
