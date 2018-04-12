package twoPointers;

import linkedList.ListNode;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RotateList_61 {
	/** Since n may be a large number compared to the length of list. So we need to know the length
	 * of linked list.After that, move the list after the (l-n%l )th node to the front to finish the
	 * rotation.
	 * 
	 * Ex: {1,2,3} k=2 Move the list after the 1st node to the front
	 * Ex: {1,2,3} k=5, In this case Move the list after (3-5%3=1)st node to the front.
	 * 
	 * So the code has three parts.
	 * 
	 * 1, Get the length
	 * 2, Move to the (l-n%l)th node
	 * 3, Do the rotation */
	
	
	public ListNode rotateRight(ListNode head, int n) {
		if (head == null || head.next == null)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode fast = dummy, slow = dummy;

		int N = 0;
		for ( ; fast.next != null; N++)	// Get the total length
			fast = fast.next;

		for (int j = N - n % N; j > 0; j--) 	// Get the i-n%i th node
			slow = slow.next;

		fast.next = dummy.next; // Do the rotation
		dummy.next = slow.next;
		slow.next = null;

		return dummy.next;
	}
}