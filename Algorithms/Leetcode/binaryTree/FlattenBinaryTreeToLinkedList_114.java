package binaryTree;

import java.util.Stack;

public class FlattenBinaryTreeToLinkedList_114 {
	
	// ----------------------------  Iterative   ----------------------------------
	
	public void flatten(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		if (root == null) return;
		
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode poped = stack.pop();

			if (poped.right != null)
				stack.push(poped.right);
			if (poped.left != null)
				stack.push(poped.left);

			poped.left = null;				// 当前节点指向下一个，左节点设空
			if (!stack.isEmpty())
				poped.right = stack.peek();
		}
	}
	
	// ----------------------------  O(1) Iterative   ----------------------------------
	
	public void flatten2(TreeNode root) {
		TreeNode cur = root;
		
		while (cur != null) {
			if (cur.left != null) {
				TreeNode pre = cur.left;
				while (pre.right != null)
					pre = pre.right;
				pre.right = cur.right;
				cur.right = cur.left;
				cur.left = null;
			}
			cur = cur.right;
		}
	}
	
	
	// ----------------------------  Recursion   ----------------------------------
	
	private TreeNode prev = null;

	public void flattenRecursion(TreeNode root) {
		if (root == null)
			return;
		
		flatten(root.right);
		flatten(root.left);
		root.right = prev;
		root.left = null;
		prev = root;
	}
}
