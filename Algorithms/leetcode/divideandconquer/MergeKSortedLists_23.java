package divideandconquer;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import linkedlist.mergeKLists_23.ListNode;

/** @author: Hongquan Yu
 *  @date: Oct 19, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MergeKSortedLists_23 {
	public ListNode mergeKLists(List<ListNode> lists) {
		if (lists == null || lists.size() == 0)
			return null;

		PriorityQueue<ListNode> queue =
				new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
					@Override
					public int compare(ListNode o1, ListNode o2) {
						if (o1.val < o2.val)
							return -1;
						else if (o1.val == o2.val)
							return 0;
						else
							return 1;
					}
				});

		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;

		for (ListNode node : lists)
			if (node != null)
				queue.add(node);

		while (!queue.isEmpty()) {
			tail.next = queue.poll();
			tail = tail.next;

			if (tail.next != null)
				queue.add(tail.next);
		}
		return dummy.next;
	}
	
	
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

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
