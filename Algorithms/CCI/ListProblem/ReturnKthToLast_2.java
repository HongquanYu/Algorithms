package ListProblem;

/** @author: Hongquan Yu
 *  @date: Jan 30, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ReturnKthToLast_2 {
	public static ListNode returnKth(ListNode l, int k) {
		ListNode p = l;
		while (k-- > 1) {
			if (p == null)
				return null;
			p = p.next;
		}
		
		return p;
	}
	
	public static int printKthToLast(ListNode head, int k) {
		if (head == null)
			return 0;
		
		int index = printKthToLast(head.next, k) + 1;
		if (index == k)
			System.out.println(k + "th to last node is " + head.val);
		return index;
	}
	
	/* Wrapper class solution */
	
	class Index {
		public int value = 0;
	}

	ListNode kthToLast(ListNode head, int k) {
		Index index = new Index();
		return kthToLast(head, k, index);
	}

	public ListNode kthToLast(ListNode head, int k, Index index) {
		if (head == null) {
			return null;
		}
		ListNode node = kthToLast(head.next, k, index);
		index.value = index.value + 1;
		if (index.value == k) {
			return head;
		}
		return node;
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
//		System.out.println();
//		print(returnKth(listNode, 9));
		printKthToLast(listNode, 7);
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
