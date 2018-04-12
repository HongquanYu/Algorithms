package linkedList;

/** @author: Hongquan Yu
 *  @date: Feb 1, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class RemoveDuplicatesFromSortedList_83 {
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null)
			return head;
		head.next = deleteDuplicates(head.next);
		return head.val == head.next.val ? head.next : head;
	}
	
	/* Iterative Solution: O(N) */
	
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode ptr = head, tmp = head;
        
        while (ptr != null && ptr.next != null) {
            if (ptr.val != ptr.next.val) {
                if (tmp != null) {
                    tmp.next = ptr.next;
                    tmp = null;
                }
                tmp = ptr.next;
            }
            ptr = ptr.next;
        }
        if (tmp != null)    tmp.next = null;
        
        return head;
    }
    
    /* Simple code */
    
	public ListNode deleteDuplicates3(ListNode head) {
		ListNode list = head;

		while (list != null) {
			if (list.next == null)
				break;

			if (list.val == list.next.val)	// 跳过相同的
				list.next = list.next.next;
			else
				list = list.next;

		}

		return head;
	}
}
