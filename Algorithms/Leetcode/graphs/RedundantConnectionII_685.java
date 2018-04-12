package graphs;

/** @author: Hongquan Yu
 *  @date: Mar 24, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

public class RedundantConnectionII_685 {
	
	/** 导致树不成立的原因：
	 * 1，一个节点有两个parents
	 * 2，cycle 存在
	 * 
	 * 如果我们能移除1条边就能得到一棵树，那么某个节点最多有两个 parents：
	 * 1，检查是否含有两个 parents 的节点
	 * 		- 把他们存为 candidate A 和 B，并设置第二条边为 invalid
	 * 2，进行 normal union find 查找当前
	 * 		- 当前树是valid，返回B
	 * 		- candidate 不存在，并且有一个cycle，返回当前边
	 * 		- 移除 candidate A（不是B） */
	
	public int[] findRedundantDirectedConnection(int[][] edges) {
		int[] can1 = { -1, -1 };
		int[] can2 = { -1, -1 };
		int N = edges.length;
		int[] parent = new int[N + 1];		// 存储当前节点（索引）的父节点
		
		// 第一步，进行遍历检查
		for (int i = 0; i < N; i++) {
			if (parent[edges[i][1]] == 0) {		// 存储第一个父节点
				parent[edges[i][1]] = edges[i][0];
			} else {					// 有两个父节点
				can2 = new int[]{ edges[i][0], edges[i][1] };
				can1 = new int[]{ parent[edges[i][1]], edges[i][1] };
				edges[i][1] = 0;		// 消去第二条边
			}
		}
		// 
		for (int i = 0; i < N; i++)
			parent[i] = i;
		// 
		for (int i = 0; i < N; i++) {
			if (edges[i][1] != 0) {
				int child = edges[i][1], father = edges[i][0];
				
				if (findRoot(parent, father) == child)
					return (can1[0] == -1) ? edges[i] : can1;
				
				parent[child] = father;
			}
		}
		return can2;
	}
	
	/** 找到当前节点 i 的 root */
	
	private int findRoot(int[] parent, int i) {
		while (i != parent[i]) {
			parent[i] = parent[parent[i]];
			i = parent[i];
		}
		return i;
	}
}
