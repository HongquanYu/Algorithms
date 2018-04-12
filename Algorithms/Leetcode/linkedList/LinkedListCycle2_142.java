package linkedList;

/** @author: Hongquan Yu
 *  @date: Oct 15, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LinkedListCycle2_142 {
	
	/* Find start node of cycle */
	
	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null)
			return null;

		ListNode slow = head;
		ListNode fast = head;
		boolean isCycle = false;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				isCycle = true;
				break;
			}
		}

		if (!isCycle)
			return null;
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}
}
