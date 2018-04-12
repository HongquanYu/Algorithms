package linkedList;

public class ReverseKGroup_25 {
	
	public static ListNode reverseKGroup(ListNode head, int k) {
	    ListNode curr = head;
	    int count = 0;
	    while (curr != null && count < k) { 	// 找到下一个k-group的起点
	        curr = curr.next;
	        count++;
	    }
	    
	    if (count == k) { 		// 
	        curr = reverseKGroup(curr, k); 		// 迭代找后面的groups
	        
	        while (count-- > 0) {
	            ListNode tmp = head.next;	//
	            head.next = curr;			// head指向下一个k-group list的头节点
	            curr = head;					// 用curr指向当前节点，把curr的节点往前移
	            head = tmp; 					// head往前移
	        }
	        
	        head = curr;		// head得指向当前k-group的头节点
	    }
	    
	    return head;
	}
	
    public static ListNode reverseKGroup1(ListNode head, int k) {
        if (k <= 1 || head == null || head.next == null)    return head;
        ListNode fkhd = new ListNode(0);    // a fakehead to ease operation
        fkhd.next = head;
        ListNode h = head;  // counter to count the number of nodes
        int count = 0;
        while ( h != null) {
            h = h.next;
            count++;
        }
        if (count < k)  return head;    // not enough to reverse, return
        int times = count / k;      // reverse times
        ListNode p = fkhd;          // to trace where the current operation node is
        ListNode q = p;             // to modify the reversed subarray pointer
        
        System.out.println("initial array: ");
        show(fkhd.next);

        while (times-- > 0) {      // how many times of reverse
            ListNode sf = p.next;
            System.out.println("------Subarray starts: " + sf.val);
            int t = k;  // counter to move calculate subarray
            while (t-- > 0) {   // find the indices of subarray
                p = p.next;
            }
            ListNode sr = p;
            p = sf;
            System.out.println("------Subarray ends: " + sr.val);
            q.next = reverse(sf, sr);
            System.out.println("current array states:");
            show(fkhd.next);
            q = p;
        }
        return fkhd.next;
    }
    
    private static ListNode reverse(ListNode start, ListNode tail) {   // reverse subarray, return head of reversed subarray
        ListNode terminate = tail.next;
        ListNode n = tail.next;
        ListNode s = start;
        while (s != terminate) {
            ListNode tmp = s.next;
            s.next = n;
            n = s;
            s = tmp;
        }
        return tail;
    }
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static void show(ListNode tt) {
		ListNode ln = tt;
		while (ln != null) {
			System.out.print(ln.val + " ");
			ln = ln.next;
		}
        System.out.println();
	}
	
	public static void main(String[] args) {

		ListNode list1 = new ListNode(1);
		ListNode listone = list1;
		list1.next = new ListNode(2);
		list1 = list1.next;
		list1.next = new ListNode(3);
		list1 = list1.next;
		list1.next = new ListNode(4);
		list1 = list1.next;
		
		show(reverseKGroup(listone, 2));
	}
}
