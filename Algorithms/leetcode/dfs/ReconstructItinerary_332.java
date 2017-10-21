package dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ReconstructItinerary_332 {
	public List<String> findItinerary(String[][] tickets) {
		for (String[] ticket : tickets)
			targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
		visit("JFK");
		return route;
	}

	Map<String, PriorityQueue<String>> targets = new HashMap<>();
	List<String> route = new LinkedList();

	void visit(String airport) {
		while (targets.containsKey(airport) && !targets.get(airport).isEmpty())
			visit(targets.get(airport).poll());
		route.add(0, airport);
	}
	
	public List<String> findItinerary1(String[][] tickets) {
		Map<String, PriorityQueue<String>> targets = new HashMap<>();
		for (String[] ticket : tickets)
			targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
		List<String> route = new LinkedList();
		Stack<String> stack = new Stack<>();
		stack.push("JFK");
		while (!stack.empty()) {
			while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty())
				stack.push(targets.get(stack.peek()).poll());
			route.add(0, stack.pop());
		}
		return route;
	}
}
