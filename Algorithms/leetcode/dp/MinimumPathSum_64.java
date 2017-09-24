package dp;

public class MinimumPathSum_64 {
	
	/* grid[i][j] += min(grid[i-1][j], grid[i][j-1]) */
	
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
}
