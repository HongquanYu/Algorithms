package dp;

public class PaintHouse2_265 {
	public int minCostII(int[][] costs) {
		int n = costs.length;
		if (n == 0)
			return 0;

		for (int i = 1; i < n; ++i)
			for (int j = 0; j < costs[0].length; ++j) {
				costs[i][j] += minOfPreviousHouse(costs[i - 1], j);
			}

		return minOfPreviousHouse(costs[n - 1], -1);
	}
	
	private int minOfPreviousHouse(int[] costs, int e) {
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < costs.length; ++i) {
			if (i == e)
				continue;
			if (costs[i] < min)
				min = costs[i];
		}

		return min;
	}
}
