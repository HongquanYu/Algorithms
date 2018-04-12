package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Apr 9, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class NumberofIslandsII_305 {
	
	/** 这道题主要是考察 Union-Find 并查集，找的是动态连通区域
	 *  */
	
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		int[] id = new int[m * n]; 	// 表示各个index对应的root

		List<Integer> res = new ArrayList<>();
		Arrays.fill(id, -1); 	// 初始化root为-1，用来标记water, 非-1表示land
		int count = 0; 	// 记录island的数量

		int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		for (int i = 0; i < positions.length; i++) {
			count++;
			int index = positions[i][0] * n + positions[i][1];	// 若是新岛，那么就是根
			id[index] = index; // root初始化

			for (int j = 0; j < dirs.length; j++) {	// 处理 4 个方向
				int x = positions[i][0] + dirs[j][0];
				int y = positions[i][1] + dirs[j][1];
				if (x >= 0 && x < m && y >= 0 && y < n && id[x * n + y] != -1) {	// 此方向上是一个小岛！
					int root = root(id, x * n + y);

					// 发现root不等的情况下，才union, 同时减小count
					if (root != index) {
						id[root] = index;
						count--;
					}
				}
			}
			res.add(count);
		}
		return res;
	}

	public int root(int[] id, int i) {
		while (i != id[i]) {
			id[i] = id[id[i]]; // 优化，为了减小树的高度
			i = id[i];
		}
		return i;
	}
}
