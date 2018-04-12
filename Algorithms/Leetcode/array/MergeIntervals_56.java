package array;

import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Feb 9, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MergeIntervals_56 {
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() <= 1)
			return intervals;

		intervals.sort((i1, i2) -> i1.start - i2.start);	// 按第一个坐标排序

		List<Interval> result = new LinkedList<Interval>();
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;

		for (Interval interval : intervals) {
			if (interval.start <= end) 	// Overlapping intervals, move the end if needed
				end = Math.max(end, interval.end);
			else { 			// Disjoint intervals, add the previous one and reset bounds
				result.add(new Interval(start, end));
				start = interval.start;
				end = interval.end;
			}
		}

		result.add(new Interval(start, end));	// 最后一组不管有没有重合都没有加到result 里。Add the last interval
		return result;
	}
}
