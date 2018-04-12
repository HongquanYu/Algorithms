package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class NumberOfDistinctIslandsII_711 {
	static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	public int numDistinctIslands2(int[][] grid) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					List<int[]> tuple = new ArrayList<>();
					dfs(i, j, grid, tuple);
					int hash = computeHash(tuple);
					set.add(hash);
				}
			}
		}
		return set.size();
	}

	public int computeHash(List<int[]> tuple) {
		int n = tuple.size();
		int hash = 0;
		HashMap<Integer, Integer> stats1 = new HashMap();
		HashMap<Integer, Integer> stats2 = new HashMap();
		for (int i = 0; i < n; ++i) {
			int[] pt1 = tuple.get(i);
			stats1.put(pt1[0], stats1.getOrDefault(pt1[0], 0) + 1);
			stats2.put(pt1[1], stats2.getOrDefault(pt1[1], 0) + 1);
			for (int j = i + 1; j < n; ++j) {
				int[] pt2 = tuple.get(j);
				int delta_x = pt1[0] - pt2[0];
				int delta_y = pt1[1] - pt2[1];
				if (delta_x == 0 || delta_y == 0) {
					hash += 19 * (delta_x * delta_x + delta_y * delta_y);
				} else {
					hash += 31 * (delta_x * delta_x + delta_y * delta_y);
				}
			}
		}

		for (int kv : stats1.keySet()) {
			hash += 73 * stats1.get(kv) * stats1.get(kv);
		}
		for (int kv : stats2.keySet()) {
			hash += 73 * stats2.get(kv) * stats2.get(kv);
		}

		int delta_x = stats1.size(), delta_y = stats2.size();
		hash += 193 * (delta_x * delta_x + delta_y * delta_y) + 97 * delta_x * delta_y;
		return hash;
	}

	public void dfs(int row, int col, int[][] grid, List<int[]> tuple) {
		if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length)
			return;
		if (grid[row][col] == 0)
			return;

		grid[row][col] = 0;
		tuple.add(new int[] {row, col});
		for (int dir[] : dirs) {
			dfs(row + dir[0], col + dir[1], grid, tuple);
		}
	}
}
