package ListProblem;

/** @author: Hongquan Yu
 *  @date: Jan 30, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
	ListNode() {}
	
	public int length() {
		ListNode t = this;
		int cnt = 0;
		while (t != null) {
			cnt++;
			t = t.next;
		}
		
		return cnt;
	}
}
