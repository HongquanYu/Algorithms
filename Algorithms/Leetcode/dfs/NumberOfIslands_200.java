package dfs;

import java.util.LinkedList;
import java.util.Queue;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class NumberOfIslands_200 {
	public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int N = grid.length, M = grid[0].length, count = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == '1') {
					dfs(grid, i, j);
					++count;
				}
			}
		}
		
		return count;
	}

	private void dfs(char[][] grid, int i, int j) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1')
			return;
		grid[i][j] = '0';
		dfs(grid, i + 1, j);
		dfs(grid, i - 1, j);
		dfs(grid, i, j + 1);
		dfs(grid, i, j - 1);
	}
	
	/* BFS */

	public static int numIslands1(char[][] grid) {
		int res = 0;
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					bfsFill(grid, i, j);
					res++;
				}
			}
		}
		
		return res;
	}

	private static void bfsFill(char[][] grid, int x, int y) {
		
		grid[x][y] = '0';
		int r = grid.length, c = grid[0].length;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(x * c + y);
		
		while (!queue.isEmpty()) {
			int pos = queue.poll();
			int i = pos / c, j = pos % c;
			if (i > 0 && grid[i - 1][j] == '1')  {	// search upward and mark adjacent '1's as '0'.
				queue.offer((i - 1) * c + j);
				grid[i - 1][j] = '0';
			}

			if (j > 0 && grid[i][j - 1] == '1')  {	// left
				queue.offer(i * c + j - 1);
				grid[i][j - 1] = '0';
			}
			if (j < c - 1 && grid[i][j + 1] == '1') {	// Right forward
				System.out.println("right: " + (i * c + j + 1));
				queue.offer(i * c + j + 1);
				
				grid[i][j + 1] = '0';
			}
			if (i < r - 1 && grid[i + 1][j] == '1') {  	// Downward
				System.out.println("Down: " + ((i + 1) * c + j));
				queue.offer((i + 1) * c + j);
				grid[i + 1][j] = '0';
			}
		}
	}
	
	public static void main(String [] args) {
		char [][] cs = new char[][] {{'1', '1', '1'}, 
									{'0', '1', '0'}, 
									{'1', '1', '1'}};
		System.out.println("Res: " + numIslands1(cs));
	}
}
