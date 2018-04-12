package dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class CourseScheduleII_210 {
	
	/** BFS */
	
	public int[] findOrder(int N, int[][] pre) {
		if (N == 0)
			return null;
		
		// Convert graph presentation from edges to indegree of adjacent list.
		int indegree[] = new int[N];
		int order[] = new int[N];
		int index = 0;
		
		for (int i = 0; i < pre.length; i++) // Indegree - how many pre are needed.
			indegree[pre[i][0]]++;

		Queue<Integer> queue = new LinkedList<Integer>();
		
		for (int i = 0; i < N; i++)
			if (indegree[i] == 0) {
				// Add the course to the order because it has no pre.
				order[index++] = i;
				queue.offer(i);
			}

		// How many courses don't need pre.
		while (!queue.isEmpty()) {
			int prerequisite = queue.poll(); // Already finished this prerequisite course.
			for (int i = 0; i < pre.length; i++) {
				if (pre[i][1] == prerequisite) {
					indegree[pre[i][0]]--;
					if (indegree[pre[i][0]] == 0) {
						// If indegree is zero, then add the course to the order.
						order[index++] = pre[i][0];
						queue.offer(pre[i][0]);
					}
				}
			}
		}

		return (index == N) ? order : new int[0];
	}
	
	/** BFS and DFS */
	
	public int[] findOrder2(int N, int[][] pre) {
		int[] incLinkCounts = new int[N];
		List<List<Integer>> adjs = new ArrayList<>(N);
		initialiseGraph(incLinkCounts, adjs, pre);
		// return solveByBFS(incLinkCounts, adjs);
		return solveByDFS(adjs);
	}

	private void initialiseGraph(int[] incLinkCounts, List<List<Integer>> adjs, int[][] pre) {
		int n = incLinkCounts.length;
		while (n-- > 0)
			adjs.add(new ArrayList<>());
		for (int[] edge : pre) {
			incLinkCounts[edge[0]]++;
			adjs.get(edge[1]).add(edge[0]);
		}
	}

	private int[] solveByDFS(List<List<Integer>> adjs) {
		BitSet hasCycle = new BitSet(1);
		BitSet visited = new BitSet(adjs.size());
		BitSet onStack = new BitSet(adjs.size());
		Deque<Integer> order = new ArrayDeque<>();
		for (int i = adjs.size() - 1; i >= 0; i--) {
			if (visited.get(i) == false && hasOrder(i, adjs, visited, onStack, order) == false)
				return new int[0];
		}

		int[] orderArray = new int[adjs.size()];

		for (int i = 0; !order.isEmpty(); i++)
			orderArray[i] = order.pop();

		return orderArray;
	}

	private boolean hasOrder(int from, List<List<Integer>> adjs, BitSet visited, BitSet onStack, Deque<Integer> order) {
		visited.set(from);
		onStack.set(from);
		for (int to : adjs.get(from)) {
			if (visited.get(to) == false) {
				if (hasOrder(to, adjs, visited, onStack, order) == false)
					return false;
			} else if (onStack.get(to) == true) {
				return false;
			}
		}
		onStack.clear(from);
		order.push(from);
		return true;
	}
	
	/** Topological Sort based on DFS with a bit OO */
	
	private int ptr = 0;

	public int[] findOrder3(int N, int[][] pre) {
		int[] result = new int[N];
		Course[] courses = new Course[N];
		
		for (int i = 0; i < N; i++)
			courses[i] = new Course(i);
		
		// 将每门课的先修课都加入到其 pre 链表里
		for (int i = 0; i < pre.length; i++)
			courses[pre[i][0]].add(courses[pre[i][1]]);
		
		for (int i = 0; i < N; i++)
			if (isCyclic(courses[i], result)) 
				return new int[0];
		
		return result;
	}
	
	/**  */
	
	private boolean isCyclic(Course cur, int[] result) {
		if (cur.tested)
			return false;
		if (cur.visited)
			return true; //

		cur.visited = true;
		for (Course c : cur.pre)
			if (isCyclic(c, result))
				return true;

		cur.tested = true;
		result[ptr++] = cur.number; // 当前课程加入到结果里

		return false;
	}

	class Course {
		boolean visited = false;		// 当前课程是否被访问过！
		boolean tested = false;		// 当前课程已经被测试过了
		int number;			// 
		
		List<Course> pre = new ArrayList<Course>();
		
		public Course(int i) {
			this.number = i;
		}

		public void add(Course c) {
			pre.add(c);
		}
	}
}
