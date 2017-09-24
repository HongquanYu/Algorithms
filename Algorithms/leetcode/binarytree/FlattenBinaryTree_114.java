package binarytree;

import java.util.Stack;

public class FlattenBinaryTree_114 {
	
	// ----------------------------  Iterative   ----------------------------------
	
	public void flatten(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		if (root == null) return;
		
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode t = stack.pop();

			if (t.right != null)
				stack.push(t.right);
			if (t.left != null)
				stack.push(t.left);

			t.left = null;				// update links
			if (!stack.isEmpty())
				t.right = stack.peek();
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
