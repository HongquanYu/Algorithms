package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Feb 13, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ContainVirus_749 {
	private Set<Integer> seen;
	private List<Set<Integer>> regions;
	private List<Set<Integer>> frontiers;
	private List<Integer> perimeters;
	private int[][] grid;
	private int R, C;
	private int[] dr = new int[]{-1, 1, 0, 0};
	private int[] dc = new int[]{0, 0, -1, 1};

	public int containVirus(int[][] grid) {
		this.grid = grid;
		R = grid.length;
		C = grid[0].length;

		int ans = 0;
		while (true) {
			seen = new HashSet<>();
			regions = new ArrayList<>();
			frontiers = new ArrayList<>();
			perimeters = new ArrayList<>();

			for (int r = 0; r < R; ++r) {
				for (int c = 0; c < C; ++c) {
					if (grid[r][c] == 1 && !seen.contains(r * C + c)) {
						regions.add(new HashSet<>());
						frontiers.add(new HashSet<>());
						perimeters.add(0);
						dfs(r, c);
					}
				}
			}

			if (regions.isEmpty())
				break;
			int triageIndex = 0;
			for (int i = 0; i < frontiers.size(); ++i) {
				if (frontiers.get(triageIndex).size() < frontiers.get(i).size())
					triageIndex = i;
			}
			ans += perimeters.get(triageIndex);

			for (int i = 0; i < regions.size(); ++i) {
				if (i == triageIndex) {
					for (int code : regions.get(i))
						grid[code / C][code % C] = -1;
				} else {
					for (int code : regions.get(i)) {
						int r = code / C, c = code % C;
						for (int k = 0; k < 4; ++k) {
							int nr = r + dr[k], nc = c + dc[k];
							if (nr >= 0 && nr < R && nc >= 0 && nc < C && grid[nr][nc] == 0)
								grid[nr][nc] = 1;
						}
					}
				}
			}
		}
		return ans;
	}

	private void dfs(int r, int c) {
		if (!seen.contains(r * C + c)) {
			seen.add(r * C + c);
			int N = regions.size();
			regions.get(N - 1).add(r * C + c);
			for (int k = 0; k < 4; ++k) {
				int nr = r + dr[k], nc = c + dc[k];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
					if (grid[nr][nc] == 1) {
						dfs(nr, nc);
					} else if (grid[nr][nc] == 0) {
						frontiers.get(N - 1).add(nr * C + nc);
						perimeters.set(N - 1, perimeters.get(N - 1) + 1);
					}
				}
			}
		}
	}
}
