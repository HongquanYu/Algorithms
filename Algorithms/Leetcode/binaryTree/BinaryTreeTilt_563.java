package binaryTree;

/**
 * 563. Binary Tree Tilt
 * 
 * Given a binary tree, return the tilt of the whole tree.
 * 
 * The tilt of a tree node is defined as the absolute difference between the sum
 * of all left subtree node values and the sum of all right subtree node values.
 * Null node has tilt 0.
 * 
 * The tilt of the whole tree is defined as the sum of all nodes' tilt. */

public class BinaryTreeTilt_563 {
    public int findTilt(TreeNode root) {
	    	if (root == null)
	        	return 0;
    	
        int left = findTilt(root.left);
        int right = findTilt(root.right);
        
        int tilt = Math.abs(sumOfTree(root.left) - sumOfTree(root.right));

        return tilt + left + right;
    }
    
    private int sumOfTree(TreeNode node) {		// Sum of node and its descendants
	    	if (node == null)
	    		return 0;
	    	return node.val + sumOfTree(node.left) + sumOfTree(node.right);
    }
    
    /** Editorial solution */
    
	private int tilt = 0;

	public int findTilt2(TreeNode root) {
		traverse(root);
		return tilt;
	}

	public int traverse(TreeNode root) {
		if (root == null)
			return 0;
		int left = traverse(root.left);
		int right = traverse(root.right);
		
		tilt += Math.abs(left - right);
		
		return left + right + root.val;
	}
}
