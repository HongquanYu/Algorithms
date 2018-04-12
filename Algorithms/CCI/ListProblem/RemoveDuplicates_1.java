package ListProblem;

import java.util.HashSet;
import java.util.Set;
import string.IntegerToEnglishWords_273;

/** @author: Hongquan Yu
 *  @date: Jan 30, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RemoveDuplicates_1 {
	
	/* 时间复杂度是n，空间复杂度是n */
	public static ListNode removeDups(ListNode list) {
		Set<Integer> set = new HashSet<>();
		
		ListNode itr = list, pre = null;
		while (itr != null) {
			if (set.contains(itr.val)) {
				pre.next = itr.next;
			} else {
				set.add(itr.val);
				pre = itr;
			}
			itr = itr.next;
		}
		
		return list;
	}
	
	/* 若是不能用buffer，那么用two pointers，时间复杂度为二次，空间为1 */
	
	public static ListNode removeDups2(ListNode list) {
		ListNode itr = list;
		while (itr != null) {
			ListNode itr2 = itr;
			while (itr2.next != null) {
				if (itr2.next.val == itr.val)
					itr2.next = itr2.next.next;
				else
					itr2 = itr2.next;
			}
			itr = itr.next;
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		ListNode listNode = new ListNode(5);
		ListNode p = listNode;
		p.next = new ListNode(1);
		p = p.next;
		p.next = new ListNode(3);
		p = p.next;
		p.next = new ListNode(1);
		p = p.next;
		p.next = new ListNode(3);
		p = p.next;
		p.next = new ListNode(5);
		p = p.next;
		p.next = new ListNode(5);
		p = p.next;
		p.next = new ListNode(5);
		p = p.next;
		p.next = new ListNode(5);
		System.out.println("Original: ");
		print(listNode);
		System.out.println();
		print(removeDups2(listNode));
	}
	
	private static void print(ListNode l) {
		ListNode p = l;
		while (p != null) {
			System.out.print(p.val + ", ");
			p = p.next;
		}
		System.out.println();
	}
}
