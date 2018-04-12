package linkedList;

/** @author: Hongquan Yu
 *  @date: Feb 1, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RemoveDuplicatesFromSortedListII_82 {
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null)
			return null;
		ListNode FakeHead = new ListNode(0);
		FakeHead.next = head;
		ListNode pre = FakeHead;
		ListNode cur = head;
		while (cur != null) {
			while (cur.next != null && cur.val == cur.next.val) {
				cur = cur.next;
			}
			if (pre.next == cur) {
				pre = pre.next;
			} else {
				pre.next = cur.next;
			}
			cur = cur.next;
		}
		return FakeHead.next;
	}
}
