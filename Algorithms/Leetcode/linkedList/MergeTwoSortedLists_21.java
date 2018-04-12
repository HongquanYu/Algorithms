package linkedList;

/** @author: Hongquan Yu
 *  @date: Jan 9, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class MergeTwoSortedLists_21 {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode tmp = new ListNode(-1);
        ListNode f = l1, s = l2, res = tmp; 
        
        while (f != null && s != null) {
            if (f.val <= s.val) {
                tmp.next = f;
                f = f.next;
            } else {
                tmp.next = s;
                s = s.next;
            }
            tmp = tmp.next;
        }
        tmp.next = f != null ? f : s;
    		
    		return res.next;
    }
    
    // Resursion
    public static ListNode mergeTwoList2(ListNode l1, ListNode l2) {
    		if (l1 == null)	return l2;
    		if (l2 == null)	return l1;
    		
    		if (l1.val < l2.val) {
    			l1.next = mergeTwoList2(l1.next, l2);
    			return l1;
    		} else {
    			l2.next = mergeTwoList2(l2.next, l1);
    			return l2;
    		}
    }
    
    public static void main(String [] args) {
    		ListNode l = new ListNode(1);
    		l.next = new ListNode(2);
    		l.next.next = new ListNode(4);
    		
    		ListNode r = new ListNode(1);
    		r.next = new ListNode(3);
    		r.next.next = new ListNode(4);
    		
    		mergeTwoLists(l, r);
    }
}
