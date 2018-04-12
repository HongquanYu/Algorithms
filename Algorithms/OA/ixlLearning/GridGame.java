package ixlLearning;

/** @author: Hongquan Yu
 *  @date: Apr 10, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */
 
public class GridGame {
	
	/** 理解这个题理解了老半天，题目很简单！
	 * LeetCode 598 */
	
	public long countX(String[] steps) {
		long count = 0;
		if (steps.length == 0)
			return count;

		int minRow = Integer.MAX_VALUE;
		int minCol = Integer.MAX_VALUE;

		for (String step : steps) {
			String[] tmp = step.split(" ");

			minRow = Math.min(minRow, Integer.valueOf(tmp[0]));
			minCol = Math.min(minCol, Integer.valueOf(tmp[1]));
		}
		return minCol * (long) minRow;
	}
}
