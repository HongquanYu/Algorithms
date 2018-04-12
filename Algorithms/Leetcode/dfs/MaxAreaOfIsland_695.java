package dfs;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class MaxAreaOfIsland_695 {
	
	/** 这里不用 visited 数组的话就修改原数组 */
	
	public int maxAreaOfIsland(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;

		int res = 0;
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
				if (grid[i][j] == 1)
					res = Math.max(res, dfs(grid, i, j));

		return res;
	}

	private int dfs(int[][] grid, int i, int j) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1)
			return 0;

		grid[i][j] = 0;
		return 1 + dfs(grid, i, j - 1) + dfs(grid, i - 1, j) + dfs(grid, i, j + 1) + dfs(grid, i + 1, j);
	}
}
