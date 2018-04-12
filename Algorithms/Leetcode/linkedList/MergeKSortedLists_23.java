package linkedList;

import java.util.PriorityQueue;

public class MergeKSortedLists_23 {
	
	//-----------------answer from leetcode forum--------------------
	
	public static ListNode mergeKLists2(ListNode[] lists) {
		return partion(lists, 0, lists.length - 1);
	}

	public static ListNode partion(ListNode[] lists, int s, int e) {
		if (s == e)							// 只剩1个list了
			return lists[s];
		if (s < e) {							// 迭代从bottom up开始做merge，然后merge左右两部分已经merge了的lists们
			int q = (s + e) / 2;
			ListNode l1 = partion(lists, s, q);
			ListNode l2 = partion(lists, q + 1, e);
			return merge(l1, l2);
		}
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
    
	
	public static ListNode mergeKLists3(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;

		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(
				lists.length,  (o1, o2) -> o1.val - o2.val);

		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;

		for (ListNode node : lists)		// 遍历list，并把list入queue
			if (node != null)
				queue.add(node);

		while (!queue.isEmpty()) {		// 遍历直到queue为空
			tail.next = queue.poll();	// 当前空链表头节点加入result，
			tail = tail.next;			// tail指针指向result尾节点

			if (tail.next != null)		// 只要此链表还有node，入queue下一个节点开始的链表
				queue.add(tail.next);
		}
		return dummy.next;
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) { val = x; }
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

		show(mergeKLists3(pp));
	}
}
