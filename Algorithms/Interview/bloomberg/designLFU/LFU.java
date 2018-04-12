package bloomberg.designLFU;

import java.util.HashMap;

/** @author: Hongquan Yu
 *  @date: Feb 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** 用2个 HashMap 能解决 LFU 问题
 * 1，一个 HashMap 存当前 key - value 键值对，共常数查询
 * 2，另外一个 HashMap 存的是 key - node 对，node 存的是其频次 Frequency
 * 3，每一次查询 get 的时候，若存在记录，则需要更新其频次到高一个频次，若是当前频次是最低频次并且没有了其他元素在当前频次上，则自增最低频次
 * 4，每一次放入 put 的时候，
 * 	- 若存在记录，则覆盖原纪录，并做一次查询 get（以修改追踪其频度）
 *  - 若不存在记录，
 *  		- 检查当前空间是否满了，满了就移除最低频次的那个元素（这里不需要更新最小频度，新添加进去的自然成为最小频度），然后再添加进去
 *  		- 没有满，直接添加，并且设其为最小频度 */

public class LFU {
	private Node head = null; 	// 管理频度并将其排序
	private int cap = 0; 		// 容量
	private HashMap<Integer, Integer> keyValue;	// key - value pair
	private HashMap<Integer, Node> keyNode; 		// key - node pairs

	public LFU(int capacity) {
		this.cap = capacity;
		keyValue = new HashMap<>();
		keyNode = new HashMap<>();
	}

	/** 每一次查询，都把查询元素的count+1，没查到返回 -1 */

	public int get(int key) {
		if (keyValue.containsKey(key)) {
			increaseCount(key);
			return keyValue.get(key);
		}

		return -1;
	}

	/**
	 * 若果存在 key， 那么就替换 value 并移到表头 不存在 key 需要新增，首先看是否表满，若满则移除最后一个。然后从头加进去
	 */

	public void put(int key, int value) {
		if (cap == 0) 	return;
		
		if (keyValue.containsKey(key)) {		// 存在，直接 update
			keyValue.put(key, value);
		} else {						// 不存在，新建
			if (keyValue.size() < cap)	 	// 还没满，直接添加
				keyValue.put(key, value);
			else { 							// 已经满了，删除频度最小的再添加
				removeOld();
				keyValue.put(key, value);
			}

			addToHead(key); 		// 表头添加
		}

		increaseCount(key); 		// 增加频度
	}
	
	/** 频度链表 表头添加节点 */
	
	private void addToHead(int key) {
		if (head == null) { 		// 空表，直接添加
			head = new Node(0);
			head.keys.add(key);
		} else if (head.frequency > 0) { 	// 添加一个
			Node node = new Node(0);
			node.keys.add(key);
			node.next = head;
			head.prev = node;
			head = node;
		} else { 		// 表头节点没有频度
			head.keys.add(key);
		}

		keyNode.put(key, head); 	// 用Map 记录 当前频度 和 节点
	}
	
	/**  */
	
	private void increaseCount(int key) {
		Node node = keyNode.get(key);
		node.keys.remove(key); 	// remove current record to next node

		if (node.next == null) { // no next node, build a new one
			node.next = new Node(node.frequency + 1);
			node.next.prev = node;
			node.next.keys.add(key);
		} else if (node.next.frequency == node.frequency + 1) { //
			node.next.keys.add(key);
		} else { // add a new node after current key
			Node tmp = new Node(node.frequency + 1);
			tmp.keys.add(key);
			tmp.prev = node;
			tmp.next = node.next;
			node.next.prev = tmp;
			node.next = tmp;
		}

		keyNode.put(key, node.next);
		if (node.keys.size() == 0) // remove empty nodes
			removeNode(node);
	}
	
	/**  */
	
	private void removeOld() {
		if (head == null) 	return;

		int old = 0;
		for (int n : head.keys) { // get first key in head node - least frequency
			old = n;
			break;
		}

		head.keys.remove(old);
		if (head.keys.size() == 0)
			removeNode(head);

		keyNode.remove(old);
		keyValue.remove(old);
	}

	/** 当前链表移除一个节点 */
	
	private void removeNode(Node node) {
		if (node.prev == null)	// 要移除的是首节点
			head = node.next;
		else						// 直接移除本节点
			node.prev.next = node.next;
		
		if (node.next != null)	// 更新下个节点的 prev 指针
			node.next.prev = node.prev;
	}
}
