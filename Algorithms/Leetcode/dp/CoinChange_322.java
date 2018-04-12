package dp;

import java.util.Arrays;

public class CoinChange_322 {
	
	// editorial brute force solution. backtracking. TLE
	
	public int coinChange(int[] coins, int amount) {
		
		return coinChange(0, coins, amount);
		
	}

	private int coinChange(int idxCoin, int[] coins, int amount) {
		
		if (amount == 0)
			return 0;
		
		if (idxCoin < coins.length && amount > 0) {
			
			int maxVal = amount / coins[idxCoin];	// initialize as maximum number of first denomination
			int minCost = Integer.MAX_VALUE;		// Initialized with maximum value
			
			for (int x = 0; x <= maxVal; x++) {		// 
				
				if (amount >= x * coins[idxCoin]) {	// 
					
					int res = coinChange(idxCoin + 1, coins, amount - x * coins[idxCoin]);
					if (res != -1)
						minCost = Math.min(minCost, res + x);
				}
				
			}
			
			return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
		}
		
		return -1;
	}
	
	/* editorial dp solution - top down
	 *  F(S) - minimum number of coins needed to make change for amount S using coin denominations 
	 *  [c0 … cn − 1] 
	 *  F(S) = F(S − C) + 1, we try every possible denomination to find the best c
	 *  */
	
	public int coinChange2(int[] coins, int amount) {
		
		if (amount < 1)
			return 0;
		
		return coinChange2(coins, amount, new int[amount]);
		
	}
	
	// the final solution is the minimum recursive tree depth.
	
	private int coinChange2(int[] coins, int rem, int[] memo) {
		if (rem < 0)				// there is no solution
			return -1;
		
		if (rem == 0)				// When there is no more amount to be found, return 0
			return 0;
		
		if (memo[rem - 1] != 0)		// computed it before, return the value
			return memo[rem - 1];
		
		int min = Integer.MAX_VALUE;
		
		for (int coin : coins) {	// traverse the coin denomination set and find the minimum denomination
			
			int res = coinChange2(coins, rem - coin, memo);		// recursive check every subproblem, get the least steps solution
			
			if (res >= 0 && res < min)	// skip -1. more optimal solutions to the problem found,
				min = 1 + res;
		}
		
		memo[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;	// store the computed record
		
		return memo[rem - 1];
	}
	
	// editorial dp solution - bottom up
	
	public int coinChange3(int[] coins, int amount) {
		
		int max = amount + 1;
		int[] dp = new int[amount + 1];
		
		Arrays.fill(dp, max);
		dp[0] = 0;
		
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i)		// if there is enough in amount to be subtracted by relative coin
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
			}
		}
		
		return dp[amount] > amount ? -1 : dp[amount];
	}
}
