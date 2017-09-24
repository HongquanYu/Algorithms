package linkedlist;

public class ReverseBetweenIndex_92 {
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n)  return head;
        ListNode fkhd = new ListNode(0);
        fkhd.next = head;
        ListNode pf = fkhd, pr = fkhd;
        
        while (--m > 0) pf = pf.next;
        while (--n > 0) pr = pr.next;
        
        pf.next = reverseBetween(pf.next, pr.next, n - m);
        return fkhd.next;
    }
    
    private static ListNode reverseBetween(ListNode start, ListNode tail, int nn) {
        ListNode n = tail.next;
        ListNode s = start;
        System.out.println("Start: " + start.val);
        System.out.println("Tail: " + tail.val);
        System.out.println("s != tail.next: " + (s != tail.next));
        System.out.println("Tail.next: " + tail.next);
        while (nn-- > 0) {
        	System.out.println("Inside while, s: " + s.val);
            ListNode tmp = s.next;
            s.next = n;
            n = s;
            s = tmp;
            System.out.println("Inside while, pointer s: " + s);
        }
        return tail;
    }
    
	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int v) {
			this.val = v;
			next = null;
		}
	}
	
	private static void show(ListNode h) {
		ListNode he = h;
		System.out.println("----List: ");
		while (he != null) {
			System.out.print(he.val + ", ");
			he = he.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {

		ListNode list = new ListNode(1);
		ListNode one = list;
		list.next = new ListNode(2);
		list = list.next;
		list.next = new ListNode(3);
		show(one);
		show(reverseBetween(one, 1, 2));
		
		System.out.println("null != null: " + (null != null));
	}
}
