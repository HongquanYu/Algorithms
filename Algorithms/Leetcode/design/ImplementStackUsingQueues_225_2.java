package design;

import java.util.LinkedList;
import java.util.Queue;

/** @author: Hongquan Yu
 *  @date: Feb 12, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ImplementStackUsingQueues_225_2 {
	Queue<Integer> que;
	
	public ImplementStackUsingQueues_225_2() {
		que = new LinkedList<>();
	}
	
	// 每次新插入一个元素，都把 n - 1 个元素给重新出队列入队列一番
	public void push(int x) {
		que.add(x);
		for (int i = 0; i < que.size() - 1; i++)
			que.add(que.poll());
	}

	public int pop() { return que.remove(); }

	public int top() { return que.peek(); }

	public boolean empty() { return que.isEmpty(); }
}
