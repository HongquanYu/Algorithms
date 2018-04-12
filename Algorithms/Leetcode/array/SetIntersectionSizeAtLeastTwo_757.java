package array;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Mar 28, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class SetIntersectionSizeAtLeastTwo_757 {
	
	/**  */
	
	public int intersectionSizeTwo(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
		int[] todo = new int[intervals.length];
		Arrays.fill(todo, 2);
		int ans = 0, t = intervals.length;
		
		while (--t >= 0) {
			int s = intervals[t][0];
			int e = intervals[t][1];
			int m = todo[t];
			for (int p = s; p < s + m; ++p) {
				for (int i = 0; i <= t; ++i)
					if (todo[i] > 0 && p <= intervals[i][1])
						todo[i]--;
				ans++;
			}
		}
		return ans;
	}
}
