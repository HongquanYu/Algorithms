package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class NumberOfDistinctIslands_694 {
	
	private static int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public int numDistinctIslands(int[][] grid) {
		
		int r = grid.length, c = grid[0].length;
		Set<List<List<Integer>>> islands = new HashSet<>();
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				List<List<Integer>> island = new ArrayList<>();
				if (dfs(i, j, i, j, grid, r, c, island))
					islands.add(island);
			}
		}
		
		return islands.size();
	}

	private boolean dfs(int i0, int j0, int i, int j, int[][] grid, int row, int col, List<List<Integer>> island) {
		if (i < 0 || row <= i || j < 0 || col <= j || grid[i][j] <= 0)	// 边界检查
			return false;
		
		island.add(Arrays.asList(i - i0, j - j0));	// 
		grid[i][j] *= -1;							// 标记当前元素
		
		for (int d = 0; d < 4; d++)
			dfs(i0, j0, i + delta[d][0], j + delta[d][1], grid, row, col, island);
		
		return true;
	}
	
	
	
	
	private int[][] grid;
	private boolean[][] seen;
	private Set<Integer> shape;

	public void dfs(int i, int j, int row, int col) {
		if (0 <= i && i < row && 0 <= j && j < col && grid[i][j] == 1 && !seen[i][j]) {
			this.seen[i][j] = true;
			this.shape.add((i - row) * 2 * col + (j - col));
			dfs(i + 1, j, row, col);
			dfs(i - 1, j, row, col);
			dfs(i, j + 1, row, col);
			dfs(i, j - 1, row, col);
		}
	}

	public int numDistinctIslands2(int[][] grid) {
		this.grid = grid;
		int r = grid.length, c = grid[0].length;
		
		seen = new boolean[r][c];
		Set shapes = new HashSet<HashSet<Integer>>();

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				shape = new HashSet<Integer>();
				dfs(r, c, i, j);
				if (!shape.isEmpty())
					shapes.add(shape);
			}
		}

		return shapes.size();
	}
}
