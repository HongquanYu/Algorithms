package bfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ShortestDistanceFromAllBuildings_317 {
	
	static final int[] delta = new int[] {0, 1, 0, -1, 0};
	int min = Integer.MAX_VALUE;

	public int shortestDistance(int[][] grid) {
		int[][] dist = new int[grid.length][grid[0].length];
		int start = 1;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					bfsVisit(grid, dist, i, j, --start);
				}
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	private void bfsVisit(int[][] grid, int[][] dist, int row, int col, int start) {
		Deque<int[]> que = new ArrayDeque<int[]>();
		que.offer(new int[] {row, col});
		int level = 0;
		min = Integer.MAX_VALUE;
		while (!que.isEmpty()) {
			int size = que.size();
			level++;
			for (int k = 0; k < size; k++) {
				int[] node = que.poll();
				for (int i = 1; i < delta.length; i++) {
					int newRow = node[0] + delta[i - 1];
					int newCol = node[1] + delta[i];
					if (newRow >= 0 && newRow < grid.length && newCol >= 0
							&& newCol < grid[0].length && grid[newRow][newCol] == start) {
						que.offer(new int[] {newRow, newCol});
						dist[newRow][newCol] += level;
						min = Math.min(min, dist[newRow][newCol]);
						grid[newRow][newCol]--;
					}
				}
			}
		}
	}
	
	
	public int shortestDistance2(int[][] grid) {
		if (grid == null || grid[0].length == 0)
			return 0;
		final int[] shift = new int[] {0, 1, 0, -1, 0};

		int row = grid.length, col = grid[0].length;
		int[][] distance = new int[row][col];
		int[][] reach = new int[row][col];
		int buildingNum = 0;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == 1) {
					buildingNum++;
					Queue<int[]> myQueue = new LinkedList<int[]>();
					myQueue.offer(new int[] {i, j});

					boolean[][] isVisited = new boolean[row][col];
					int level = 1;

					while (!myQueue.isEmpty()) {
						int qSize = myQueue.size();
						for (int q = 0; q < qSize; q++) {
							int[] curr = myQueue.poll();

							for (int k = 0; k < 4; k++) {
								int nextRow = curr[0] + shift[k];
								int nextCol = curr[1] + shift[k + 1];

								if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col
										&& grid[nextRow][nextCol] == 0
										&& !isVisited[nextRow][nextCol]) {
									// The shortest distance from [nextRow][nextCol] to thic
									// building
									// is 'level'.
									distance[nextRow][nextCol] += level;
									reach[nextRow][nextCol]++;

									isVisited[nextRow][nextCol] = true;
									myQueue.offer(new int[] {nextRow, nextCol});
								}
							}
						}
						level++;
					}
				}
			}
		}

		int shortest = Integer.MAX_VALUE;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == 0 && reach[i][j] == buildingNum) {
					shortest = Math.min(shortest, distance[i][j]);
				}
			}
		}

		return shortest == Integer.MAX_VALUE ? -1 : shortest;
	}
}
