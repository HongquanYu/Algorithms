package binarytree;

import java.util.Stack;

/** 617. Merge Two Binary Trees
 * 
 * Given two binary trees and imagine that when you put one of them to cover the
 * other, some nodes of the two trees are overlapped while the others are not.
 * 
 * You need to merge them into a new binary tree. The merge rule is that if two
 * nodes overlap, then sum node values up as the new value of the merged node.
 * Otherwise, the NOT null node will be used as the node of new tree.
 */

public class MergeBinaryTree_617 {
	
	/** Recursion */
	
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null) return t2;
		if (t2 == null) return t1;
		
		t1.val += t2.val;
		t1.left = mergeTrees(t1.left, t2.left);
		t1.right = mergeTrees(t1.right, t2.right);
		return t1;
	}
	
	public TreeNode mergeTrees3(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null)
			return null;

		int val = (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val);
		TreeNode newNode = new TreeNode(val);

		newNode.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
		newNode.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);

		return newNode;
	}
	
	/** Iterative */
	
	public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
		if (t1 == null)
			return t2;
		
		Stack<TreeNode[]> stack = new Stack<>();
		stack.push(new TreeNode[] { t1, t2 });		// a two dimension array
		
		while (!stack.isEmpty()) {
			
			TreeNode[] t = stack.pop();
			
			if (t[0] == null || t[1] == null)		// one null or two null, continue
				continue;
			
			t[0].val += t[1].val;					// sum up the values of current node
			
			if (t[0].left == null)					// merge left subtree
				t[0].left = t[1].left;
			else
				stack.push(new TreeNode[] { t[0].left, t[1].left });		// push to stack
			
			if (t[0].right == null)					// merge right subtree
				t[0].right = t[1].right;
			else
				stack.push(new TreeNode[] { t[0].right, t[1].right });		// push to stack
			
		}
		
		return t1;		// return first tree
	}
}
