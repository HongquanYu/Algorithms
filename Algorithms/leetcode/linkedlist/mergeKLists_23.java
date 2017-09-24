package linkedlist;

public class mergeKLists_23 {
	
	/*
	 * does not accept by leetcode because of time exceeded
	 */
	public static ListNode mergeKLists(ListNode[] lists) {
		int len = lists.length;
		if (len == 0)
			return null;
		len--;

		ListNode originalR = lists[0];
		while (len > 0) { // iterate all the lists through len
			ListNode r = originalR;
			ListNode p = lists[len];
			while (p != null && r != null) { // iterate all the nodes in the list (list len is not empty)
				if (p.val < r.val) { // smaller or equal to r, insert to front of list, update head node pointer
					ListNode t = p;
					p = p.next;
					t.next = r;
					r = t;
					originalR = r;
				} else { // larger than r
					while (r.next != null && r.next.val <= p.val)
						r = r.next;
					if (r.next == null) { // r reachs list end, break loop to next list
						r.next = p;
						break;
					}
                    ListNode tmpP = p;
                    p = p.next;
                    tmpP.next = r.next;
                    r.next = tmpP;
                    r = tmpP;
				}
			}
			// current list is empty or reach end.
			if (r == null) { // current list is empty (does not enter while loop above)
				originalR = p;
			}
			len--;
		}
		return originalR;
	}
	
	//-----------------answer from leetcode forum--------------------
	
	public static ListNode mergeKLists2(ListNode[] lists) {
		return partion(lists, 0, lists.length - 1);
	}

	public static ListNode partion(ListNode[] lists, int s, int e) {
		if (s == e)
			return lists[s];
		if (s < e) {
			int q = (s + e) / 2;
			ListNode l1 = partion(lists, s, q);
			ListNode l2 = partion(lists, q + 1, e);
			return merge(l1, l2);
		} else
			return null;
	}

	// This function is from Merge Two Sorted Lists.
	public static ListNode merge(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		if (l1.val < l2.val) {
			l1.next = merge(l1.next, l2);
			return l1;
		} else {
			l2.next = merge(l1, l2.next);
			return l2;
		}
	}
	//-----------------end--------------------
    
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static void show(ListNode tt) {
		ListNode ln = tt;
		while (tt != null) {
			System.out.print(tt.val + " ");
			tt = tt.next;
		}
	}

	public static void main(String[] args) {

		ListNode list1 = new ListNode(-10);
		ListNode listone = list1;
		list1.next = new ListNode(-10);
		list1 = list1.next;
		list1.next = new ListNode(-9);
		list1 = list1.next;
		list1.next = new ListNode(-9);
		list1 = list1.next;
		list1.next = new ListNode(-9);
		list1 = list1.next;
		list1.next = new ListNode(-9);
		list1 = list1.next;
		list1.next = new ListNode(-7);
		list1 = list1.next;
		list1.next = new ListNode(-2);
		list1 = list1.next;
		list1.next = new ListNode(-2);
		list1 = list1.next;
		list1.next = new ListNode(-1);
		list1 = list1.next;
		list1.next = new ListNode(-1);
		list1 = list1.next;
		list1.next = new ListNode(1);
		list1 = list1.next;
		list1.next = new ListNode(2);
		list1 = list1.next;
		list1.next = new ListNode(3);
		list1 = list1.next;
		list1.next = new ListNode(4);

		ListNode list3 = new ListNode(-9);
		ListNode listthree = list3;
		list3.next = new ListNode(-7);
		list3 = list3.next;
		list3.next = new ListNode(-6);
		list3 = list3.next;
		list3.next = new ListNode(-6);
		list3 = list3.next;
		list3.next = new ListNode(-3);
		list3 = list3.next;
		list3.next = new ListNode(0);
		list3 = list3.next;
		list3.next = new ListNode(1);
		list3 = list3.next;
		list3.next = new ListNode(3);

		ListNode[] pp = new ListNode[2];
		pp[0] = listone;
		pp[1] = listthree;

		show(mergeKLists(pp));
	}
}
