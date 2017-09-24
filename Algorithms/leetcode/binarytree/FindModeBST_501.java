package binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 501. Find Mode in Binary Search Tree
 * 
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the
 * most frequently occurred element) in the given BST. */

public class FindModeBST_501 {
	
	/** Two passes. One to find the highest number of occurrences of any value,
	 * and then a second pass to collect all values occurring that often. Both
	 * passes keep track of the current value etc, and the second pass
	 * additionally collects the modes in the result array. I took the value
	 * handling out of the in-order traversal into its own function for clarity.
	 * Also, this way you could very easily replace my recursive in-order
	 * traversal with for example Morris traversal. Then you wouldn't even need
	 * to disregard the recursion stack space in order to claim O(1) extra space */
	
	public int[] findMode(TreeNode root) {
		inorder(root);
		modes = new int[modeCount];
		modeCount = 0;
		currCount = 0;
		inorder(root);
		return modes;
	}

	private int currVal;
	private int currCount = 0;
	private int maxCount = 0;
	private int modeCount = 0;

	private int[] modes;

	private void handleValue(int val) {
		if (val != currVal) {
			currVal = val;
			currCount = 0;
		}
		currCount++;
		if (currCount > maxCount) {
			maxCount = currCount;
			modeCount = 1;
		} else if (currCount == maxCount) {
			if (modes != null)
				modes[modeCount] = currVal;
			modeCount++;
		}
	}

	private void inorder(TreeNode root) {
		if (root == null)
			return;
		inorder(root.left);
		handleValue(root.val);
		inorder(root.right);
	}
	
	/** Here's Morris traversal, just replace my previous inorder function with
	 * this. I hadn't realized it earlier, but having my separate handleValue
	 * function doesn't just nicely separate the traversal logic from this
	 * problem's logic, but it's also beneficial for Morris traversal because
	 * I'm calling handleValue from two different places in the code! */
	
	private void inorder2(TreeNode root) {
		TreeNode node = root;
		while (node != null) {
			if (node.left == null) {
				handleValue(node.val);
				node = node.right;
			} else {
				TreeNode prev = node.left;
				while (prev.right != null && prev.right != node)
					prev = prev.right;
				if (prev.right == null) {
					prev.right = node;
					node = node.left;
				} else {
					prev.right = null;
					handleValue(node.val);
					node = node.right;
				}
			}
		}
	}
	
	// -------------------------------------------------------------------------------
	
	Integer prev = null;
	int count = 1;
	int max = 0;

	public int[] findMode2(TreeNode root) {
		if (root == null)
			return new int[0];

		List<Integer> list = new ArrayList<>();
		traverse(root, list);

		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); ++i)
			res[i] = list.get(i);
		
		return res;
	}

	private void traverse(TreeNode root, List<Integer> list) {
		if (root == null)
			return;
		
		traverse(root.left, list);
		
		if (prev != null) {
			if (root.val == prev) 	count++;
			else 					count = 1;
		}
		if (count > max) {
			max = count;
			list.clear();
			list.add(root.val);
		} else if (count == max) {
			list.add(root.val);
		}
		prev = root.val;
		
		traverse(root.right, list);
	}
}
