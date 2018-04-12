package twoPointers;

import linkedList.ListNode;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RemoveNthNodeFromEndOfList_19 {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		int length = 0;

		ListNode first = head;
		while (first != null) {		// 计算node个数
			length++;
			first = first.next;
		}
		length -= n;		// 计算从正数是第多少个
		first = dummy;
		while (length > 0) {			// 遍历找到要移除的前一个
			length--;
			first = first.next;
		}
		first.next = first.next.next;
		return dummy.next;
	}
	
	public ListNode removeNthFromEnd2(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode first = dummy;
		ListNode second = dummy;
		// Advances first pointer so that the gap between first and second is n nodes apart
		for (int i = 1; i <= n + 1; i++) {
			first = first.next;
		}
		// Move first to the end, maintaining the gap
		while (first != null) {
			first = first.next;
			second = second.next;
		}
		second.next = second.next.next;
		return dummy.next;
	}
}
