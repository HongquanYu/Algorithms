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
	
	public boolean canFinish1(int numCourses, int[][] prerequisites) {
		ArrayList[] graph = new ArrayList[numCourses];
		for (int i = 0; i < numCourses; i++)
			graph[i] = new ArrayList();

		boolean[] visited = new boolean[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			graph[prerequisites[i][1]].add(prerequisites[i][0]);
		}

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

	
	/* BFS */
	
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
		Queue<Integer> queue = new LinkedList();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0)
				queue.offer(i);
		}
		while (!queue.isEmpty()) {
			int course = queue.poll();
			count++;
			for (int i = 0; i < numCourses; i++) {
				if (matrix[course][i] != 0) {
					if (--indegree[i] == 0)
						queue.offer(i);
				}
			}
		}
		return count == numCourses;
	}
}
