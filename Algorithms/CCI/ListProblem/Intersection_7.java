package ListProblem;

import java.util.HashSet;
import java.util.Set;

/** @author: Hongquan Yu
 *  @date: Jan 31, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Intersection_7 {
	public static ListNode isIntersect(ListNode l1, ListNode l2) {
		Set<ListNode> set = new HashSet<>();
		
		for (ListNode ptr = l1; ptr != null; ptr = ptr.next) {
			set.add(ptr);
		}
		for (ListNode itr = l2; itr != null; itr = itr.next)
			if (set.contains(itr))
				return itr;
		
		return null;
	}
	
	/*  */
	public static ListNode findIntersection(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null)
			return null;
		
		IntersectionResult res1 = getTailAndSize(l1);
		IntersectionResult res2 = getTailAndSize(l2);
		
		if (res1.tail != res2.tail)		// 必须要有相同的尾节点
			return null;
		
		ListNode shorter = res1.size < res2.size ? l1 : l2;
		ListNode longer  = res1.size < res2.size ? l2 : l1;
		
		longer = getKthNode(longer, Math.abs(res1.size - res2.size));
		
		while (shorter != longer) {	// 遍历直到intersection
			shorter = shorter.next;
			longer = longer.next;
		}
		
		return longer;
	}
	
	private static IntersectionResult getTailAndSize(ListNode list) {
		if (list == null)	return null;
		
		int size = 1;
		ListNode current = list;
		while (current.next != null) {
			size++;
			current = current.next;
		}
		return new IntersectionResult(current, size);
	}
	
	private static ListNode getKthNode(ListNode head, int k) {
		ListNode current = head;
		while (k > 0 && current != null) {
			current = current.next;
			k--;
		}
		return current;
	}
}
