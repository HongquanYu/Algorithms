package linkedList;

/** @author: Hongquan Yu
 *  @date: Feb 1, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class InsertionSortList_147 {
	
	/** Time N^2
	 * 链表前端是排好序的元素，后端是待排序的元素
	 * 每选择一个就从头开始扫描到合适位置进行插入 */
	
	public ListNode insertionSortList(ListNode head) {
		if (head == null)	return head;

		ListNode dummy = new ListNode(0);
		ListNode cur = head;
		ListNode pre = dummy;
		ListNode next = null;
		
		while (cur != null) {
			next = cur.next;
			while (pre.next != null && pre.next.val < cur.val)
				pre = pre.next;
			
			cur.next = pre.next;
			pre.next = cur;
			pre = dummy;
			cur = next;
		}

		return dummy.next;
	}
}
