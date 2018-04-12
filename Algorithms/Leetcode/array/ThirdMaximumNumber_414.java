package array;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Apr 11, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu */

public class ThirdMaximumNumber_414 {
	
	/** O(n) 的解法！ */
	
	public int thirdMax(int[] nums) {
		Integer first = null;
		Integer second = null;
		Integer third = null;
		for (Integer n : nums) {
			if (n.equals(first) || n.equals(second) || n.equals(third))
				continue;
			if (first == null || n > first) {
				third = second;
				second = first;
				first = n;
			} else if (second == null || n > second) {
				third = second;
				second = n;
			} else if (third == null || n > third) {
				third = n;
			}
		}
		return third == null ? first : third;
	}

	/** 优先队列解法 */
	
	public int thirdMax2(int[] nums) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		Set<Integer> set = new HashSet<>();
		for (int i : nums) {
			if (!set.contains(i)) {
				pq.offer(i);
				set.add(i);
				if (pq.size() > 3)
					set.remove(pq.poll());
			}
		}
		if (pq.size() < 3) {
			while (pq.size() > 1)
				pq.poll();
		}
		return pq.peek();
	}
}
