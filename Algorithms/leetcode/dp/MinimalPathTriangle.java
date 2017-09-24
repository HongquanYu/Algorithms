package dp;

import java.util.List;

public class MinimalPathTriangle {
	
	// update every element in the path with the sum number
	
	public int minimumTotal(List<List<Integer>> triangle) {
		int row = triangle.size();

		for (int i = 1; i < row; ++i) { 			// traverse every row
			triangle.get(i).set(0, triangle.get(i).get(0) + triangle.get(i - 1).get(0)); // first of row
			
			for (int idx = 1; idx < i; ++idx) 		// items among them
				triangle.get(i).set(idx, triangle.get(i).get(idx)
						+ Math.min(triangle.get(i - 1).get(idx - 1), triangle.get(i - 1).get(idx)));
			
			triangle.get(i).set(i, triangle.get(i).get(i) + triangle.get(i - 1).get(i - 1)); // last of row
		}

		int min = Integer.MAX_VALUE;
		for (int i : triangle.get(row - 1))
			min = (min > i) ? i : min;
		return min;
		
	}
	
	public int minimumTotal2(List<List<Integer>> triangle) {
		int n = triangle.size();
		int[] dp = new int[n + 1];

		for (int l = n - 1; l >= 0; l--)	// from last row to first row
			for (int i = 0; i <= l; i++)	// traverse elements within the row
				dp[i] = Math.min(dp[i], dp[i + 1]) + triangle.get(l).get(i);

		return dp[0];
	}
}
