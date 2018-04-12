package dfs;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FriendCircles_547 {

	public int findCircleNum(int[][] M) {
		int[] visited = new int[M.length];	// 第 ith 个人有没有被访问过，避免重复查找
		int count = 0;
		
		for (int i = 0; i < M.length; i++) {		// 遍历行
			if (visited[i] == 0) {		// 第 i 个人没有被访问过，把和第 i 个人有关系的人都给标记上，这样就不用重复访问
				DFS(M, visited, i);
				count++;
			}
		}
		
		return count;
	}
	
	private void DFS(int[][] M, int[] visited, int i) {
		for (int j = 0; j < M.length; j++) {		// 遍历列
			if (M[i][j] == 1 && visited[j] == 0) {	// 当前第 j 个人没有被访问过
				visited[j] = 1;
				DFS(M, visited, j);
			}
		}
	}
}
