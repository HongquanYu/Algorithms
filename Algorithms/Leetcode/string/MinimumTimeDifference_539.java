package string;

import java.util.ArrayList;
import java.util.List;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MinimumTimeDifference_539 {
	
	/* There is only 24 * 60 = 1440 possible time points. Just create a boolean array, each element
	 * stands for if we see that time point or not. Then things become simple... */

	public int findMinDifference(List<String> timePoints) {
		boolean[] mark = new boolean[24 * 60];
		for (String time : timePoints) {
			String[] t = time.split(":");
			int h = Integer.parseInt(t[0]);
			int m = Integer.parseInt(t[1]);
			if (mark[h * 60 + m])
				return 0;
			mark[h * 60 + m] = true;
		}

		int prev = 0, min = Integer.MAX_VALUE;
		int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
		for (int i = 0; i < 24 * 60; i++) {
			if (mark[i]) {
				if (first != Integer.MAX_VALUE)
					min = Math.min(min, i - prev);
				
				first = Math.min(first, i);
				last = Math.max(last, i);
				prev = i;
			}
		}

		min = Math.min(min, (24 * 60 - last + first));	// 判断逆向大小

		return min;
	}
	
	/** My Implement! 最后两个 case TLE */
	
    public int findMinDifference1(List<String> timePoints) {
        int min = Integer.MAX_VALUE;
        String[] time = timePoints.toArray(new String[0]);
        
        for (int i = 0; i < time.length; ++i)
            for (int j = 1; j < time.length; ++j)
            		if (i != j)
            			min = Math.min(min, getDiff(time[i], time[j]));
        return min;
    }
    
    private int getDiff(String a, String b) {
        int min = Integer.MAX_VALUE;
        int hourA = Integer.parseInt(a.substring(0, 2));
        int hourB = Integer.parseInt(b.substring(0, 2));
        int minuteA = Integer.parseInt(a.substring(3));
        int minuteB = Integer.parseInt(b.substring(3));
        
        if (hourA > hourB || (hourA == hourB && minuteA > minuteB)) {
            min = Math.min(min, (hourA - hourB) * 60 + (minuteA - minuteB));
            min = Math.min(min, (24 - hourA + hourB) * 60 + (minuteB - minuteA) );
            System.out.println("If: " + min);
        } else {
            min = Math.min(min, (hourB - hourA) * 60 + (minuteB - minuteA));
            min = Math.min(min, (24 - hourB + hourA) * 60 + (minuteA - minuteB) );
            System.out.println("Else: " + min);
        }
        return min;
    }
    
    public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("23:59");
		list.add("00:00");
		
		MinimumTimeDifference_539 m = new MinimumTimeDifference_539();
		System.out.println(m.findMinDifference1(list));
	}
}
