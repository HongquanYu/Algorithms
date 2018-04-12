package linkedList;

/** @author: Hongquan Yu
 *  @date: Feb 2, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class PlusOneLinkedList_369 {
	public ListNode plusOne(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		ListNode lastNotNine = dummy, node = head;

		while (node != null) {
			if (node.val != 9)
				lastNotNine = node;
			
			node = node.next;
		}
		
		lastNotNine.val++;
		node = lastNotNine.next;
		
		while (node != null) {
			node.val = 0;
			node = node.next;
		}
		return dummy.val == 1 ? dummy : dummy.next;
	}
	
	/* Recursion */
	
	public ListNode plusOne2(ListNode head) {
		if (DFS(head) == 0) {
			return head;
		} else {
			ListNode newHead = new ListNode(1);
			newHead.next = head;
			return newHead;
		}
	}

	public int DFS(ListNode head) {
		if (head == null)
			return 1;

		int carry = DFS(head.next);

		if (carry == 0)
			return 0;

		int val = head.val + 1;
		head.val = val % 10;
		return val / 10;
	}
}
