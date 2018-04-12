package linkedList;

/** @author: Hongquan Yu
 *  @date: Feb 1, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class SplitLinkedListInParts_725 {
	
	/** 先计数，得到每一个 list 的个数和余数。余数 p 分散在前 p 个 part 里 */
	
	public ListNode[] splitListToParts(ListNode root, int k) {
		ListNode[] parts = new ListNode[k];
		int N = 0;
		
		for (ListNode node = root; node != null; node = node.next)
			N++;
		int single = N / k, r = N % k; // n : minimum guaranteed part size; r : extra nodes spread to the first r parts;
		ListNode curr = root, prev = null;
		
		for (int i = 0; curr != null && i < k; i++, r--) {
			parts[i] = curr;
			for (int j = 0; j < single + (r > 0 ? 1 : 0); j++) {	// 
				prev = curr;
				curr = curr.next;
			}
			prev.next = null;
		}
		
		return parts;
	}
}
