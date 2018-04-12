package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class PerfectSquares_279 {
	public int numSquares(int n) {
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		q.offer(0);
		visited.add(0);
		int depth = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			depth++;
			while (size-- > 0) {
				int u = q.poll();
				for (int i = 1; i * i <= n; i++) {
					int v = u + i * i;
					if (v == n) {
						return depth;
					}
					if (v > n) {
						break;
					}
					if (!visited.contains(v)) {
						q.offer(v);
						visited.add(v);
					}
				}
			}
		}
		return depth;
	}
}
