package hashTable;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CopyListWithRandomPointer_138 {
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return null;

		Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

		// loop 1. copy all the nodes
		RandomListNode ptr = head;
		while (ptr != null) {
			map.put(ptr, new RandomListNode(ptr.label));
			ptr = ptr.next;
		}

		// loop 2. assign next and random pointers
		ptr = head;
		while (ptr != null) {
			map.get(ptr).next = map.get(ptr.next);
			map.get(ptr).random = map.get(ptr.random);
			ptr = ptr.next;
		}

		return map.get(head);
	}

	class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	};
}
