package greedy;

import java.util.Arrays;

public class NonoverlappoingIntervals_435 {
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
        	return 0;
        
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        int count = 0;
        int boundary = intervals[0].end;
        
        for (int i = 1; i < intervals.length; ++i) {
        		if (intervals[i].start > boundary)
        			continue;
        		boundary = Math.min(boundary, intervals[i].end);
        		count++;
        }
        
    		return count;
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
