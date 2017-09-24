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
		if (n == 0)
			return 0;
		else if (n == 1)
			return k;
		
		int diffColorCounts = k * (k - 1);
		int sameColorCounts = k;
		
		for (int i = 2; i < n; i++) {
			int temp = diffColorCounts;
			diffColorCounts = (diffColorCounts + sameColorCounts) * (k - 1);
			sameColorCounts = temp;
		}
		
		return diffColorCounts + sameColorCounts;
	}
	
    public int numWays_1(int n, int k) {
        if (n == 0 || k == 0)
        	return 0;
        if (n == 1)
        	return k;
        
        int same = k;			// first two are same, k cases.
        int diff = k * (k - 1);	// first two are different, k * (k - 1) cases.
        
        for (int i = 2; i < n; ++i) {	// Calculation for the rest n - 2 elements
        	int tmp = diff;
        	diff = (same + tmp) * (k - 1);		// current post is different as previous one
        	same = tmp;							// current post is same as previous one
        }
    	
    	return same + diff;
    }
}
