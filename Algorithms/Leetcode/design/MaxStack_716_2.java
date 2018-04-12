package design;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/** @author: Hongquan Yu
 *  @date: Feb 9, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MaxStack_716_2 {
	TreeMap<Integer, List<Node>> map;
	DoubleLinkedList dll;

	public MaxStack_716_2() {
		map = new TreeMap<>();
		dll = new DoubleLinkedList();
	}

	public void push(int x) {
		Node node = dll.add(x);
		if (!map.containsKey(x))
			map.put(x, new ArrayList<Node>());
		map.get(x).add(node);
	}

	public int pop() {
		int val = dll.pop();
		List<Node> L = map.get(val);
		L.remove(L.size() - 1);
		if (L.isEmpty())
			map.remove(val);
		return val;
	}

	public int top() {
		return dll.peek();
	}

	public int peekMax() {
		return map.lastKey();
	}

	public int popMax() {
		int max = peekMax();
		List<Node> L = map.get(max);
		Node node = L.remove(L.size() - 1);
		dll.unlink(node);
		if (L.isEmpty())
			map.remove(max);
		return max;
	}

	class DoubleLinkedList {
		Node head, tail;

		public DoubleLinkedList() {
			head = new Node(0);
			tail = new Node(0);
			head.next = tail;
			tail.prev = head;
		}

		public Node add(int val) {
			Node x = new Node(val);
			x.next = tail;
			x.prev = tail.prev;
			tail.prev = tail.prev.next = x;
			return x;
		}

		public int pop() {
			return unlink(tail.prev).val;
		}

		public int peek() {
			return tail.prev.val;
		}

		public Node unlink(Node node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			return node;
		}
	}

	class Node {
		int val;
		Node prev, next;
		public Node(int v) {
			val = v;
		}
	}
}
