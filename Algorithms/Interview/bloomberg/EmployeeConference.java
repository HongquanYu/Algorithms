package bloomberg;

import java.util.PriorityQueue;

/** @author: Hongquan Yu
 *  @date: Feb 13, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class EmployeeConference {
	
	/** Partition to do! */
	
	public ListNode sort(ListNode[] para) {
		return partition(para, 0, para.length - 1);
	}
	
	private ListNode partition(ListNode[] p, int l, int r) {
		if (l == r)
			return p[l];
		else if (l < r) {
			int mid = l + (r - l) / 2;
			ListNode l1 = partition(p, l, mid);
			ListNode l2 = partition(p, mid + 1, r);
			
			return merge(l1, l2);
		} else
			return null;
	}
	
	private ListNode merge(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		
		if (l1.data < l2.data) {
			l1.next = merge(l1.next, l2);
			return l1;
		} else {
			l2.next = merge(l1, l2.next);
			return l2;
		}
	}
	
	/** PriorityQueue to do! */
	
	public ListNode getPriority(ListNode[] l) {
		PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.data - b.data);
		int i = l.length;
		
		while (--i >= 0) {
			ListNode ls = l[i];
			while (ls != null) {
				pq.add(ls);
				ls = ls.next;
			}
		}
		
		ListNode res = pq.poll(), ptr = res;
		while(!pq.isEmpty()) {
			ptr.next = pq.poll();
			ptr = ptr.next;
			ptr.next = null;
		}
		
		return res;
	}
	
	public class ListNode {
		char data;
		ListNode next;
		
		public ListNode(char d) {
			this.data = d;
		}
	}
	
	public static void main(String[] args) {
		ListNode[] para = new ListNode[3];
		
		EmployeeConference ec = new EmployeeConference();
		
		para[0] = ec.new ListNode('a');
		ListNode ptr = para[0];
		ptr.next = ec.new ListNode('b');
		ptr = ptr.next;
		ptr.next = ec.new ListNode('c');
		
		
		para[1] = ec.new ListNode('b');
		ListNode cur = para[1];
		cur.next = ec.new ListNode('f');
		cur = cur.next;
		cur.next = ec.new ListNode('h');
		
		para[2] = ec.new ListNode('c');
		ListNode cc = para[2];
		cc.next = ec.new ListNode('g');
		cc = cc.next;
		cc.next = ec.new ListNode('k');
		
		ListNode t = ec.sort(para);
	
		while (t != null) {
			System.out.print(t.data + ", " );
			t = t.next;
		}
		System.out.println();
		ListNode p = ec.getPriority(para);
		
		while (p != null) {
			System.out.print(p.data + ", " );
			p = p.next;
		}
	}
}
