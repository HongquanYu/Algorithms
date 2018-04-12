package binarySearchTree;

import java.util.Stack;

import binaryTree.TreeNode;

public class BinarySearchTreeIterator_173 {
	
	/** --------------------- My Solution -------------------------
	 * in-order traversal the tree into an array, visit array every time

	private Stack<TreeNode> stack;
	private ArrayList<Integer> elements;
	private int count;

	public BSTIterator(TreeNode root) { 	// in-order traverse the tree
		stack = new Stack<TreeNode>(); 	// traversal support
		elements = new ArrayList<Integer>(); //
		count = 0;

		if (root == null) return;
		TreeNode cur = root;

		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.pop();
			elements.add(cur.val);
			cur = cur.right;
		}
	}

	public boolean hasNext() {
		return count < elements.size();
	}

	public int next() {
		return elements.get(count++);
	}
	*/
	
	// ---------------------  -------------------------
	
	/**
	private Stack<TreeNode> stack;

	public BSTIterator(TreeNode root) {
		stack = new Stack<>();
		TreeNode cur = root;
		smallestNodeFromCurrentNode(cur);
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}

	public int next() {
		TreeNode node = stack.pop();
		TreeNode cur = node;
		
		if (cur.right != null) 		// traversal right branch
			smallestNodeFromCurrentNode(cur.right);
		
		return node.val;
	}
	
	private void smallestNodeFromCurrentNode(TreeNode node) {	// get left smallest node from current node
		while (node != null) {
			stack.push(node);
			if (node.left != null)
				node = node.left;
			else
				break;
		}
	}
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
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
	}
}
