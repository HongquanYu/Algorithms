package bloomberg.designMarathon;

/** @author: Hongquan Yu
 *  @date: Feb 25, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class CheckPoint {
	int id;
	int distance;
	int count;
	Node<Runner> head, tail;
	
	public CheckPoint(int id, int dis) {
		this.id = id;
		this.distance = dis;
		count = 0;
	}
	
	// 从当前 checkPoint 移除一个 Runner
	public Runner removeRunner(Node<Runner> node) {
		if (count == 0)
			return null;
		else if (count == 1) {
			head = null;
			tail = null;
		} else {
			if (node == head) {
				head = head.next;
				head.prev = null;
			} else if (node == tail) {
				tail = tail.prev;
				tail.next = null;
			} else {
				node.prev.next = node.next;
				node.next.prev = node.prev;
			}
		}
		count--;
		
		return node.data;
	}
	
	// 从末尾加入一个 Runner
	public void addRunner(Node<Runner> node) {
		if (count == 0) {
			head = node;
			tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		count++;
	}
}
