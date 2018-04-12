package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree_101 {
	
	/** 101. Symmetric Tree
	 * 
	 * Given a binary tree, check whether it is a mirror of itself (ie,
	 * symmetric around its center).
	 */
	
    // --------------------- Recursion -------------------------
	
	/**
	 * Two trees are a mirror reflection of each other if:
	 * 
	 * 1. Their two roots have the same value. 
	 * 2. The right subtree of each tree is a mirror reflection of the left 
	 * 		subtree of the other tree.
	 */
	
	public boolean isSymmetric(TreeNode root) {
	    return isMirror(root, root);
	}

	public boolean isMirror(TreeNode t1, TreeNode t2) {
	    if (t1 == null && t2 == null) return true;		// boundary case && base case
	    if (t1 == null || t2 == null) return false;		// only one node exists
	    return (t1.val == t2.val)						// values of two nodes are identical
	        && isMirror(t1.right, t2.left)				// right subtree == left subtree
	        && isMirror(t1.left, t2.right);				// left subtree == right subtree
	}
    
    // --------------------- Iterative -------------------------
    
	public boolean isSymmetricIterative(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode t1 = q.poll();
			TreeNode t2 = q.poll();
			if (t1 == null && t2 == null) 	continue;		// 
			if (t1 == null || t2 == null) 	return false;	// one null node
			if (t1.val != t2.val) 			return false;	// not symmetric
			q.add(t1.left);		// add two nodes which need to be compared together
			q.add(t2.right);
			q.add(t1.right);	// add two nodes which need to be compared together
			q.add(t2.left);
		}
		return true;
	}
}
