package binarytree;

public class IsBinaryTreeBalanced_110 {
	/**
	 * 110. Balanced Binary Tree
	 * 
	 * Given a binary tree, determine if it is height-balanced.
	 * 
	 * For this problem, a height-balanced binary tree is defined as a binary
	 * tree in which the depth of the two subtrees of every node never differ by
	 * more than 1.
	 */
	
	// --------------------- Top down approach -------------------------
	/**
	 * For the current node root, calling depth() for its left and right
	 * children actually has to access all of its children, thus the complexity
	 * is O(N). We do this for each node in the tree, so the overall complexity
	 * of isBalanced will be O(N^2)
	 */
	
    public boolean isBalanced(TreeNode root) {
        if (root == null)			// empty tree or reach leaves
        	return true;

    	if (Math.abs(height(root.left) - height(root.right)) > 1)	// current node is balanced
    		return false;
    	
    	return isBalanced(root.left) && isBalanced(root.right);		// check subtrees
    }
	
	private int height(TreeNode root) {
		if (root == null)
			return 0;
		return 1 + Math.max(height(root.left), height(root.right));
	}
	
	// --------------------- DFS. Bottom up approach -------------------------
	
	/**
	 * Same recursion depth. But computation from bottom, avoid repeatedly compute
	 */
	
	public boolean isBalanced2(TreeNode root) {
		return dfsHeight(root) != -1;
	}
	
	private int dfsHeight(TreeNode root) {
		if (root == null)
			return 0;
		
		int left = dfsHeight(root.left);	// left subtree is not balance
		if (left == -1)		return -1;
		int right = dfsHeight(root.right);	// right subtree is not balance
		if (right == -1)	return -1;
		
		if (Math.abs(left - right) > 1)		// left and right subtree are not balanced
			return -1;
		return Math.max(left, right) + 1;	// return height of this tree
	}
}
