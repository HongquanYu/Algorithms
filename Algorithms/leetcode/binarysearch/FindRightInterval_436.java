package binarysearch;

import java.util.Arrays;
import java.util.HashMap;

/** @author: Hongquan Yu
 *  @date: Oct 16, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class FindRightInterval_436 {
	
	public int[] findRightInterval(Interval[] intervals) {
		Interval[] endIntervals = Arrays.copyOf(intervals, intervals.length);
		HashMap<Interval, Integer> hash = new HashMap<>();
		for (int i = 0; i < intervals.length; i++) {
			hash.put(intervals[i], i);
		}
		Arrays.sort(intervals, (a, b) -> a.start - b.start);
		Arrays.sort(endIntervals, (a, b) -> a.end - b.end);
		int j = 0;
		int[] res = new int[intervals.length];
		for (int i = 0; i < endIntervals.length; i++) {
			while (j < intervals.length && intervals[j].start < endIntervals[i].end) {
				j++;
			}
			res[hash.get(endIntervals[i])] = j == intervals.length ? -1 : hash.get(intervals[j]);
		}
		return res;
	}
	
	public class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}
}
