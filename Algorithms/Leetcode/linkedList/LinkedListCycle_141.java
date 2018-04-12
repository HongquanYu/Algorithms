package linkedList;

import java.util.HashSet;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Oct 15, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LinkedListCycle_141 {
	
	public boolean hasCycle1(ListNode head) {
		Set<ListNode> nodesSeen = new HashSet<>();
		
		while (head != null) {
			if (nodesSeen.contains(head))
				return true;
			else
				nodesSeen.add(head);
			head = head.next;
		}
		
		return false;
	}

	
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null)
			return false;
		
		ListNode slow = head;
		ListNode fast = head.next;
		
		while (slow != fast) {
			if (fast == null || fast.next == null)	// 到达了尾部null, 可以判定没有 cycle
				return false;
			
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return true;
	}
}
