package dfs;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 18, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class NestedListWeightSumII_364 {
	public int depthSumInverse(List<NestedInteger> nestedList) {
		int unweighted = 0, weighted = 0;
		while (!nestedList.isEmpty()) {
			List<NestedInteger> nextLevel = new ArrayList<>();
			for (NestedInteger ni : nestedList) {
				if (ni.isInteger())
					unweighted += ni.getInteger();
				else
					nextLevel.addAll(ni.getList());
			}
			weighted += unweighted;
			nestedList = nextLevel;
		}
		return weighted;
	}
}
