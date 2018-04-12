package linkedList;

/** @author: Hongquan Yu
 *  @date: Feb 1, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class AddTwoNumbers_2 {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode p1 = l1, p2 = l2;
		ListNode dummy = new ListNode(0);
		ListNode ptr = dummy;
		int sum = 0;
		
		while (p1 != null || p2 != null) {
			sum /= 10;			// 计算carry 
			if (p1 != null) {
				sum += p1.val;
				p1 = p1.next;
			}
			if (p2 != null) {
				sum += p2.val;
				p2 = p2.next;
			}
			ptr.next = new ListNode(sum % 10);	// 当前节点的值
			ptr = ptr.next;
		}
		
		if (sum >= 10) 	ptr.next = new ListNode(1);
		
		return dummy.next;
	}
}
