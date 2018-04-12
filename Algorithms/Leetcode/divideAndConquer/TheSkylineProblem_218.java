package divideAndConquer;

import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class TheSkylineProblem_218 {
	public List<int[]> getSkyline(int[][] buildings) {
		return merge(buildings, 0, buildings.length - 1);
	}

	private LinkedList<int[]> merge(int[][] buildings, int lo, int hi) {
		LinkedList<int[]> res = new LinkedList<>(); // result collection
		if (lo > hi) { // check if indices cross boundary
			return res;
		} else if (lo == hi) { // recursion corner case
			res.add(new int[] {buildings[lo][0], buildings[lo][2]}); // adding (left, height)
			res.add(new int[] {buildings[lo][1], 0}); // adding (right, 0)
			return res;
		}

		int mid = lo + (hi - lo) / 2; // recursive divide and conquer
		LinkedList<int[]> left = merge(buildings, lo, mid); // left part
		LinkedList<int[]> right = merge(buildings, mid + 1, hi); // right part
		int leftH = 0, rightH = 0;

		while (!left.isEmpty() || !right.isEmpty()) { // When there is sub-problem
			long x1 = left.isEmpty() ? Long.MAX_VALUE : left.peekFirst()[0]; // get x coordinate
			long x2 = right.isEmpty() ? Long.MAX_VALUE : right.peekFirst()[0]; // get x coordinate
			int x = 0; // temporary store x coordinate of answer
			if (x1 < x2) { // get smaller x
				int[] temp = left.pollFirst(); // (left, height)
				x = temp[0];
				leftH = temp[1];
			} else if (x1 > x2) { // get smaller x
				int[] temp = right.pollFirst();
				x = temp[0];
				rightH = temp[1];
			} else { // items from left and right are equal
				x = left.peekFirst()[0]; // get X coordinate
				leftH = left.pollFirst()[1]; // get height or 0
				rightH = right.pollFirst()[1]; // get height or 0
			}
			int h = Math.max(leftH, rightH); // get higher one, lower one will be overlapped
			if (res.isEmpty() || h != res.peekLast()[1]) { // adding into result
				res.add(new int[] {x, h});
			}
		}
		return res;
	}

}
