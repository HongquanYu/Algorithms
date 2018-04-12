package array;

import java.util.Arrays;

/** @author: Hongquan Yu
 *  @date: Mar 27, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class MeetingRooms_252 {
	public boolean canAttendMeetings(Interval[] intervals) {
		if (intervals == null || intervals.length == 0)
			return true;
		
		Arrays.sort(intervals, (a, b) -> a.start - b.start);
		int end = intervals[0].end;
		
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i].start < end)
				return false;
			end = Math.max(end, intervals[i].end);
		}
		
		return true;
	}
}
