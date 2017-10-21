package dfs;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class CourseScheduleII_210 {
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

	private boolean hasOrder(int from, List<List<Integer>> adjs, BitSet visited, BitSet onStack,
			Deque<Integer> order) {
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
}
