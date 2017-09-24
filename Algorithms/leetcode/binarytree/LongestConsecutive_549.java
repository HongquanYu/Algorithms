package binarytree;

/** 543. Diameter of Binary Tree
 * 
 * Given a binary tree, you need to compute the length of the diameter of the
 * tree. The diameter of a binary tree is the length of the longest path between
 * any two nodes in a tree. This path may or may not pass through the root. */

public class LongestConsecutive_549 {
	
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
	
	/** 549. Binary Tree Longest Consecutive Sequence II
	 * 
	 * Given a binary tree, you
	 * need to find the length of Longest Consecutive Path in Binary Tree.
	 * 
	 * Especially, this path can be either increasing or decreasing. For
	 * example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path
	 * [1,2,4,3] is not valid. On the other hand, the path can be in the
	 * child-Parent-child order, where not necessarily be parent-child order.
	 */
	
	private int maxval = 0;

	public int longestConsecutive(TreeNode root) {
		longestPath(root);
		return maxval;
	}

	public int[] longestPath(TreeNode root) {
		if (root == null)
			return new int[] { 0, 0 };
		
		int inr = 1, dcr = 1;
		if (root.left != null) {
			
			int[] l = longestPath(root.left);		// recursive call left
			
			if (root.val == root.left.val + 1)		// update inr or dcr
				dcr = l[1] + 1;
			else if (root.val == root.left.val - 1)
				inr = l[0] + 1;
		}
		
		if (root.right != null) {
			
			int[] r = longestPath(root.right);
			
			if (root.val == root.right.val + 1)
				dcr = Math.max(dcr, r[1] + 1);
			
			else if (root.val == root.right.val - 1)
				inr = Math.max(inr, r[0] + 1);
		}
		
		maxval = Math.max(maxval, dcr + inr - 1);
		
		return new int[] { inr, dcr };
	}
}
