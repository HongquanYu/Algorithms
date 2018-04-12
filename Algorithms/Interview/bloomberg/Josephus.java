package bloomberg;

import linkedList.ListNode;

/** @author: Hongquan Yu
 *  @date: Feb 13, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Josephus {
	
	/** Simple Version is use a single linkedlist to simulate the process */
	
	public int simulation(int total, int kill) {
		ListNode head = createCycle(total);
		ListNode pre = null;
		int start = 1, counter = start;

		while (head != head.next) {
			if (counter == kill) {
				pre.next = head.next;
				head = pre.next;
				counter = 1;
			} else {
				pre = head;
				head = head.next;
				counter++;
			}
		}
		return head.val;
	}
	
	private ListNode createCycle(int total) {
		int count = 1;
		ListNode head = new ListNode(count), ptr = head, tail = head;

		while (count < total) {
			ptr.next = new ListNode(++count);
			ptr = ptr.next;
			tail = tail.next;
		}
		tail.next = head;

		return head;
	}
	
	/** 递推公式是： F(n, k) = (F(n - 1, k) + k) mod n, F(1, k) = 0; */
	
	public int josephus(int n, int k) {
		if (n == 1)
			return 1;
		return (josephus(n - 1, k) + k - 1) % n + 1;
	}
	
	public int josephusIterative(int n, int k) {
		int survive = 0;
		
		for (int i = 2; i <= n; ++i)
			survive = (survive + k) % i;
		
		return survive;
	}
	
	/** Follow Up: What if the K is super big? */
	
	public static void main(String[] args) {
		Josephus j = new Josephus();
/*		System.out.println(j.josephus(5, 2));
		System.out.println(j.josephus(7, 3));
		System.out.println(j.josephus(9999, 5));
		System.out.println(j.simulation(5, 2));
		System.out.println(j.simulation(7, 3));
		System.out.println(j.simulation(9999, 5));*/
//		System.out.println("300: " + j.josephus(300, 6));
//		System.out.println("300: " + j.josephusIterative(300, 6));
		System.out.println("43533: " + j.simulation(43533, 105));
//		System.out.println("364832746: " + j.simulation(364832746, 500));
	}
}
