package ListProblem;

/** @author: Hongquan Yu
 *  @date: Jan 30, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class DeleteMiddleNode_3 {
	public static void deleteMiddle(ListNode m) {
		ListNode p = m;
		if (p == null || p.next == null)
			return;
		
		p.val = p.next.val;
		p.next = p.next.next;
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
		deleteMiddle(p);
		print(listNode);
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
