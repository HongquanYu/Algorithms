package binarytree;

import java.util.Stack;

/**
 * 98. Validate Binary Search Tree
 * 
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * 
 * 1, The left subtree of a node contains only nodes with keys less than the node's key. 
 * 2, The right subtree of a node contains only nodes with keys greater than the node's key. 
 * 3, Both the left and right subtrees must also be binary search trees. */

public class ValidateBST_98 {
	
	// --------------------- Recursion -------------------------
	
	TreeNode invalidNode = new TreeNode(Integer.MAX_VALUE);

	public boolean isValidBST(TreeNode root) {
		return isValidBST(root, null) != invalidNode;
	}

	public TreeNode isValidBST(TreeNode node, TreeNode prev) {
		if (node == null)
			return prev;
		prev = isValidBST(node.left, prev);
		if (prev != null && node.val <= prev.val)
			return invalidNode;
		return isValidBST(node.right, node);
	}
	
	// --------------------- Iterative -------------------------
	
	public boolean isValidBST2(TreeNode root) {
		if (root == null)
			return true;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pre = null;
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if (pre != null && root.val <= pre.val)
				return false;
			pre = root;
			root = root.right;
		}
		return true;
	}
}
