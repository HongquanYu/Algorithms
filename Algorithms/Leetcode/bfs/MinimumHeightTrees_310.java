package bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MinimumHeightTrees_310 {
	
	/** 这里是剥洋葱的做法，即将整个图看作一个树，我们每一次将最外层的叶子删掉，如此往复，直到
	 * 只剩下1个或者2个是最后的根节点。具体的 implementation 是：
	 * 1，用一个二位的链表来实现存储每个节点和他的邻接点。
	 * 2，用一个链表来存储叶子结点
	 * 3，循环直到只剩1个或者2个节点，对每个叶子结点进行删除操作，然后如果现有节点变成了新节点，
	 * 	  加入叶子结点集合，以供下一次的循环。 */
	
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		if (n == 1)
			return Collections.singletonList(0);

		List<Set<Integer>> adj = new ArrayList<>(n);
		for (int i = 0; i < n; ++i)
			adj.add(new HashSet<>());
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}

		List<Integer> leaves = new ArrayList<>();	// 存的是节点名字
		for (int i = 0; i < n; ++i)
			if (adj.get(i).size() == 1)
				leaves.add(i);

		while (n > 2) {
			n -= leaves.size();
			List<Integer> newLeaves = new ArrayList<>();
			for (int i : leaves) {
				int j = adj.get(i).iterator().next();
				adj.get(j).remove(i);
				if (adj.get(j).size() == 1)
					newLeaves.add(j);
			}
			leaves = newLeaves;
		}
		return leaves;
	}
    
    public static void main(String[] args) {
		int[][] t1 = new int[][] {{1, 0}, {1, 2}, {1, 3}};
		int[][] t2 = new int[][] {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
		int[][] t3 = new int[][] {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
		
		MinimumHeightTrees_310 m = new MinimumHeightTrees_310();
		System.out.println(m.findMinHeightTrees(6, t2).toString());
		System.out.println(m.findMinHeightTrees(4, t1).toString());
		System.out.println(m.findMinHeightTrees(6, t3).toString());
	}
}
