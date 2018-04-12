package reservoirSampling;

import java.util.Random;

import linkedList.ListNode;

/** @author: Hongquan Yu
 *  @date: Mar 14, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class LinkedListRandomNode_382 {
	ListNode head;
	Random random;

	public LinkedListRandomNode_382(ListNode h) {
        head = h;       
        random = new Random();        
    }

	public int getRandom() {
		ListNode ptr = head;
		int selected = ptr.val;
		
		for (int i = 1; ptr.next != null; i++) {
			ptr = ptr.next;
			if (random.nextInt(i + 1) == i)
				selected = ptr.val;
		}

		return selected;
	}
}
