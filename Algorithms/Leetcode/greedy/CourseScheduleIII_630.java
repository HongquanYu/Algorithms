package greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/** @author: Hongquan Yu
 *  @date: Apr 1, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class CourseScheduleIII_630 {
	
	/** 用一个 优先队列来存已经 taken 的 course 的长度。若是当前的开始时间 + 持续时间 小于其结束时间。
	 * 若是不能选择一个当前的时间，我们就用以前上过的持续最久的时间来替换当前的课程。 */
	
	public int scheduleCourse(int[][] courses) {
		Arrays.sort(courses, (a, b) -> a[1] - b[1]);
		PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
		int time = 0;
		for (int[] c : courses) {
			if (time + c[0] <= c[1]) {
				queue.offer(c[0]);
				time += c[0];
			} else if (!queue.isEmpty() && queue.peek() > c[0]) {
				time += c[0] - queue.poll();
				queue.offer(c[0]);
			}
		}
		return queue.size();
	}
}
