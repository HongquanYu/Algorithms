package design;

import java.util.Stack;

public class BinarySearchTreeIterator_173 {
	
	/*
	 * I use Stack to store directed left children from root. When next() be called, I just pop one
	 * element and process its right child as new root. The code is pretty straightforward.
	 * 
	 * So this can satisfy O(h) memory, hasNext() in O(1) time, But next() is O(h) time.
	 * 
	 * I can't find a solution that can satisfy both next() in O(1) time, space in O(h).
	 */

	private Stack<TreeNode> stack = new Stack<TreeNode>();

	public BinarySearchTreeIterator_173(TreeNode root) {
		pushAll(root);
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	/** @return the next smallest number */
	public int next() {
		TreeNode tmpNode = stack.pop();
		pushAll(tmpNode.right);
		return tmpNode.val;
	}

	private void pushAll(TreeNode node) {
		for (; node != null; stack.push(node), node = node.left);

	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
