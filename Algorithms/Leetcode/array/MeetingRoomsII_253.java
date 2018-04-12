package array;

import java.util.Arrays;
import java.util.PriorityQueue;

/** @author: Hongquan Yu
 *  @date: Mar 27, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class MeetingRoomsII_253 {
	
	/** Greedy Solution! 
	 * 我们用一个数据结构来存储当前不重合的所有会议的结束时间，那么最后一个元素就是最晚结束时间。
	 * 遍历数组，对于每一个interval，我们检查它是不是开始时间早于最早结束时间，如果是的话就需要
	 * 另外开辟一个房间，如果不是，那么我们可以用当前的房间。
	 * 这里我们选择优先队列，最后优先队列的大小就是房间的数量！ */
	
	public int minMeetingRooms(Interval[] intervals) {
		if (intervals == null || intervals.length == 0)
			return 0;
		Arrays.sort(intervals, (a, b) -> a.start - b.start);
		
		PriorityQueue<Integer> endTimes = new PriorityQueue<>();	// 用堆来管理房间的结束时间
		endTimes.offer(intervals[0].end);
		
		for (Interval i : intervals) {
			// 如果当前时间段的开始时间大于最早结束的时间，则可以更新这个最早的结束时间为当前时间段的结束时间，如果小于的话，就加入一个新的结束时间，表示新的房间
			if (i.start >= endTimes.peek())
				endTimes.poll();
			
			endTimes.offer(i.end);
		}
		
		return endTimes.size();		// 有多少结束时间就有多少房间
	}
}
