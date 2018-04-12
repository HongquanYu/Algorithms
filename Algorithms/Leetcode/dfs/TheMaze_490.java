package dfs;

import java.util.LinkedList;
import java.util.Queue;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class TheMaze_490 {
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		return dfs(maze, start, destination, visited);
	}

	public boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
		if (visited[start[0]][start[1]])
			return false;
		if (start[0] == destination[0] && start[1] == destination[1])
			return true;
		visited[start[0]][start[1]] = true;
		int r = start[1] + 1, l = start[1] - 1, u = start[0] - 1, d = start[0] + 1;
		while (r < maze[0].length && maze[start[0]][r] == 0) // right
			r++;
		if (dfs(maze, new int[] {start[0], r - 1}, destination, visited))
			return true;
		while (l >= 0 && maze[start[0]][l] == 0) // left
			l--;
		if (dfs(maze, new int[] {start[0], l + 1}, destination, visited))
			return true;
		while (u >= 0 && maze[u][start[1]] == 0) // up
			u--;
		if (dfs(maze, new int[] {u + 1, start[1]}, destination, visited))
			return true;
		while (d < maze.length && maze[d][start[1]] == 0) // down
			d++;
		if (dfs(maze, new int[] {d - 1, start[1]}, destination, visited))
			return true;
		return false;
	}
	
	
	
	public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
		int m = maze.length, n = maze[0].length;
		boolean[][] visited = new boolean[m][n];
		int[] dx = new int[] {0, -1, 0, 1};
		int[] dy = new int[] {1, 0, -1, 0};

		Queue<int[]> queue = new LinkedList<>();
		queue.offer(start);
		visited[start[0]][start[1]] = true;

		while (!queue.isEmpty()) {
			int[] curPos = queue.poll();
			if (curPos[0] == destination[0] && curPos[1] == destination[1]) {
				return true;
			}
			// try four direction until it hits the wall
			for (int direction = 0; direction < 4; direction++) {
				int nx = curPos[0], ny = curPos[1];
				while (nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] == 0) {
					nx += dx[direction];
					ny += dy[direction];
				}

				// back one step
				nx -= dx[direction];
				ny -= dy[direction];

				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.offer(new int[] {nx, ny});
				}
			}
		}
		return false;
	}
}
