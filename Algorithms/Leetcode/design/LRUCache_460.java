package design;
import java.util.HashMap;

public class LRUCache_460 {
	class DLinkedNode {
		int key, value;
		DLinkedNode pre, post;
	}

	private void addNode(DLinkedNode node) {	// Always add the new node right after head;
		node.pre = head;
		node.post = head.post;
		head.post.pre = node;
		head.post = node;
	}

	private void removeNode(DLinkedNode node) {		// Remove an existing node from the linked list.
		DLinkedNode pre = node.pre;
		DLinkedNode post = node.post;
		pre.post = post;
		post.pre = pre;
	}

	private void moveToHead(DLinkedNode node) {		// Move certain node in between to the head.
		this.removeNode(node);
		this.addNode(node);
	}

	private DLinkedNode popTail() {		// pop the current tail.
		DLinkedNode res = tail.pre;
		this.removeNode(res);
		return res;
	}

	private HashMap<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
	private int count, capacity;
	private DLinkedNode head, tail;

	public LRUCache_460(int capacity) {
		this.count = 0;
		this.capacity = capacity;
		head = new DLinkedNode();
		head.pre = null;
		tail = new DLinkedNode();
		tail.post = null;
		head.post = tail;
		tail.pre = head;
	}

	public int get(int key) {
		DLinkedNode node = cache.get(key);
		if (node == null)
			return -1; // should raise exception here.
		this.moveToHead(node);	// move the accessed node to the head;
		return node.value;
	}

	public void put(int key, int value) {
		DLinkedNode node = cache.get(key);

		if (node == null) {
			DLinkedNode newNode = new DLinkedNode();
			newNode.key = key;
			newNode.value = value;
			this.cache.put(key, newNode);
			this.addNode(newNode);
			++count;
			
			if (count > capacity) {
				DLinkedNode tail = this.popTail();	// pop the tail
				this.cache.remove(tail.key);
				--count;
			}
		} else {
			node.value = value;		// update the value.
			this.moveToHead(node);
		}
	}
}
