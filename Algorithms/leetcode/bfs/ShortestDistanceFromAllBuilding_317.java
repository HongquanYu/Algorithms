package bfs;

import java.util.ArrayDeque;
import java.util.Deque;

/** The idea is very simple:
 * For every building in the map, we computed it's reachable's neighbors distances and record them at a dist matrix
 * After calculation of every building, the dist matrix is filled with values which are sums of it to every building.
 * Then we pick the minimum value of the dist matrix, the spot is the closest spot to every building. 
 * 
 * WHY WE NEED TO UPDATE GIRD MATRIX VALUE BY SUBTRACTING THE SPOT ONE??
 * 
 * Because if we do not update them, we would calculate more spots that some are not connectable to some buildings.
 * */

public class ShortestDistanceFromAllBuilding_317 {
	private static final int[] delta = new int[] {0, 1, 0, -1, 0};		// the array is used for calculating it's four neighbors
	private int min = Integer.MAX_VALUE;

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
	
	/* bfsVisit searches all the reachable spots and record the distance of current node to them.
	 * And also we need to make all the passable spots so we can find them in the next round.
	 * At the calculation process, we track the minimum distance to all the buildings so far met.
	 * When we traverse all the building, we find the closest spot to all buildings. */

	private void bfsVisit(int[][] grid, int[][] dist, int row, int col, int start) {		// do a BFS search at grid(row, col)
		Deque<int[]> que = new ArrayDeque<int[]>();
		que.offer(new int[] {row, col});
		int level = 0;
		min = Integer.MAX_VALUE;
		
		while (!que.isEmpty()) {			// check all the nodes until there's no reachable one
			int size = que.size();
			level++;
			
			for (int k = 0; k < size; k++) {			// process node in every layer
				int[] node = que.poll();
				
				for (int i = 1; i < delta.length; i++) {		// check four neighbors
					int newRow = node[0] + delta[i - 1];
					int newCol = node[1] + delta[i];
					
					if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && grid[newRow][newCol] == start) {	// valid in matrix and reachable
						que.offer(new int[] {newRow, newCol});		// enqueue new neighbor
						dist[newRow][newCol] += level;				// distance of (row, col) and it's neighbor 
						min = Math.min(min, dist[newRow][newCol]);	// track minimum spot to met buildings so far
						grid[newRow][newCol]--;						// update new neighbor by subtracting its value
					}
				}
			}
		}
	}
}
