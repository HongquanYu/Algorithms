package bloomberg.designMarathon;

/** @author: Hongquan Yu
 *  @date: Feb 25, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Node<T> {
	T data;		// 
	Node next, prev;
	int count;
	Node(T d) {
		this.data = d;
		this.next = null;
		this.prev = null;
	}
}