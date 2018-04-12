package segmentTree;

/** @author: Hongquan Yu
 *  @date: Feb 26, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class Node {
	int left, right;		// 左右区间的值
	boolean covered;		// 表示是否被覆盖
	int count;			// 表示此节点表示的线段区间出现的次数（被覆盖的次数），默认为0
	Node leftChild, rightChild;

	Node(int left, int right) {
		this.left = left;
		this.right = right;
	}
	Node(int left, int right, int cnt, boolean covered) {
		this.left = left;
		this.right = right;
		this.covered = covered;
		this.count = cnt;
	}
	
	public String toString() {
		return "[" + this.left + ", " + this.right + "]"; 
	}
}
