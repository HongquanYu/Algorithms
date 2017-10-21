package dfs;

import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class NestedListWeightSum_339 {
	public int depthSum(List<NestedInteger> nestedList) {
		return depthSum(nestedList, 1);
	}

	public int depthSum(List<NestedInteger> list, int depth) {
		int sum = 0;
		for (NestedInteger n : list) {
			if (n.isInteger()) {
				sum += n.getInteger() * depth;
			} else {
				sum += depthSum(n.getList(), depth + 1);
			}
		}
		return sum;
	}
}
