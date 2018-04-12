package greedy;

import java.util.Arrays;
import array.Interval;

public class NonoverlappoingIntervals_435 {
	
	/** Brute force solution */

	public int eraseOverlapIntervals1(Interval[] intervals) {
		Arrays.sort(intervals, (a, b) -> a.start - b.start);
		return erase(-1, 0, intervals);
	}

	public int erase(int prev, int curr, Interval[] intervals) {
		if (curr == intervals.length)
			return 0;
		
		int taken = Integer.MAX_VALUE, nottaken;
		if (prev == -1 || intervals[prev].end <= intervals[curr].start)
			taken = erase(curr, curr + 1, intervals);
		
		nottaken = erase(prev, curr + 1, intervals) + 1;
		return Math.min(taken, nottaken);
	}
	
    /** DP solution based on starting points */

	public int eraseOverlapIntervals2(Interval[] intervals) {
		int N = intervals.length;
		if (N == 0) 	return 0;
		
		Arrays.sort(intervals, (a, b) -> a.start - b.start);
		int dp[] = new int[N];
		dp[0] = 1;
		int ans = 1;
		for (int i = 1; i < N; i++) {
			int max = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (intervals[j].end <= intervals[i].start)
					max = Math.max(dp[j], max);
			}
			dp[i] = max + 1;
			ans = Math.max(ans, dp[i]);
		}
		
		return N - ans;
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
    
    /** Use greedy based on start point
     * 用一个prev指针遍历数组，对每一个interval如果和prev重合就需要移除一个。若是prev覆盖了
     * 当前 interval，那么就直接删除 */
    
	public int eraseOverlapIntervals4(Interval[] intervals) {
		if (intervals.length == 0)
			return 0;

		Arrays.sort(intervals, (a, b) -> a.start - b.start);
		int prev = 0, count = 0;
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[prev].end > intervals[i].start) {
				if (intervals[prev].end > intervals[i].end)	// 若是
					prev = i;
				count++;
			} else {
				prev = i;
			}
		}
		return count;
	}
    
    /* Use greedy based on end point */
    
	public int eraseOverlapIntervals(Interval[] intervals) {
		if (intervals.length == 0)
			return 0;
		
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
	
	public static void main(String[] args) {
		Interval[] tmp = new Interval[5];
		tmp[0] = new Interval(1, 3);
		tmp[1] = new Interval(5, 8);
		tmp[2] = new Interval(4, 7);
		tmp[3] = new Interval(9, 10);
		tmp[4] = new Interval(11, 15);
		
		NonoverlappoingIntervals_435 n = new NonoverlappoingIntervals_435();
		
		System.out.println(n.eraseOverlapIntervals4(tmp));
	}
}
