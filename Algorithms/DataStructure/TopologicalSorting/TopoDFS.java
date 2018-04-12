package TopologicalSorting;

import java.util.*;

/** @author: Hongquan Yu
 *  @date: Mar 31, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

/** A Java program to print topological sorting of a DAG, and it's based on
 * DFS algorithm and have some little modifications.
 * 
 * This class represents a directed graph using adjacency list representation */

class TopoDFS {
	private int V; // No. of vertices
	private LinkedList<Integer> adj[]; // Adjacency List

	// Constructor
	TopoDFS(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList<>();
	}

	private void addEdge(int v, int w) {
		adj[v].add(w);
	}

	private void sortUtil(int v, boolean visited[], Stack<Integer> stack) {
		visited[v] = true;
		Integer i;

		Iterator<Integer> it = adj[v].iterator();
		while (it.hasNext()) {
			i = it.next();
			if (!visited[i])
				sortUtil(i, visited, stack);
		}

		stack.push(new Integer(v));
	}

	public void sort() {
		Stack<Integer> stack = new Stack<>();

		boolean visited[] = new boolean[V];
		Arrays.fill(visited, false);
		
		for (int i = 0; i < V; i++)
			if (visited[i] == false)
				sortUtil(i, visited, stack);

		while (stack.empty() == false)
			System.out.print(stack.pop() + " ");
	}

	public static void main(String args[]) {
		TopoDFS g = new TopoDFS(6);
		g.addEdge(5, 2);
		g.addEdge(5, 0);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 1);

		System.out.println("Following is a Topological sort of the given graph");
		g.sort();
	}
}
