package ListProblem;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import twoPointers.PalindromeLinkedList_234;

/** @author: Hongquan Yu
 *  @date: Jan 31, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SumList_5 {
	
	/* Follow Up, 列表顺序和digits顺序是一样的！*/
	
	public static ListNode addLists(ListNode l1, ListNode l2) {
		int n1 = l1.length();
		int n2 = l2.length();
		
		if (n1 < n2) 	l1 = padList(l1, n2 - n1);
		else				l2 = padList(l2, n1 - n2);
		
		PartialSum sum = addListHelper(l1, l2);
		if (sum.carry == 0)		// 最高位没有进位了
			return sum.sum;
		else {					// 最高位还剩一个进位，添加到最前面！
			ListNode res = insertBefore(sum.sum, sum.carry);
			return res;
		}
	}
	
	// 递归从链表尾部开始计算结果， 因为两个链表一样长，所以不会存在一个节点存在一个不存在的情况
	private static PartialSum addListHelper(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {		// 同时为空，返回一个空节点
			PartialSum sum = new PartialSum();
			return sum;
		}
		
		PartialSum sum = addListHelper(l1.next, l2.next);
		
		int val = sum.carry + l1.val + l2.val;
		
		ListNode full_result = insertBefore(sum.sum, val % 10);
		
		sum.sum = full_result;
		sum.carry = val / 10;
		return sum;
	}
	
	private static ListNode padList(ListNode l, int padding) {
		ListNode head = l;
		for (int i = 0; i < padding; i++)
			head = insertBefore(head, 0);
		return head;
	}
	
	private static ListNode insertBefore(ListNode list, int data) {
		ListNode node = new ListNode(data);
		if (list != null)
			node.next = list;
		return node;
	}
	
	/* Follow Up, 列表顺序和digits顺序是一样的！我们也可以先reverse list然后再调用加法*/
	
	public static ListNode sumList(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null)
			return null;
		
		int carry = 0;
		ListNode dummy = new ListNode(-1);
		ListNode p1 = l1, p2 = l2, ptr = dummy;
		while (p1 != null && p2 != null) {
			ptr.next = new ListNode((p1.val + p2.val + carry) % 10);
			carry = (p1.val + p2.val + carry) / 10;
			ptr = ptr.next;
			p1 = p1.next;
			p2 = p2.next;
		}
		while (p1 != null) {
			ptr.next = new ListNode((p1.val + carry) % 10);
			carry = (p1.val + carry) % 10;
			ptr = ptr.next;
			p1 = p1.next;
		}
		
		while (p2 != null) {
			ptr.next = new ListNode((p2.val + carry) % 10);
			carry = (p2.val + carry) % 10;
			ptr = ptr.next;
			p2 = p2.next;
		}
		if (carry != 0) {
			ptr.next = new ListNode(1);
		}
		
		return dummy.next;
	}
	
	public static ListNode sumList2(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null)
			return null;
		
		int carry = 0;
		ListNode dummy = new ListNode(-1);
		ListNode p1 = l1, p2 = l2, ptr = dummy;
		while (p1 != null && p2 != null) {
			ptr.next = new ListNode((p1.val + p2.val + carry) % 10);
			carry = (p1.val + p2.val + carry) / 10;
			ptr = ptr.next;
			p1 = p1.next;
			p2 = p2.next;
		}
		while (p1 != null) {
			ptr.next = new ListNode((p1.val + carry) % 10);
			carry = (p1.val + carry) % 10;
			ptr = ptr.next;
			p1 = p1.next;
		}
		
		while (p2 != null) {
			ptr.next = new ListNode((p2.val + carry) % 10);
			carry = (p2.val + carry) % 10;
			ptr = ptr.next;
			p2 = p2.next;
		}
		if (carry != 0)
			ptr.next = new ListNode(1);
		
		return dummy.next;
	}
	
	public static ListNode addLists(ListNode l1, ListNode l2, int carry) {
		if (l1 == null && l2 == null && carry == 0)
			return null;
		
		ListNode res = new ListNode();
		int value = carry;
		if (l1 != null)	value += l1.val;
		if (l2 != null)	value += l2.val;
		
		res.val = value % 10;
		if (l1 != null || l2 != null)
			res.next = addLists(l1 == null ? null : l1.next,
					l2 == null ? null : l2.next,
					value >= 10 ? 1 : 0);

		return res;
	}
	
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(7);
		ListNode p = l1;
		p.next = new ListNode(1);
		p = p.next;
		p.next = new ListNode(6);
		
		ListNode l2 = new ListNode(5);
		ListNode q = l2;
		q.next = new ListNode(9);
		q = q.next;
		q.next = new ListNode(2);
		System.out.println("Original: ");
		print(l1);
		System.out.println();
		print(l2);
		System.out.println();
		print(addLists(l1, l2, 0));
		
		ListNode l3 = new ListNode(7);
		ListNode r = l3;
		r.next = new ListNode(1);
		r = r.next;
		r.next = new ListNode(6);
		
		ListNode l4 = new ListNode(5);
		ListNode s = l4;
		s.next = new ListNode(9);
		s = s.next;
		s.next = new ListNode(8);
		System.out.println("Original: ");
		print(l3);
		System.out.println();
		print(l4);
		System.out.println();
		print(addLists(l3, l4, 0));
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
