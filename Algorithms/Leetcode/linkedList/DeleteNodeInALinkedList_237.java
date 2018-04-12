package linkedList;

/** @author: Hongquan Yu
 *  @date: Feb 1, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class DeleteNodeInALinkedList_237 {
	public void deleteNode(ListNode node) {
	    node.val = node.next.val;
	    node.next = node.next.next;
	}
}
