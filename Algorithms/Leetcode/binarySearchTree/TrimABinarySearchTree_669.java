package binarySearchTree;

/** @author: Hongquan Yu
 *  @date: Feb 13, 2018
 *
 *  @From: University of Maryland, College Park
 *  @Email: hyu12346@terpmail.umd.edu
 */
public class TrimABinarySearchTree_669 {
	
	/** Concise Recursion */
	
	public TreeNode trimBST(TreeNode root, int L, int R) {
		if (root == null)
			return null;
		
		// 若是 root 不满足此节点，依照其值决定是往左子树还是右子树移动
		if (root.val < L)
			return trimBST(root.right, L, R);
		if (root.val > R)
			return trimBST(root.left, L, R);
		
		// 当前 root 节点满足在 [L, R] 范围内
		root.left = trimBST(root.left, L, R);
		root.right = trimBST(root.right, L, R);

		return root;	
	}
	
	/** Concise Iterative */
	
	public TreeNode trimBSTIterative(TreeNode root, int L, int R) {
		if (root == null)
			return root;
		
		// Find a valid root which is used to return.
		while (root.val < L || root.val > R) {
			if (root.val < L) 	root = root.right;
			if (root.val > R) 	root = root.left;
		}
		
		TreeNode dummy = root;
		
		// Remove the invalid nodes from left subtree.
		while (dummy != null) {
			// If the left child is smaller than L, then we just keep the right subtree of it.
			while (dummy.left != null && dummy.left.val < L)
				dummy.left = dummy.left.right;
			
			dummy = dummy.left;
		}
		
		dummy = root;
		
		// Remove the invalid nodes from right subtree
		while (dummy != null) {
			// If the right child is biggrt than R, then we just keep the left subtree of it.
			while (dummy.right != null && dummy.right.val > R)
				dummy.right = dummy.right.left;
			
			dummy = dummy.right;
		}
		
		return root;
	}
}
