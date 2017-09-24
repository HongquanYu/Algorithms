package dp;

/** 70. Climbing Stairs
 * 
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 * 
 * Note: Given n will be a positive integer. */

public class ClimbStairs_71 {
	/** TLE - Time Complexity too high */
	
	public int climbStairs1(int n) {
		if (n == 0 || n == 1 || n == 2)
			return n;

		return climbStairs1(n - 1) + climbStairs1(n - 2);
	}
	
    /** dp with memo */
	
    public int climbStairs2(int n) {
    	
        if (n == 0 || n == 1 || n == 2)
        	return n;
        
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        
        for (int i = 2; i <= n; i++)
        	dp[i] = dp[i - 1] + dp[i - 2];        
        return dp[n];
    }
    
    
	/** Editorial Solution - TLE Exception - Time: O(2^n)
	 * 
	 * At every step we are calling the function climbStairsclimbStairs for step
	 * 11 and 22, and return the sum of returned values of both functions.
	 * 
	 * climbStairs(i, n) = (i+1, n) + climbStairs(i+2, n)
	 * 
	 * where ii defines the current step and nn defines the destination step.
	 */
    
	public int climbStairs3(int n) {
		return climb_Stairs(0, n);
	}
	
	public int climb_Stairs(int i, int n) {
		if (i > n) 		return 0;
		if (i == n) 	return 1;
		
		return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
	}
	
	
	/**
	 * Editorial Solution - Recursion with memorization - Time: O(n)
	 * 
	 * In the previous approach we are redundantly calculating the result for
	 * every step. Instead, we can store the result at each step in memo
	 * array and directly returning the result from the memo array whenever that
	 * function is called again.
	 * 
	 * In this way we are pruning recursion tree with the help of memo array
	 * and reducing the size of recursion tree upto nn. */
	
	public int climbStairs4(int n) {
        int memo[] = new int[n + 1];
        return climb_Stairs1(0, n, memo);
    }
    private int climb_Stairs1(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {		// the value has been computed, return directly
            return memo[i];
        }
        memo[i] = climb_Stairs1(i + 1, n, memo) + climb_Stairs1(i + 2, n, memo);
        return memo[i];
    }
    
	/**
	 * As we can see this problem can be broken into subproblems, and it
	 * contains the optimal substructure property i.e. its optimal solution can
	 * be constructed efficiently from optimal solutions of its subproblems, we
	 * can use dynamic programming to solve this problem.
	 * 
	 * One can reach i^th i ​th ​​ step in one of the two ways:
	 * 
	 * Taking a single step from (i-1)^{th}(i−1) ​th ​​ step.
	 * 
	 * Taking a step of 22 from (i-2)^{th}(i−2) ​th ​​ step.
	 * 
	 * So, the total number of ways to reach i^{th}i ​th ​​ is equal to sum of
	 * ways of reaching (i-1)^{th}(i−1) ​th ​​ step and ways of reaching
	 * (i-2)^{th}(i−2) ​th ​​ step.
	 * 
	 * Let dp[i]dp[i] denotes the number of ways to reach on i^{th}i ​th ​​
	 * step.
	 * 
	 * dp[i]=dp[i-1]+dp[i-2]dp[i]=dp[i−1]+dp[i−2]
	 */
    
	public int climbStairs5(int n) {
		if (n == 1) {
			return 1;
		}
		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}
}
