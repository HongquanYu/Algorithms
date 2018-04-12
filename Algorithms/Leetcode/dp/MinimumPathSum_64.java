package dp;

public class MinimumPathSum_64 {
	
	/** dp[i][j] += min(dp[i-1][j], dp[i][j-1]) */
	
	public int minPathSum(int[][] grid) {

		if (grid.length == 0 || grid[0].length == 0)
			return -1;

		int row = grid.length;
		int col = grid[0].length;

		for (int i = 1; i < row; ++i)
			grid[i][0] += grid[i - 1][0];

		for (int i = 1; i < col; ++i)
			grid[0][i] += grid[0][i - 1];

		for (int i = 1; i < row; ++i)
			for (int j = 1; j < col; ++j)
				grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);

		return grid[row - 1][col - 1];
	}
	
	// editorial solution, from 
	
	public int minPathSum2(int[][] grid) {
		
		for (int i = grid.length - 1; i >= 0; i--) {
			for (int j = grid[0].length - 1; j >= 0; j--) {
				
				if (i == grid.length - 1 && j != grid[0].length - 1)
					grid[i][j] = grid[i][j] + grid[i][j + 1];
				else if (j == grid[0].length - 1 && i != grid.length - 1)
					grid[i][j] = grid[i][j] + grid[i + 1][j];
				else if (j != grid[0].length - 1 && i != grid.length - 1)
					grid[i][j] = grid[i][j] + Math.min(grid[i + 1][j], grid[i][j + 1]);
			}
		}
		return grid[0][0];
    }
	
    public int minPathSum3(int[][] grid) {
        int N = grid.length, M = grid[0].length;
        int[][] dp = new int[N + 1][M + 1];
        
        for(int i = 1; i <= N; ++i)
            dp[i][1] = dp[i - 1][1] + grid[i - 1][0];
        for (int i = 1; i <= M; ++i)
            dp[1][i] = dp[1][i - 1] + grid[0][i - 1];
        
        for (int i = 2; i <= N; ++i) {
            for (int j = 2; j <= M; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[N][M];
    }
    
    /**  */
    
    public int minPathSum4(int[][] grid) {
        int N = grid.length, M = grid[0].length;
        int[][] dp = new int[N][M];
        
        for(int i = 0; i < N; ++i)
            dp[i][0] = ((i == 0) ? 0 : dp[i - 1][0]) + grid[i][0];
        for (int i = 0; i < M; ++i)
            dp[0][i] = ((i == 0) ? 0 : dp[0][i - 1]) + grid[0][i];
        
        for (int i = 1; i < N; ++i)
            for (int j = 1; j < M; ++j)
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];

        return dp[N - 1][M - 1];
    }
    
    public static void main(String[] args) {
		int[][] tmp = new int[][] {{1,3,1},{1,5,1},{4,2,1}};
		
		MinimumPathSum_64 m = new MinimumPathSum_64();
		System.out.println(m.minPathSum4(tmp));
		
	}
}
