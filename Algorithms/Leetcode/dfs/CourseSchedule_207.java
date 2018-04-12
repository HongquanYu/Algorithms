package dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class CourseSchedule_207 {
	
	/** DFS, 大概意思是：
	 * 用一个HashMap 似的将每个节点和他的后续节点存起来。然后DFS 搜索每一个节点
	 * 如果遇到环也就是重复访问已经访问过的节点就返回false，如果能顺利的全部访问，返回true */
	
	public boolean canFinish1(int numCourses, int[][] prerequisites) {
		ArrayList[] graph = new ArrayList[numCourses];
		for (int i = 0; i < numCourses; i++)
			graph[i] = new ArrayList();

		boolean[] visited = new boolean[numCourses];
		for (int i = 0; i < prerequisites.length; i++)
			graph[prerequisites[i][1]].add(prerequisites[i][0]);

		for (int i = 0; i < numCourses; i++) {
			if (!dfs(graph, visited, i))
				return false;
		}
		return true;
	}

	private boolean dfs(ArrayList[] graph, boolean[] visited, int course) {
		if (visited[course])
			return false;
		else
			visited[course] = true;;

		for (int i = 0; i < graph[course].size(); i++) {
			if (!dfs(graph, visited, (int) graph[course].get(i)))
				return false;
		}
		visited[course] = false;
		
		return true;
	}

	
	/** BFS Solution
	 *  */
	
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[][] matrix = new int[numCourses][numCourses]; // i -> j
		int[] indegree = new int[numCourses];

		for (int i = 0; i < prerequisites.length; i++) {
			int ready = prerequisites[i][0];
			int pre = prerequisites[i][1];
			if (matrix[pre][ready] == 0)
				indegree[ready]++; // duplicate case
			matrix[pre][ready] = 1;
		}

		int count = 0;
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < indegree.length; i++)
			if (indegree[i] == 0)
				queue.offer(i);
		
		while (!queue.isEmpty()) {
			int course = queue.poll();
			count++;
			for (int i = 0; i < numCourses; i++)
				if (matrix[course][i] != 0)
					if (--indegree[i] == 0)
						queue.offer(i);
		}
		
		return count == numCourses;
	}
}
