package binarytree;

import java.util.Stack;

/**
 * Add to List 99. Recover Binary Search Tree
 * 
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * Note: A solution using O(n) space is pretty straight forward. Could you
 * devise a constant space solution?
 */

public class RecoverTree_99 {
	/** In-order traversal solution
	 *  
	 */
	
	TreeNode firstElement = null;
	TreeNode secondElement = null;
	TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

	public void recoverTree(TreeNode root) {
		inorderTraverse(root);
		
		int temp = firstElement.val;
		firstElement.val = secondElement.val;
		secondElement.val = temp;
	}

	private void inorderTraverse(TreeNode root) {
		if (root == null)
			return;

		inorderTraverse(root.left);

		if (firstElement == null && prevElement.val >= root.val)
			firstElement = prevElement;
		
		if (firstElement != null && prevElement.val >= root.val)
			secondElement = root;
		
		prevElement = root;

		inorderTraverse(root.right);
	}
}
