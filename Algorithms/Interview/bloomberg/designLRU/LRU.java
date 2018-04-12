package bloomberg.designLRU;

import java.util.HashMap;

/** @author: Hongquan Yu
 *  @date: Feb 23, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** 用一个链表来排列每个元素使用的频度，从高到低排列
 *  HashMap 提供常数级的查询和更新！但是没办法追踪频度，我们需要一个链表来追踪每个元素的使用频度！
 *  对于get 和 put 操作，我们都可以在 HashMap 里操作完。但是对于频度，我们使用双向链表来追踪。
 *  每一次操作都修改链表指针，可以保证为常数操作！ */

public class LRU {
	private HashMap<Integer, Integer> keyValue;		// 存储 key - value pair， 常数查询
	private HashMap<Integer, Node> keyNode;			// 存储 key - node pair，以方便操作链表，进行频次追踪
	private static int size;		// 追踪当前节点的个数
	private Node head = null;	// 链表头节点
	private Node tail = null;	// 链表尾节点
	private int capacity;		// 当前 LRU 的个数

	public LRU(int capacity) {
		keyValue = new HashMap<>();
		keyNode = new HashMap<>();
		this.capacity = capacity;
		size = 0;
	}
	
	/** 每一次查询，都把查询元素移到表头，没查到返回 -1 */
	
	public int get(int key) {
		if (keyValue.containsKey(key)) {
			moveToHead(key);
			return keyValue.get(key);
		}

		return -1;
	}
	
	/** 若果存在 key， 那么就替换value并移到表头
	 * 不存在 key 需要新增，首先看是否表满，若满则移除最后一个。然后从头加进去  */
	
	public void put(int key, int value) {
		if (keyValue.containsKey(key)) { 	// pair 存在，替换值把它移到开头，然后返回
			keyValue.replace(key, value);
			moveToHead(key);
			return;
		}
		if (size == capacity)		// space is full, remove last one
			removeLast();

		keyValue.put(key, value);
		addToHead(key);
	}
	
	/** 链表操作：移除链表末尾的元素 */
	
	private Node removeLast() {
		Node t = tail;

		if (head == tail) { 	// 只有一个节点
			tail = null;
			head = null;
		} else {		 		// 多个节点，直接用tail 节点移除最后一个就OK
			tail = tail.prev;
			tail.next = null;
		}

		keyValue.remove(t.hashMapKey);	// 移除相应的hashmap里面的 value
		keyNode.remove(t.hashMapKey);	// 移除相应节点

		size--;

		return t;
	}

	/** 链表操作：hit 一个节点，把节点移向开头 */
	
	private void moveToHead(int key) {
		Node node = keyNode.get(key);
		
		// 先把节点从链表里截断出来
		if (node == head) 		// 就是在头节点
			return;
		if (node == tail) { 		// 此节点在尾节点
			tail = tail.prev;
			tail.next = null;
		} else { 				// 在中间节点
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}
		// 把节点插入链表前面
		node.next = head;
		node.prev = null;
		head.prev = node;
		head = node;
	}
	
	/** 操作链表，表头添加节点 */
	private void addToHead(int key) {
		if (head == null) { 			// 空表
			head = new Node(key);
			tail = head;
		} else { 					// 从表头插入
			Node t = new Node(key);
			t.next = head;
			head.prev = t;
			head = t;
		}

		keyNode.put(key, head);		// 在 HashMap 里做记录
		size++;
	}
}
