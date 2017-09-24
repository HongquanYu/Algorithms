package dp;

/** 309. Best Time to Buy and Sell Stock with Cooldown
 * 
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock
 * multiple times) with the following restrictions:
 * 
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again). After you sell your stock, you cannot
 * buy stock on next day. (ie, cooldown 1 day) */


public class BestTimeBuySellStockWithCooldown_309 {
	
	public int maxProfit1(int[] prices) {
		
		int sell = 0;
		int prevSell = 0;
		int buy = Integer.MIN_VALUE;
		int prevBuy;
		
		for (int p : prices) {
			prevBuy = buy;
			buy = Math.max(prevSell - p, prevBuy);
			prevSell = sell;
			sell = Math.max(prevBuy + p, prevSell);
		}
		
		return sell;
	}
	
	
	public int maxProfit2(int[] prices) {
		
		if (prices == null || prices.length < 2)
			return 0;
		
		int buy = -prices[0];
		int sell = Integer.MIN_VALUE;
		int rest = 0;
		
		for (int i = 1; i < prices.length; i++) {
			int tmp = buy;
			buy = Math.max(buy, rest - prices[i]);
			rest = Math.max(rest, sell);
			sell = tmp + prices[i];
		}
		
		return Math.max(buy, Math.max(sell, rest));
	}
}
