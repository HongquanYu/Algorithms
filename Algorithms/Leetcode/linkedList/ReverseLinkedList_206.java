package linkedList;

/** @author: Hongquan Yu
 *  @date: Feb 1, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ReverseLinkedList_206 {
	
	/* iterative solution */
	
	public ListNode reverseList(ListNode head) {
		ListNode newHead = null;
		
		while (head != null) {
			ListNode next = head.next;
			head.next = newHead;
			newHead = head;
			head = next;
		}
		
		return newHead;
	}
	
    /* recursive solution */
	
	public ListNode reverseListR(ListNode head) {
		return reverseListInt(head, null);
	}

	private ListNode reverseListInt(ListNode head, ListNode newHead) {
		if (head == null)
			return newHead;
		ListNode next = head.next;
		head.next = newHead;
		return reverseListInt(next, head);
	}
}
