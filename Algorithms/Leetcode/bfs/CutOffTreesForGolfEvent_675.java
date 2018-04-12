package bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class CutOffTreesForGolfEvent_675 {
	
	/** 1，Since we have to cut trees in order of their height, we first put trees 
	 * (int[] {row, col, height}) into a priority queue and sort by height.
	 * 2，Poll each tree from the queue and use BFS to find out steps needed. */
	
	static int[][] direction = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

	public int cutOffTree(List<List<Integer>> forest) {
		if (forest == null || forest.size() == 0)
			return 0;
		int M = forest.size(), N = forest.get(0).size();
		
		PriorityQueue<coordinate> trees = new PriorityQueue<>((a, b) -> a.height - b.height);

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				int h = forest.get(i).get(j);
				if (h > 1) 	trees.add(new coordinate( i, j, h ));
			}
		}

		coordinate start = new coordinate(0, 0, 0);	// 第三个坐标已经不重要
		int steps = 0;
		while (!trees.isEmpty()) {
			coordinate cdnt = trees.poll();
			int step = minStepToNextTree(forest, start, cdnt, M, N);

			if (step == -1) 	return -1;
			steps += step;

			start.row = cdnt.row;
			start.col = cdnt.col;
		}

		return steps;
	}
	
	/** 从 start 位置走到 tree 位置的最小步数
	 * 用 queue 做 BFS 搜索 */

	private int minStepToNextTree(List<List<Integer>> forest, coordinate start, coordinate tree, int M, int N) {
		boolean[][] visited = new boolean[M][N];			// 已经走过，标记以免重复走
		int step = 0;
		Queue<coordinate> queue = new LinkedList<>();	// BFS 搜索
		queue.add(start);
		visited[start.row][start.col] = true;

		while (!queue.isEmpty()) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {		// 处理当前节点一步之内的所有节点
				coordinate curr = queue.poll();
				if (curr.row == tree.row && curr.col == tree.col)		// 到达了终点
					return step;

				for (int[] d : direction) {		// 周围的 4 个方向
					int nr = curr.row + d[0];
					int nc = curr.col + d[1];
					if (nr < 0 || nr >= M || nc < 0 || nc >= N || forest.get(nr).get(nc) == 0 || visited[nr][nc])		// reach an obstacle
						continue;
					queue.add(new coordinate(nr, nc, 0));	// 添加邻居
					visited[nr][nc] = true;
				}
			}
			step++;
		}
		return -1;
	}
	
	class coordinate {
		int row, col, height;
		public coordinate(int r, int c, int h) {
			this.row = r;
			this.col = c;
			this.height = h;
		}
	}
}
