package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 20, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Combinations_77 {
	public static List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> combs = new ArrayList<List<Integer>>();
		combine(combs, new ArrayList<Integer>(), 1, n, k);
		return combs;
	}

	public static void combine(List<List<Integer>> combs, List<Integer> comb, int start, int n,
			int k) {
		if (k == 0) {
			combs.add(new ArrayList<Integer>(comb));
			return;
		}
		for (int i = start; i <= n; i++) {
			comb.add(i);
			combine(combs, comb, i + 1, n, k - 1);
			comb.remove(comb.size() - 1);
		}
	}
	
	/*
	 * Basically, this solution follows the idea of the mathematical formula
	 * C(n,k)=C(n-1,k-1)+C(n-1,k).
	 * 
	 * Here C(n,k) is divided into two situations. Situation one, number n is selected, so we only
	 * need to select k-1 from n-1 next. Situation two, number n is not selected, and the rest job
	 * is selecting k from n-1.
	 */

	public List<List<Integer>> combine2(int n, int k) {
		if (k == n || k == 0) {
			List<Integer> row = new LinkedList<>();
			for (int i = 1; i <= k; ++i) {
				row.add(i);
			}
			return new LinkedList<>(Arrays.asList(row));
		}
		List<List<Integer>> result = this.combine(n - 1, k - 1);
		result.forEach(e -> e.add(n));
		result.addAll(this.combine(n - 1, k));
		return result;
	}
}
