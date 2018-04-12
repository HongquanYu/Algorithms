package linkedList;

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
		ListNode mid = null;		// loop 完指向中点

		while (fast != null && fast.next != null) {
			mid = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		mid.next = null;		// 截断中点使其成为两个lists

		ListNode p = sortList(head);
		ListNode l = sortList(slow);

		return merge(p, l);
	}

	private ListNode merge(ListNode f, ListNode l) {
		ListNode res = new ListNode(0);
		ListNode ptr = res;

		while (f != null && l != null) {
			if (f.val < l.val) { 	// store the first node into list
				ptr.next = f;
				f = f.next;
			} else { 				// store the last node into list
				ptr.next = l;
				l = l.next;
			}
			ptr = ptr.next;	// ALWAY FORGOTTEN!
		}
		
		ptr.next = (f != null) ? f : l;
		
		return res.next;
	}
}
