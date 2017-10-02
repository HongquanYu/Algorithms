package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class NonoverlappoingIntervals_435 {
	
	/* Brute force solution */
	
	class myComparator implements Comparator<Interval> {
		public int compare(Interval a, Interval b) {
			return a.start - b.start;
		}
	}

	public int eraseOverlapIntervals1(Interval[] intervals) {
		Arrays.sort(intervals, new myComparator());
		//Arrays.sort(intervals, (a, b) -> a.start - b.start); 		// In java 8
		return erase_Overlap_Intervals(-1, 0, intervals);
	}

	public int erase_Overlap_Intervals(int prev, int curr, Interval[] intervals) {
		if (curr == intervals.length) {
			return 0;
		}
		int taken = Integer.MAX_VALUE, nottaken;
		if (prev == -1 || intervals[prev].end <= intervals[curr].start) {
			taken = erase_Overlap_Intervals(curr, curr + 1, intervals);
		}
		nottaken = erase_Overlap_Intervals(prev, curr + 1, intervals) + 1;
		return Math.min(taken, nottaken);
	}
	
    /* DP solution based on starting points */

	public int eraseOverlapIntervals2(Interval[] intervals) {
		if (intervals.length == 0) {
			return 0;
		}
		Arrays.sort(intervals, (a, b) -> a.start - b.start);
		int dp[] = new int[intervals.length];
		dp[0] = 1;
		int ans = 1;
		for (int i = 1; i < dp.length; i++) {
			int max = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (intervals[j].end <= intervals[i].start) {
					max = Math.max(dp[j], max);
				}
			}
			dp[i] = max + 1;
			ans = Math.max(ans, dp[i]);

		}
		
		return intervals.length - ans;
	}
    
    /* DP based on ending points */
    
    
    public int eraseOverlapIntervals3(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a.end - b.end);
        int dp[] = new int[intervals.length];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < dp.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (intervals[j].end <= intervals[i].start) {
                    max = Math.max(dp[j], max);
                    break;
                }
            }
            dp[i] = Math.max(max + 1, dp[i - 1]);
            ans = Math.max(ans, dp[i]);
        }
        return intervals.length - ans;
    }
    
    /* Use greedy based on start point */
    
    
    public int eraseOverlapIntervals4(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        int end = intervals[0].end, prev = 0, count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[prev].end > intervals[i].start) {
                if (intervals[prev].end > intervals[i].end) {
                    prev = i;
                }
                count++;
            } else {
                prev = i;
            }
        }
        return count;
    }
    
    /* Use greedy based on end point */
    
	public int eraseOverlapIntervals(Interval[] intervals) {
		if (intervals.length == 0) {
			return 0;
		}
		Arrays.sort(intervals, (a, b) -> a.end - b.end);
		int end = intervals[0].end;
		int count = 1;
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i].start >= end) {
				end = intervals[i].end;
				count++;
			}
		}
		return intervals.length - count;
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
