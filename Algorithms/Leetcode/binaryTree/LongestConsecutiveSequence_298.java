package binaryTree;

/** 298. Binary Tree Longest Consecutive Sequence
 * 
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * 
 * The path refers to any sequence of nodes from some starting node to any node
 * in the tree along the parent-child connections. The longest consecutive path
 * need to be from parent to child (cannot be the reverse). */

public class LongestConsecutiveSequence_298 {
	/** -----------------------Top Down Recursion ------------------------------
	 * A top down approach is similar to an in-order traversal. We use a
	 * variable length to store the current consecutive path length and pass it
	 * down the tree. As we traverse, we compare the current node with its
	 * parent node to determine if it is consecutive. If not, we reset the length. */
	
	private int maxLength = 0;

	public int longestConsecutive(TreeNode root) {
		dfs(root, null, 0);
		return maxLength;
	}

	private void dfs(TreeNode p, TreeNode parent, int length) {
		if (p == null)
			return;
		length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;
		maxLength = Math.max(maxLength, length);
		
		dfs(p.left, p, length);
		dfs(p.right, p, length);
	}
	
	/** -----------------------Bottom Up Recursion ------------------------------
	 * The bottom-up approach is similar to a post-order traversal. We return
	 * the consecutive path length starting at current node to its parent. Then
	 * its parent can examine if its node value can be included in this
	 * consecutive path. */
	
	private int maxLength2 = 0;

	public int longestConsecutive2(TreeNode root) {
		dfs(root);
		return maxLength;
	}

	private int dfs(TreeNode p) {
		if (p == null) 	return 0;
		
		int L = dfs(p.left) + 1;
		int R = dfs(p.right) + 1;
		
		if (p.left != null && p.val + 1 != p.left.val)
			L = 1;
		
		if (p.right != null && p.val + 1 != p.right.val)
			R = 1;
		
		int length = Math.max(L, R);
		maxLength = Math.max(maxLength, length);
		
		return length;
	}
}
