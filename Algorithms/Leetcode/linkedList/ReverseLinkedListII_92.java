package linkedList;

/** @author: Hongquan Yu
 *  @date: Feb 1, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ReverseLinkedListII_92 {
	
	/** 找到逆转的前一个，然后开始reverse，这个逆转是需要选择一个最简洁最好理解的，这样才能让
	 * 代码更好理解！ */
	
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		for (int i = 0; i < m - 1; i++)		// 找到待转的前一个
			pre = pre.next;

		ListNode start = pre.next;
		ListNode then = start.next;

		for (int i = 0; i < n - m; i++) {	// 逆转
			start.next = then.next;
			then.next = pre.next;
			pre.next = then;
			then = start.next;
		}

		return dummy.next;
	}
}
