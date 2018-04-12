package twoPointers;

import linkedList.ListNode;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class PalindromeLinkedList_234 {
	
	/** reverse 再来判断 */
	
	public boolean isPalindrome(ListNode head) {
		ListNode fast = head, slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		if (fast != null) // odd nodes: let right half smaller
			slow = slow.next;
		
		slow = reverse(slow);
		fast = head;

		while (slow != null) {
			if (fast.val != slow.val)
				return false;
			
			fast = fast.next;
			slow = slow.next;
		}
		return true;
	}

	public ListNode reverse(ListNode head) {
		ListNode prev = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}
	
	/** O(N) time, O(1) Space Follow Up */
	
	public boolean isPalindrome2(ListNode head) {
		int N = 0;
		String normal = "", reverse = "";
		
		for (ListNode ptr = head; ptr != null; ptr = ptr.next) {
			normal = normal + ptr.val;
			reverse = ptr.val + reverse;
			N++;
		}
		for (int i = 0; i < N; ++i) {
			if (normal.charAt(i) != reverse.charAt(i))
				return false;
		}
		return true;
	}
}
