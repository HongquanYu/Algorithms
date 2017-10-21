package backtracking;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 20, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FactorCombinations_254 {
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		helper(result, new ArrayList<Integer>(), n, 2);
		return result;
	}

	public void helper(List<List<Integer>> result, List<Integer> item, int n, int start) {
		if (n <= 1) {
			if (item.size() > 1) {
				result.add(new ArrayList<Integer>(item));
			}
			return;
		}

		for (int i = start; i <= n; ++i) {
			if (n % i == 0) {
				item.add(i);
				helper(result, item, n / i, i);
				item.remove(item.size() - 1);
			}
		}
	}
}
