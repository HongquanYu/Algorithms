package binaryTree;

/** @author: Hongquan Yu
 *  @date: Mar 4, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */

/** 543. Diameter of Binary Tree
 * 
 * Given a binary tree, you need to compute the length of the diameter of the
 * tree. The diameter of a binary tree is the length of the longest path between
 * any two nodes in a tree. This path may or may not pass through the root. */

public class DiameterOfBinaryTree_543 {
	
	/** For every node, length of longest path which pass it = MaxDepth of its
	 * left subtree + MaxDepth of its right subtree. */
	
	private int max = 0;

	public int diameterOfBinaryTree(TreeNode root) {
		maxDepth(root);
		return max;
	}

	private int maxDepth(TreeNode root) {
		if (root == null)
			return 0;

		int left = maxDepth(root.left);
		int right = maxDepth(root.right);

		max = Math.max(max, left + right);

		return Math.max(left, right) + 1;
	}
}
