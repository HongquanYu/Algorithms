package binarySearch;

import java.util.Stack;

import binaryTree.TreeNode;

/* @author: Hongquan Yu
   @date: Oct 16, 2017
 *
   @From: University of Maryland, College Park
   @Email: hyu12346@terpmail.umd.edu
 */
public class KthSmallestElementInABST_230 {
	
	/* Binary Search (dfs) */

	public int kthSmallest(TreeNode root, int k) {
		int count = countNodes(root.left);
		
		if (k <= count) {
			return kthSmallest(root.left, k);
		} else if (k > count + 1) {
			return kthSmallest(root.right, k - 1 - count); // 1 is counted as current node
		}

		return root.val;		// k == count + 1 的时候正是root
	}

	public int countNodes(TreeNode n) {
		if (n == null)
			return 0;

		return 1 + countNodes(n.left) + countNodes(n.right);
	}
	
	/* DFS in-order recursive */
	
    private static int number = 0;
    private static int count = 0;

	public int kthSmallest2(TreeNode root, int k) {
		count = k;
		helper(root);
		return number;
	}

	public void helper(TreeNode n) {
		if (n.left != null)
			helper(n.left);
		count--;
		if (count == 0) {
			number = n.val;
			return;
		}
		if (n.right != null)
			helper(n.right);
	}
	
	/* DFS in-order iterative */
	public int kthSmallest3(TreeNode root, int k) {
		Stack<TreeNode> st = new Stack<>();

		while (root != null) {
			st.push(root);
			root = root.left;
		}

		while (k != 0) {
			TreeNode popedItem = st.pop();
			
			if (--k == 0) return popedItem.val;
			
			TreeNode right = popedItem.right;
			while (right != null) {
				st.push(right);
				right = right.left;
			}
		}

		return -1; // never hit if k is valid
	}
}
