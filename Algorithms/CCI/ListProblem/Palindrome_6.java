package ListProblem;

import java.util.Stack;


/** @author: Hongquan Yu
 *  @date: Jan 31, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Palindrome_6 {
	
	/* Reverse and Compare! */
	
	public static boolean isPalindrome(ListNode l) {
		if (l == null)
			return false;
		
		ListNode reverse = reverseList(l);
		ListNode ptr = l;
		
		while (ptr != null) {
			if (ptr.val != reverse.val)
				return false;
			ptr = ptr.next;
			reverse = reverse.next;
		}
		return true;
	}
	
	private static ListNode reverseList(ListNode l) {
		ListNode tail = null, ptr = l;
		while (ptr != null) {
			ListNode n = ptr.next;
			ptr.next = tail;
			tail = ptr;
			ptr = n;
		}
		
		return tail;
	}
	
	/* Iterative Approach */
	
	public static boolean isPalindrome2 (ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		
		Stack<Integer> stack = new Stack<>();
		
		while (fast != null && fast.next != null) {
			stack.push(slow.val);
			slow = slow.next;
			fast = fast.next;
		}
		
		if (fast != null)	slow = slow.next;	// odd number, skip the middle node!
		
		while (slow != null) {
			int top = stack.pop().intValue();
			if (top != slow.val)
				return false;
			slow = slow.next;
		}
		return true;
	}
	
	/* Recursion */
	
	public static boolean isPalindrome3(ListNode head) {
		int len = lengthOfList(head);
		Result p = isPalindromeRecurse(head, len);
		return p.result;
	}
	
	private static Result isPalindromeRecurse(ListNode head, int len) {
		if (head == null || len <= 0)		// Even number of nodes
			return new Result(head, true);
		else if (len == 1)					// Odd number of nodes
			return new Result(head.next, true);
		
		Result res = isPalindromeRecurse(head.next, len - 2);		// Recurse on sublist
		
		if (!res.result || res.node == null)		// if child calls are not a palindrome, pass back up a failure
			return res;
		
		res.result = (head.val == res.node.val);	// check if matching corresponding node on other side
		res.node = res.node.next;				// Return corresponding node
		
		return res;
	}
	
	private static int lengthOfList(ListNode n) {
		int size = 0;
		while (n != null) {
			size++;
			n= n.next;
		}
		return size;
	}
	
	public static void main(String[] args) {
		ListNode l3 = new ListNode(7);
		ListNode r = l3;
		r.next = new ListNode(1);
		r = r.next;
		r.next = new ListNode(6);
		
		ListNode l4 = new ListNode(5);
		ListNode s = l4;
		s.next = new ListNode(9);
		s = s.next;
		s.next = new ListNode(5);
		
		ListNode l5 = new ListNode(8);
		
		print(l3);
		System.out.println("is Palindrome? " + isPalindrome(l3));
		System.out.println();
		
		print(l4);
		System.out.println();
		System.out.println("is Palindrome? " + isPalindrome(l4));
		
		print(l5);
		System.out.println();
		System.out.println("is Palindrome? " + isPalindrome(l5));
		
		System.out.println("is null Palindrome? " + isPalindrome(null));
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
