package divideandconquer;

/** @author: Hongquan Yu
 *  @date: Oct 20, 2017
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class ReversePairs_493 {
	
	public int reversePairs(int[] nums) {
		int res = 0;
		Node root = null;

		for (int ele : nums) {
			res += search(root, 2L * ele + 1);
			root = insert(root, ele);
		}

		return res;
	}

	private int search(Node root, long val) {
		if (root == null) {
			return 0;
		} else if (val == root.val) {
			return root.cnt;
		} else if (val < root.val) {
			return root.cnt + search(root.left, val);
		} else {
			return search(root.right, val);
		}
	}

	private Node insert(Node root, int val) {
		if (root == null) {
			root = new Node(val);
		} else if (val == root.val) {
			root.cnt++;
		} else if (val < root.val) {
			root.left = insert(root.left, val);
		} else {
			root.cnt++;
			root.right = insert(root.right, val);
		}

		return root;
	}
	
	class Node {
		int val, cnt;
		Node left, right;

		Node(int val) {
			this.val = val;
			this.cnt = 1;
		}
	}
}
