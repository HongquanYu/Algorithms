package ListProblem;

/** @author: Hongquan Yu
 *  @date: Jan 31, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LoopDetection_8 {
	
	/* Floyd's cycle detection */
	
	public static ListNode loopDetection(ListNode list) {
		if (list == null)
			return null;
		
		ListNode slow = list;
		ListNode fast = list;
		boolean isCycle = false;
		
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (slow == fast) {
				isCycle = true;
				break;
			}
		}
		
		if (!isCycle)		// 没有圈
			return null;
		
		slow = list;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		
		return slow;
	}
	
	/*  */
	
	
}
