package array;

import java.util.TreeMap;

/** @author: Hongquan Yu
 *  @date: Mar 28, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class MyCalendarI_729 {
	private TreeMap<Integer, Integer> calendar;

	public MyCalendarI_729() {
		calendar = new TreeMap<>();
	}

	public boolean book(int start, int end) {
		Integer floorKey = calendar.floorKey(start);
		if (floorKey != null && calendar.get(floorKey) > start)	// previous.end > start
			return false;
		Integer ceilingKey = calendar.ceilingKey(start);		// next.start < end
		if (ceilingKey != null && ceilingKey < end)
			return false;

		calendar.put(start, end);
		return true;
	}
}
