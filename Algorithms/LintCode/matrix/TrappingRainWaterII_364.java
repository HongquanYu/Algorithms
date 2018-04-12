package matrix;

import java.util.PriorityQueue;

/** @author: Hongquan Yu
 *  @date: Feb 14, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class TrappingRainWaterII_364 {
	class Cell {
		public int x, y, h;

		public Cell() { }

		public Cell(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}
	
	public int trapRainWater(int[][] heights) {
		// Input validation
		if (heights == null || heights.length == 0 || heights[0].length == 0)
			return 0;

		int m = heights.length;
		int n = heights[0].length;

		// Initialize min-heap minHeap, visited matrix visited[][]
		PriorityQueue<Cell> minHeap = new PriorityQueue<>(1, (c1, c2) -> c1.h - c2.h);
		int[][] visited = new int[m][n];

		// Traverse the outer cells, add to the minHeap
		for (int i = 0; i < m; i++) {
			minHeap.offer(new Cell(i, 0, heights[i][0]));
			minHeap.offer(new Cell(i, n - 1, heights[i][n - 1]));

			visited[i][0] = 1;
			visited[i][n - 1] = 1;
		}
		for (int j = 0; j < n; j++) {
			minHeap.offer(new Cell(0, j, heights[0][j]));
			minHeap.offer(new Cell(m - 1, j, heights[m - 1][j]));

			visited[0][j] = 1;
			visited[m - 1][j] = 1;
		}

		// Helper direction array
		int[] dirX = new int[]{ 0, 0, -1, 1 };
		int[] dirY = new int[]{ -1, 1, 0, 0 };

		int water = 0;

		// Starting from the min height cell, check 4 direction
		while (!minHeap.isEmpty()) {
			Cell cur = minHeap.poll();

			for (int k = 0; k < 4; k++) {
				int x = cur.x + dirX[k];
				int y = cur.y + dirY[k];

				if (x < m && x >= 0 && y < n && y >= 0 && visited[x][y] != 1) {
					minHeap.offer(new Cell(x, y, Math.max(cur.h, heights[x][y])));
					visited[x][y] = 1;

					// Fill in water or not
					water += Math.max(0, cur.h - heights[x][y]);
				}
			}
		}
		
		return water;
	}
}
