package bloomberg.designLRU;

/** @author: Hongquan Yu
 *  @date: Feb 23, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
class Node {
	int hashMapKey;		// 
	Node next, prev;
	Node(int key) {
		this.hashMapKey = key;
		this.next = null;
		this.prev = null;
	}
}
