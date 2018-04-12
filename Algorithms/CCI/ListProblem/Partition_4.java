package ListProblem;

/** @author: Hongquan Yu
 *  @date: Jan 30, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Partition_4 {
	
	/* Stable */
	
	public static ListNode partition(ListNode list, int x) {
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		ListNode small = new ListNode(Integer.MIN_VALUE);
		dummy.next = list;
		
		ListNode itr = dummy;	// 用来遍历
		ListNode ptr = small;	// 用来遍历
		
		while (itr != null && itr.next != null) {
			if (itr.next.val < x) {		// 当前元素比x小，需要移除列表并加到小的列表去
				ListNode t = itr.next;
				itr.next = t.next;
				
				ptr.next = t;
				ptr = ptr.next;
			} else						// 大
				itr = itr.next;
		}
		ptr.next = dummy.next;
		
		return small.next;
	}
	
	/* 既然题意不要求stable，那么我们可以随便插入！ */
	
	public static ListNode partition2(ListNode node, int x) {
		ListNode head = node;
		ListNode tail = node;

		while (node != null) {
			ListNode next = node.next;	// 缓存下一个node
			if (node.val < x) {		// head指针从头插入, 这里指针的next指针已经指向了head或者tail了
				node.next = head;
				head = node;
			} else {					// tail从后插入
				tail.next = node;
				tail = node;
			}
			node = next;				// 指针向下移
		}
		tail.next = null;	// 链表尾部结束

		return head;
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
		System.out.print("Original(3): 	");
		print(listNode);
		System.out.print("\nPartition: 	");
		print(partition2(listNode, 3));
		System.out.println(" ------------------------------------- ");
		
		
		ListNode ln = new ListNode(8);
		ListNode q = ln;
		q.next = new ListNode(7);
		q = q.next;
		q.next = new ListNode(6);
		q = q.next;
		q.next = new ListNode(5);
		q = q.next;
		q.next = new ListNode(4);
		q = q.next;
		q.next = new ListNode(3);
		q = q.next;
		q.next = new ListNode(2);
		q = q.next;
		q.next = new ListNode(1);
		q = q.next;
		q.next = new ListNode(-1);
		
		System.out.print("Original(5): 	");
		print(ln);
		System.out.print("\nPartition: 	");
		print(partition2(ln, 5));
		System.out.println(" ------------------------------------- ");
		
		ListNode pp = new ListNode(3);
		ListNode t = pp;
		t.next = new ListNode(5);
		t = t.next;
		t.next = new ListNode(8);
		t = t.next;
		t.next = new ListNode(5);
		t = t.next;
		t.next = new ListNode(10);
		t = t.next;
		t.next = new ListNode(2);
		t = t.next;
		t.next = new ListNode(1);
		
		System.out.print("Original(5): 	");
		print(pp);
		System.out.print("\nPartition: 	");
		print(partition2(pp, 5));
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
