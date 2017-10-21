package dfs;

/** @author: Hongquan Yu
 *  @date: Oct 17, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class PopulatingNextRightPointersInEachNode_116 {
	public void connect(TreeLinkNode root) {
		while (root != null) {
			TreeLinkNode p = root;
			while (p != null && p.left != null) {
				p.left.next = p.right;
				p.right.next = p.next == null ? null : p.next.left;
				p = p.next;
			}
			root = root.left;
		}
	}
	
	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}
	
	public void connect2(TreeLinkNode root) {
		if (root == null)
			return;

		if (root.left != null) {
			root.left.next = root.right;
			if (root.next != null)
				root.right.next = root.next.left;
		}

		connect2(root.left);
		connect2(root.right);
	}
}
