package linkedlist;

public class SortLinkedList_148 {
	/*
	 * basic idea is that we seperate the list to two lists, recursive this
	 * process and divide in util to basic cases and then merge them.
	 */

	public ListNode sortList(ListNode head) {	//recursive split the list into two lists.
		if (head == null || head.next == null)	// base case
			return head;
		
		ListNode fast = head;
		ListNode slow = head;
		ListNode prev = null;

		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		prev.next = null;

		ListNode p = sortList(head);
		ListNode l = sortList(slow);

		return merge(p, l);
	}

	private ListNode merge(ListNode f, ListNode l) {	// merge the lists into one
		ListNode rtn = new ListNode(0);		// a head node to simplify code
		ListNode cursor = rtn;

		while (f != null && l != null) {
			if (f.val < l.val) { // store the first node into list
				cursor.next = f;
				f = f.next;
			} else { // store the last node into list
				cursor.next = l;
				l = l.next;
			}
			cursor = cursor.next;
		}


		if (f != null)
			cursor.next = f;
		else if (l != null)
			cursor.next = l;
		
		return rtn.next;
	}

	private class ListNode {
		int val;
		ListNode next;

		ListNode(int v) {
			this.val = v;
			next = null;
		}
	}	
}
