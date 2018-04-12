package dp;

public class PaintFence_276 {
	
	/* The hard thing for DP is finding the formula
	 * According to the rules, we need to make sure no more than two fences are with the same color
	 * we iterate every new fence with a new color (no matter what colors of the two previous fences)
	 * update the current fence with k-1 colors to the whole colors presented, assign it to diff
	 * But the problem is that we still also need previous two nodes to decide current node
	 * diff = color combination of previous node
	 * same = color combination of the second previous node
	 * Of course this is a permutation problem. It is reasonable to combine previous two nodes when
	 * develop current node's possibility. */
	
	public int numWays(int n, int k) {
		if (n == 0) 			return 0;
		else if (n == 1) 	return k;
		
		int diffColorCounts = k * (k - 1);
		int sameColorCounts = k;
		
		for (int i = 2; i < n; i++) {
			int temp = diffColorCounts;
			diffColorCounts = (diffColorCounts + sameColorCounts) * (k - 1);
			sameColorCounts = temp;
		}
		
		return diffColorCounts + sameColorCounts;
	}
	
	/* Assuming there are 3 posts:
	 *  - if the first one and the second one has the same color, then the third one has k-1 options. 
	 *  		The first and second together has k options. 
	 *  - If the first and the second do not have same color, the total is k * (k-1), 
	 *  		then the third one has k options.
	 *  
	 *  - f(3) = (k-1)*k + k*(k-1)*k = (k-1)(k+k*k) 
	 *  - Formula: f(n) = (k-1)(f(n-1)+f(n-2)) */
	
	public int numWays1(int n, int k) {
		int dp[] = {0, k, k * k, 0};

		if (n <= 2) 		return dp[n];

		for (int i = 2; i < n; i++) {
			dp[3] = (k - 1) * (dp[1] + dp[2]);
			dp[1] = dp[2];
			dp[2] = dp[3];
		}

		return dp[3];
	}
}
