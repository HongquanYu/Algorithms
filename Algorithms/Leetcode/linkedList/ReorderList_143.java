package linkedList;

public class ReorderList_143 {
	
	/** 找到中点，reverse 后面的链表，然后再插入 */
	
	public void reorderList(ListNode head) {
		if (head == null || head.next == null)
			return;

		// Find the middle of the list
		ListNode slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		// Reverse the half after middle 1->2->3->4->5->6 to 1->2->3->6->5->4
		ListNode mid = slow, cur = slow.next;
		while (cur.next != null) {
			ListNode tmp = cur.next;
			cur.next = tmp.next;
			tmp.next = mid.next;
			mid.next = tmp;
		}

		// Start reorder one by one 1->2->3->6->5->4 to 1->6->2->5->3->4
		slow = head;
		fast = mid.next;
		while (slow != mid) {
			mid.next = fast.next;
			fast.next = slow.next;
			slow.next = fast;
			slow = fast.next;
			fast = mid.next;
		}
	}
}
