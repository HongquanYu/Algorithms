package dp;

public class UniquePaths2_63 {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid.length == 0)
			return 0;

		int row = obstacleGrid.length, col = obstacleGrid[0].length;
		int[][] dp = new int[row + 1][col + 1];

		for (int i = 0; i <= row; ++i)		// 第一列全部设为 0
			dp[i][0] = 0;
		dp[1][0] = 1;		// 将第一个设为 1

		for (int i = 0; i <= col; ++i)		// 第一行全是设为 0
			dp[0][i] = 0;

		for (int i = 1; i <= row; ++i)
			for (int j = 1; j <= col; ++j) {
				if (obstacleGrid[i - 1][j - 1] == 1) 	// meet a obstacle, clear it
					dp[i][j] = 0;
				else
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}

		return dp[row][col];
	}
}
