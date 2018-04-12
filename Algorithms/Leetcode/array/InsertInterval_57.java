package array;

import java.util.LinkedList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Mar 27, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class InsertInterval_57 {
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> res = new LinkedList<>();
		int i = 0;
		while (i < intervals.size() && intervals.get(i).end < newInterval.start)
			res.add(intervals.get(i++));
		
		while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
			newInterval = new Interval(
					Math.min(newInterval.start, intervals.get(i).start),
					Math.max(newInterval.end, intervals.get(i).end));
			i++;
		}
		res.add(newInterval);
		while (i < intervals.size())
			res.add(intervals.get(i++));
		
		return res;
	}
	
	/** 下面这个解法比较绕！ */
	
	public List<Interval> insert1(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new LinkedList<Interval>();
		for (Interval i : intervals) {
			if (newInterval == null || i.end < newInterval.start)
				result.add(i);
			else if (i.start > newInterval.end) {
				result.add(newInterval);
				result.add(i);
				newInterval = null;
			} else {
				newInterval.start = Math.min(newInterval.start, i.start);
				newInterval.end = Math.max(newInterval.end, i.end);
			}
		}
		if (newInterval != null)
			result.add(newInterval);
		return result;
	}
}
