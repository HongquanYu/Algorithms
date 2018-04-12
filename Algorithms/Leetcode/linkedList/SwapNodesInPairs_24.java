package linkedList;

/** @author: Hongquan Yu
 *  @date: Feb 1, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SwapNodesInPairs_24 {
	public ListNode swapPairs(ListNode head) {
		if ((head == null) || (head.next == null))
			return head;
		ListNode n = head.next;
		head.next = swapPairs(head.next.next);
		n.next = head;
		return n;
	}
	
	/** 指针 Manipulation， 要旨就是要站在前一个节点操作后两个节点，这样比较容易！ */
	
	public ListNode swapPairs2(ListNode head) {
		ListNode newHead = new ListNode(0);
		newHead.next = head;
		ListNode pre = newHead;
		
		/* 我们站在 pre 的位置去操作下两个节点，当后面只有一个节点的时候，我们也不需要去逆转 */
		while (pre.next != null && pre.next.next != null) {	// 
			ListNode cur = pre.next;
			ListNode next = pre.next.next;
			cur.next = next.next;
			pre.next = next;
			pre.next.next = cur;
			pre = pre.next.next;
		}
		
		return newHead.next;
	}
	
	/* 或者以当前节点的视角来做，但是得保留一个 pre 节点 */
	
    public ListNode swapPairs3(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head, pre = dummy;
        
        while (cur != null && cur.next != null) {
        		ListNode p = cur.next.next;
        		pre.next = cur.next;
        		cur.next.next = cur;
        		cur.next = p;
        		pre = cur;
        		cur = cur.next;
        }
        return dummy.next;
    }	
}
