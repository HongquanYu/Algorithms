package bfs;

import java.util.PriorityQueue;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class TrappingRainWaterII_407 {
	public class Cell {
		int row;
		int col;
		int height;

		public Cell(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}
	}

	public int trapRainWater(int[][] heights) {
		if(heights == null || heights.length <= 2 || heights[0].length <= 2) 
			return 0;

		PriorityQueue<Cell> pq = new PriorityQueue<>(1, (a, b) -> a.height - b.height);

		int m = heights.length;
		int n = heights[0].length;
		boolean[][] visited = new boolean[m][n];

		// Initially, add all the Cells which are on borders to the pq.
		for (int i = 0; i < m; i++) {
			visited[i][0] = true;
			visited[i][n - 1] = true;
			pq.offer(new Cell(i, 0, heights[i][0]));
			pq.offer(new Cell(i, n - 1, heights[i][n - 1]));
		}

		for (int i = 0; i < n; i++) {
			visited[0][i] = true;
			visited[m - 1][i] = true;
			pq.offer(new Cell(0, i, heights[0][i]));
			pq.offer(new Cell(m - 1, i, heights[m - 1][i]));
		}
		
		int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int res = 0;
		while (!pq.isEmpty()) {
			Cell cell = pq.poll();
			for (int[] dir : dirs) {
				int row = cell.row + dir[0];
				int col = cell.col + dir[1];
				if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
					visited[row][col] = true;
					res += Math.max(0, cell.height - heights[row][col]);
					pq.offer(new Cell(row, col, Math.max(heights[row][col], cell.height)));
				}
			}
		}

		return res;
	}
}
