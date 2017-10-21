package dp;

import java.util.Arrays;

public class DungeonGame_174 {

	public int calculateMinimumHP(int[][] dungeon) {
		if (dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0)
			return 0;
		int m = dungeon.length, n = dungeon[0].length;
		int[][] dp = new int[m][n]; // the minimum health needed to be able to not die at cell i, j
		int last = 1 - dungeon[m - 1][n - 1];
		dp[m - 1][n - 1] = last <= 0 ? 1 : last; // need to make sure the knight don't die at cell
													// i, j
		// initiation last column
		for (int i = m - 2; i >= 0; i--) {
			int temp = dp[i + 1][n - 1] - dungeon[i][n - 1];
			dp[i][n - 1] = temp <= 0 ? 1 : temp;
		}
		// initiate last row
		for (int j = n - 2; j >= 0; j--) {
			int temp = dp[m - 1][j + 1] - dungeon[m - 1][j];
			dp[m - 1][j] = temp <= 0 ? 1 : temp;
		}
		// dp process
		for (int i = m - 2; i >= 0; i--) {
			for (int j = n - 2; j >= 0; j--) {
				int temp = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
				dp[i][j] = temp <= 0 ? 1 : temp;
			}
		}
		return dp[0][0];
	}
	
	public int calculateMinimumHP1(int[][] dungeon) {
		if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0)
			return 0;

		int m = dungeon.length;
		int n = dungeon[0].length;

		int[][] health = new int[m][n];

		health[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);

		for (int i = m - 2; i >= 0; i--) {
			health[i][n - 1] = Math.max(health[i + 1][n - 1] - dungeon[i][n - 1], 1);
		}

		for (int j = n - 2; j >= 0; j--) {
			health[m - 1][j] = Math.max(health[m - 1][j + 1] - dungeon[m - 1][j], 1);
		}

		for (int i = m - 2; i >= 0; i--) {
			for (int j = n - 2; j >= 0; j--) {
				int down = Math.max(health[i + 1][j] - dungeon[i][j], 1);
				int right = Math.max(health[i][j + 1] - dungeon[i][j], 1);
				health[i][j] = Math.min(right, down);
			}
		}

		return health[0][0];
	}
}
