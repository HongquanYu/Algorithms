package binarytree;

import java.util.Stack;

public class IsSameTree_100 {
	/**
	 * 100. Same Tree.
	 * 
	 * Given two binary trees, write a function to check if they are equal or
	 * not.
	 * 
	 * Two binary trees are considered equal if they are structurally identical
	 * and the nodes have the same value.
	 */
	
	// --------------------- Recursion -------------------------

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) // boundary case or base case
			return true;
		if (p != null && q != null && p.val == q.val) // p = q, compare their
														// subtrees
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		else // 1, one of p and q is null; 2, p.val != q.val
			return false;
	}

	// --------------------- Iterative -------------------------
	
	public boolean isSameTree2(TreeNode p, TreeNode q) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		if(p != null)	stack.push(p);
		if(q != null)	stack.push(q);
		
		while (!stack.isEmpty()) {
			if (stack.size() % 2 != 0) return false;
			
			TreeNode t1 = stack.pop(), t2 = stack.pop();
			if (t1.val != t2.val) return false;
			
			if (t1.right != null)	stack.push(t1.right);
			if (t2.right != null)	stack.push(t2.right);
			
			if (stack.size() % 2 != 0) return false;
			
			if (t1.left != null)	stack.push(t1.left);
			if (t2.left != null)	stack.push(t2.left);
			
			if (stack.size() % 2 != 0) return false;
		}
		return true;
	}
}
